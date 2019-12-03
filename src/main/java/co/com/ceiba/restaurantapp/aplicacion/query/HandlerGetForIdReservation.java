package co.com.ceiba.restaurantapp.aplicacion.query;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;

public class HandlerGetForIdReservation {

	private ReservationRequestRepository reservationRequestRepository;

	public HandlerGetForIdReservation(
			co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository reservationRequestRepository) {
		this.reservationRequestRepository = reservationRequestRepository;
	}

	public ReservationRequest execute(int id) {
		return this.reservationRequestRepository.reservationRequestById(id);
	}

}
