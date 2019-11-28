package co.com.ceiba.restaurantapp.aplicacion.command.handler;

import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryReservation;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRepository;

public class HandlerUpdateReservation {

	private FactoryReservation factoryReservation;
	private ReservationRepository reservationRepository;

	public HandlerUpdateReservation(FactoryReservation factoryReservation,
			ReservationRepository reservationRepository) {
		this.factoryReservation = factoryReservation;
		this.reservationRepository = reservationRepository;
	}

	public void execute(ReservationRequest reservationRequest) {
		Reservation reservation = this.factoryReservation.createReservation(reservationRequest);
		this.reservationRepository.addReservation(reservation);

	}

}
