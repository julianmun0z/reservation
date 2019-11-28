package co.com.ceiba.restaurantapp.aplicacion.command.factory;

import org.springframework.stereotype.Component;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

@Component
public class FactoryReservation {

	public Reservation createReservation(ReservationRequest reservationRequest) {

		Client client = new Client(reservationRequest.getId(), reservationRequest.getFirstName(),
				reservationRequest.getLastName(), reservationRequest.getEmail(), reservationRequest.getPhoneNumber());

		return new Reservation(reservationRequest.getId(), reservationRequest.getReservationDate(),
				reservationRequest.getNumberPeople(), reservationRequest.isDecor(), client);

	}
}
