package co.com.ceiba.restaurantapp.aplicacion.command.factory;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

public class FactoryBill {

	public Bill crearBill(ReservationRequest reservationRequest) {
		Client client = new Client(reservationRequest.getId(), reservationRequest.getFirstName(),
				reservationRequest.getLastName(), reservationRequest.getEmail(), reservationRequest.getPhoneNumber());

		Reservation reservation = new Reservation(reservationRequest.getId(), reservationRequest.getReservationDate(),
				reservationRequest.getNumberPeople(), reservationRequest.isDecor(), client);

		return new Bill(reservation);
	}

}
