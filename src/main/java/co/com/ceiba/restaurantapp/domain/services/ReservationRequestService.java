package co.com.ceiba.restaurantapp.domain.services;

import org.springframework.stereotype.Service;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

@Service
public class ReservationRequestService {

	public Reservation createReservation(ReservationRequest reservationRequest) {

		Client client = new Client(reservationRequest.getFirstName(), reservationRequest.getLastName(),
				reservationRequest.getEmail(), reservationRequest.getPhoneNumber());

		Reservation reservation = new Reservation(reservationRequest.getId(), reservationRequest.getReservationDate(),
				reservationRequest.getNumberPeople(), reservationRequest.isDecor(), client);

		Bill bill = new Bill(reservation);

		reservation.setBill(bill);

		return reservation;
	}
}