package co.com.ceiba.restaurantapp.infrastructure.adapter.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

@Configuration
public class ReservationBuilder {

	@Autowired
	BillBuilder billBuilder;

	@Autowired
	ClientBuilder clientBuilder;

	public ReservationEntity convertReservationToReservationEntity(Reservation reservation) {

		ReservationEntity reservationEntity = new ReservationEntity();

		ClientEntity clientEntity = new ClientBuilder().convertClientToRClientEntity(reservation.getClient());

		reservationEntity.setIdReservation(reservation.getIdReservation());
		reservationEntity.setReservationDate(reservation.getReservationDate());
		reservationEntity.setNumberPeople(reservation.getNumberPeople());
		reservationEntity.setDecor(reservation.isDecor());
		reservationEntity.setClientEntity(clientEntity);
		return reservationEntity;
	}

	public Reservation convertReservationEntityToReservation(ReservationEntity reservationEntity) {

		Client client = new ClientBuilder().convertClientEntityToClient(reservationEntity.getClientEntity());

		return new Reservation(reservationEntity.getIdReservation(), reservationEntity.getReservationDate(),
				reservationEntity.getNumberPeople(), reservationEntity.isDecor(), client);

	}

}
