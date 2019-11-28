package co.com.ceiba.restaurantapp.aplicacion.command.handler;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;

public class HandlerDeleteReservation {

	private ReservationRequestRepository reservationRequestRepository;

	public HandlerDeleteReservation(
			co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository reservationRequestRepository) {
		this.reservationRequestRepository = reservationRequestRepository;
	}

	public ReservationRequest execute(int id) {
		return this.reservationRequestRepository.deleteReservationRequest(id);
	}

}
 