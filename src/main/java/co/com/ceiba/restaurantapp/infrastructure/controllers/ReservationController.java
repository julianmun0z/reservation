package co.com.ceiba.restaurantapp.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.repositories.ReservationRepositoryInMemory;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/reservation")

public class ReservationController {

	@Autowired
	ReservationRepositoryInMemory reservationRepositoryPersistent;

	@GetMapping
	public List<Reservation> getReservation() {
		return reservationRepositoryPersistent.getReservations();
	}

	@GetMapping(path = { "/{id}" })
	public Reservation getReservationForId(@PathVariable("id") int id) {
		return reservationRepositoryPersistent.getReservationById(id);
	}

}
