package org.springframework.samples.petclinic.owner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BillController {

	private final BillService billService;

	@Autowired
	public BillController(BillService billService) {
		this.billService = billService;
	}

	@GetMapping("/bills")
	@Operation(summary = "Obtiene la lista de Facturas")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de Facturas", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})})
	public String showBills(Model model) {
		List<Bill> bills = billService.findAll();
		model.addAttribute("bills", bills);
		return "owners/ownerBills";
	}
}
