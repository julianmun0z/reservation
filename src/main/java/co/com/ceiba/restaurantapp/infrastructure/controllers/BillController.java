package co.com.ceiba.restaurantapp.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.restaurantapp.aplicacion.query.HandlerGetForIdBill;
import co.com.ceiba.restaurantapp.domain.model.Bill;

@RestController
@RequestMapping(value = "/bill")
public class BillController {

	private final HandlerGetForIdBill handlerGetForIdBill;

	public BillController(HandlerGetForIdBill handlerGetForIdBill) {
		this.handlerGetForIdBill = handlerGetForIdBill;

	}

	@GetMapping(path = { "/{id}" })
	public Bill getBillForId(@PathVariable("id") int id) {
		return handlerGetForIdBill.execute(id);
	}
}
