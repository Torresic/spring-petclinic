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
package org.springframework.samples.petclinic.owner;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */

@Controller
class OwnerController {
	@Autowired
	OwnerService ownerService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
		return ownerService.findOwner(ownerId);
	}

	@GetMapping("/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		return ownerService.initCreationForm(model);
	}

	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		return ownerService.processCreationForm(owner, result);
	}

	@GetMapping("/owners/find")
	public String initFindForm(Map<String, Object> model) {
		return ownerService.initFindForm(model);
	}

	@GetMapping("/owners")
	@Operation(summary = "Obtiene la lista de dueños")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de dueños", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})})
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
			Model model) {
		return ownerService.processFindForm(page, owner, result, model);
	}

	private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
		return ownerService.addPaginationModel(page, model, paginated);
	}

	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
		return ownerService.findPaginatedForOwnersLastName(page, lastname);
	}

	@GetMapping("/owners/{ownerId}/edit")
	@Operation(summary = "Edita un dueño en especifico")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Dueño editado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})})
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		return ownerService.initUpdateOwnerForm(ownerId, model);
	}

	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
			@PathVariable("ownerId") int ownerId) {
		return ownerService.processUpdateOwnerForm(owner, result, ownerId);
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		return ownerService.showOwner(ownerId);
	}

}
