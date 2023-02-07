/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Slf4j
@Controller
class VetController {

	@Autowired
	VetService vetService;

	private final VetRepository vetRepository;

	public VetController(VetRepository clinicService) {
		this.vetRepository = clinicService;
	}

	@GetMapping("/vets.html")
	public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		return vetService.showVetList(page, model);
	}

	// Información de uso http://localhost:8080/filterLastName?lastName=Douglas
	@GetMapping("/filterLastName")
	public ResponseEntity<String> filterVets(@RequestParam("lastName") String lastName) {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Vet> filteredVets = vetService.findByLastName(lastName, pageable);
		StringBuilder response = new StringBuilder();

		return ResponseBase(filteredVets, response);
	}

	@GetMapping("/filterOr")
	public ResponseEntity<String> filterVetsByFirstOrLastName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Vet> filteredVets = vetService.findByFirstOrLastName(firstName, lastName, pageable);
		StringBuilder response = new StringBuilder();

		return ResponseBase(filteredVets, response);
	}

	@GetMapping("/filterAnd")
	public ResponseEntity<String> filterVetsByFirstAndLastName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Vet> filteredVets = vetService.findByFirstAndLastName(firstName, lastName, pageable);
		StringBuilder response = new StringBuilder();

		return ResponseBase(filteredVets, response);
	}



	private ResponseEntity<String> ResponseBase(Page<Vet> filteredVets, StringBuilder response) {
		for (Vet v : filteredVets) {
			log.info("Vet: " + v.getFirstName());
			response.append(v.getFirstName()).append(" ").append(v.getLastName()).append("\n");
		}
		return ResponseEntity.ok(response.toString());
	}

	@GetMapping({ "/vets" })
	@Operation(summary = "Obtiene la lista veterinarios disponibles")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de veterinarios", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})})
	public @ResponseBody Vets showResourcesVetList() {
		return vetService.showResourcesVetList();
	}

}
