package org.springframework.samples.petclinic.vet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetService {

	@Autowired
	private final VetWithoutSpecialtyRepository VetWithoutSpecialtyRepository;

	public VetService(
			org.springframework.samples.petclinic.vet.VetWithoutSpecialtyRepository VetWithoutSpecialtyRepository) {
		this.VetWithoutSpecialtyRepository = VetWithoutSpecialtyRepository;
	}

	public List<VetWithoutSpecialty> getAllVets() {
		return VetWithoutSpecialtyRepository.findAll();
	}

	public VetWithoutSpecialty saveVet(VetWithoutSpecialty vetWithoutSpecialty) {
		return VetWithoutSpecialtyRepository.save(vetWithoutSpecialty);
	}

	public Vet getVetById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
