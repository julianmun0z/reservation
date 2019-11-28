package co.com.ceiba.restaurantapp.infrastructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.restaurantapp.aplicacion.command.handler.HandlerCreateReservation;
import co.com.ceiba.restaurantapp.aplicacion.command.handler.HandlerDeleteReservation;
import co.com.ceiba.restaurantapp.aplicacion.command.handler.HandlerUpdateReservation;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.aplicacion.query.HandlerGetForIdReservation;
import co.com.ceiba.restaurantapp.aplicacion.query.HandlerListReservation;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/reservationrequest")
public class ReservationRequestController {

	private final HandlerCreateReservation handlerCreateReservation;
	private final HandlerListReservation handlerListReservation;
	private final HandlerUpdateReservation handlerUpdateReservation;
	private final HandlerDeleteReservation handlerDeleteReservation;
	private final HandlerGetForIdReservation handlerGetForIdReservation;

	public ReservationRequestController(HandlerCreateReservation handlerCreateReservation,
			HandlerListReservation handlerListReservation, HandlerUpdateReservation handlerUpdateReservation,
			HandlerDeleteReservation handlerDeleteReservation, HandlerGetForIdReservation handlerGetForIdReservation) {
		this.handlerCreateReservation = handlerCreateReservation;
		this.handlerListReservation = handlerListReservation;
		this.handlerUpdateReservation = handlerUpdateReservation;
		this.handlerDeleteReservation = handlerDeleteReservation;
		this.handlerGetForIdReservation = handlerGetForIdReservation;
	}

	@GetMapping
	public List<ReservationRequest> getReservationRequest() {
		return handlerListReservation.execute();
	}

	@PostMapping
	public void addReservationResquest(@RequestBody ReservationRequest reservationRequest) {
		this.handlerCreateReservation.execute(reservationRequest);
	}

	@GetMapping(path = { "/{id}" })
	public ReservationRequest getReservationRequestForId(@PathVariable("id") int id) {
		return handlerGetForIdReservation.execute(id);
	}

	@PutMapping(path = { "/{id}" })
	public void editReservationResquest(@RequestBody ReservationRequest reservationRequest,
			@PathVariable("id") int id) {
		this.handlerUpdateReservation.execute(reservationRequest);
	}

	@DeleteMapping(path = { "/{id}" })
	public ReservationRequest delete(@PathVariable("id") int id) {
		return this.handlerDeleteReservation.execute(id);
	}
}
