package co.com.ceiba.restaurantapp.domain.repositories;

import java.util.List;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;

public interface ReservationRequestRepository {

	public List<ReservationRequest> getReservationRequests();

	public void addReservationRequest(ReservationRequest reservationRequest);

	public ReservationRequest ReservationRequestById(int id);

	public void editReservationRequest(ReservationRequest reservationRequest);

	public ReservationRequest deleteReservationRequest(int id);
}
