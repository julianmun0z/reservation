package co.com.ceiba.restaurantapp.aplicacion.query;

import java.util.List;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;

public class HandlerListReservation {

	private final ReservationRequestRepository reservationRequestRepository;

	public HandlerListReservation(ReservationRequestRepository reservationRequestRepository) {
		this.reservationRequestRepository = reservationRequestRepository;
	}

	public List<ReservationRequest> execute() {
		return this.reservationRequestRepository.getReservationRequests();
	}

}
