package co.com.ceiba.restaurantapp.domain.repositories;

import java.util.List;

import co.com.ceiba.restaurantapp.domain.model.Reservation;

public interface ReservationRepository {

	public List<Reservation> getReservations();

	public Reservation getReservationById(int id);

	public void addReservation(Reservation reservation);

}
