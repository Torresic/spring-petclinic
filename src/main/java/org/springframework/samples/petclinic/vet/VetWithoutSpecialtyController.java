package org.springframework.samples.petclinic.vet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VetWithoutSpecialtyController {

	@Autowired
	private VetService vetService;

	@GetMapping("/vetsBasic/{id}")
	public Vet getVetById(@PathVariable Integer id) {
		return vetService.getVetById(id);
	}

	@GetMapping("/vetsBasic")
	public List<VetWithoutSpecialty> getAllVets() {
		return vetService.getAllVets();
	}

}
