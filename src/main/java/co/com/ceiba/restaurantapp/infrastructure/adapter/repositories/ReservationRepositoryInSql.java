package co.com.ceiba.restaurantapp.infrastructure.adapter.repositories;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRepository;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ReservationDao;

@Service
public class ReservationRepositoryInSql implements ReservationRepository {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	ReservationBuilder reservationBuilder;

	@Override
	public List<Reservation> getReservations() {
		List<Reservation> reservations = new ArrayList<>();
		List<ReservationEntity> entities = reservationDao.findAll();
		for (ReservationEntity reservationEntity : entities) {
			Reservation reservation = reservationBuilder.convertReservationEntityToReservation(reservationEntity);
			reservations.add(reservation);
		}
		return reservations;
	}

	@Override
	public Reservation getReservationById(int id) {
		ReservationEntity reservationEntity = reservationDao.findById(id);
		Reservation reservation = reservationBuilder.convertReservationEntityToReservation(reservationEntity);
		return reservation;
	}

	@Override
	public void addReservation(Reservation reservation) {
		ReservationEntity reservationEntity = reservationBuilder.convertReservationToReservationEntity(reservation);
		reservationDao.save(reservationEntity);
	}
}
