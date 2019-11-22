package co.com.ceiba.restaurantapp.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.infrastructure.adapter.repositories.ReservationRequestRepositoryInMemory;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/reservationrequest")
public class ReservationRequestController {

	@Autowired
	ReservationRequestRepositoryInMemory reservationRequestRepositoryPersistent;
	ReservationRequest reservationRequest;

	@GetMapping
	public List<ReservationRequest> getReservationRequest() {
		return reservationRequestRepositoryPersistent.getReservationRequests();
	}

	@PostMapping
	public void addReservationResquest(@RequestBody ReservationRequest reservationRequest) {
		reservationRequestRepositoryPersistent.addReservationRequest(reservationRequest);
	}

	@GetMapping(path = { "/{id}" })
	public ReservationRequest getReservationRequestForId(@PathVariable("id") int id) {
		return reservationRequestRepositoryPersistent.ReservationRequestById(id);
	}

	@PutMapping(path = { "/{id}" })
	public void editReservationResquest(@RequestBody ReservationRequest reservationRequest,
			@PathVariable("id") int id) {
		reservationRequestRepositoryPersistent.editReservationRequest(reservationRequest);
	}

	@DeleteMapping(path = { "/{id}" })
	public ReservationRequest delete(@PathVariable("id") int id) {
		return reservationRequestRepositoryPersistent.deleteReservationRequest(id);
	}
}
