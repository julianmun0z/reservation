package co.com.ceiba.restaurantapp.aplicacion.command.handler;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;;

public class HandlerDeleteReservation {

	private ReservationRequestRepository ReservationRequestRepository;

	public HandlerDeleteReservation(
			co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository reservationRequestRepository) {
		ReservationRequestRepository = reservationRequestRepository;
	}

	public ReservationRequest execute(int id) {
		return this.ReservationRequestRepository.deleteReservationRequest(id);
	}

}
