package co.com.ceiba.restaurantapp.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.infrastructure.adapter.repositories.BillRepositoryInSql;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/bill")
public class BillController {

	
	@Autowired
	BillRepositoryInSql reservationRepositoryPersistent;

	@GetMapping
	public List<Bill> getReservation() {
		return reservationRepositoryPersistent.getBills();
	}

	@GetMapping(path = { "/{id}" })
	public Bill getBillForId(@PathVariable("id") int id) {
		return reservationRepositoryPersistent.getBillById(id);
	}
}
