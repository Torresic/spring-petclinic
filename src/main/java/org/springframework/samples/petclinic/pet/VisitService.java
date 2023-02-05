package org.springframework.samples.petclinic.pet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class VisitService {

	@Autowired
	VisitRepository visitRepository;

	private final OwnerRepository owners;

	public VisitService(OwnerRepository owners) {
		this.owners = owners;
	}

	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,
									  BindingResult result) {

		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}

		owner.addVisit(petId, visit);
		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";

	}

	public Visit loadPetWithVisit(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
								  Map<String, Object> model) {
		Owner owner = this.owners.findById(ownerId);

		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		model.put("owner", owner);

		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}

	public Visit addVisit(Pet pet, LocalDate date, String description) {
		Visit visit = new Visit();
		visit.setDate(date);
		visit.setDescription(description);
		visitRepository.save(visit);
		return visit;
	}

	public List<Visit> findLatestVisits() {
		return visitRepository.findLatestVisits();
	}
}
