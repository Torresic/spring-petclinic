package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

	private final BillRepository billRepository;

	@Autowired
	public BillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public List<Bill> findAll() {
		return billRepository.findAll();
	}
}
