package co.com.ceiba.restaurantapp.infrastructure.adapter.builders;

import org.springframework.context.annotation.Configuration;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;

@Configuration
public class ReservationResquestBuilder {

	public ReservationRequest getReservartionObjectReservationRequest(Reservation reservation) {

		ReservationRequest reservationRequest = new ReservationRequest();

		reservationRequest.setId(reservation.getIdReservation());
		reservationRequest.setReservationDate(reservation.getReservationDate());
		reservationRequest.setDecor(reservation.isDecor());
		reservationRequest.setNumberPeople(reservation.getNumberPeople());
		reservationRequest.setFirstName(reservation.getClient().getFirstName());
		reservationRequest.setLastName(reservation.getClient().getLastName());
		reservationRequest.setEmail(reservation.getClient().getEmail());
		reservationRequest.setPhoneNumber(reservation.getClient().getPhoneNumber());

		return reservationRequest;
	}

}
