package co.com.ceiba.restaurantapp.domain.repositories;

import java.util.List;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;

public interface ReservationRequestRepository {

	public List<ReservationRequest> getReservationRequests();

	public void addReservationRequest(ReservationRequest reservationRequest);

	public ReservationRequest reservationRequestById(int id);

	public ReservationRequest deleteReservationRequest(int id);
}
