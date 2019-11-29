package co.com.ceiba.restaurantapp.aplicacion.command.handler;

import org.springframework.stereotype.Component;

import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryReservation;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRepository;
import co.com.ceiba.restaurantapp.domain.services.BillRequetSerivice;

@Component
public class HandlerCreateReservation {

	private final BillRequetSerivice billRequetSerivice;
	private final FactoryReservation factoryReservation;
	private final ReservationRepository reservationRepository;

	public HandlerCreateReservation(BillRequetSerivice billRequetSerivice, FactoryReservation factoryReservation,
			ReservationRepository reservationRepository) {
		this.billRequetSerivice = billRequetSerivice;
		this.factoryReservation = factoryReservation;
		this.reservationRepository = reservationRepository;
	}

	public void execute(ReservationRequest reservationRequest) {
		Reservation reservation = this.factoryReservation.createReservation(reservationRequest);
		this.reservationRepository.addReservation(reservation);
		this.billRequetSerivice.createBill(reservationRequest);

	}

}
