package co.com.ceiba.restaurantapp.infrastructure.adapter.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationResquestBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ReservationDao;

@Service
public class ReservationRequestRepositoryInMemory implements ReservationRequestRepository {

	@Autowired
	ReservationResquestBuilder reservationResquestBuilder;

	@Autowired
	ReservationBuilder reservationBuilder;

	@Autowired
	ReservationRepositoryInMemory reservationRepositoryInMemory;

	@Autowired
	BillRepositoryInMemory billRepositoryInMemory;

	@Autowired
	ClientRepositoryInMemory clientRepositoryInMemory;

	@Autowired
	ReservationDao reservationDao;

	BillBuilder billBuilder;

	@Override
	public List<ReservationRequest> getReservationRequests() {
		List<ReservationRequest> requests = new ArrayList<>();
		List<Reservation> reservations = new ArrayList<>();
		List<ReservationEntity> entities = reservationDao.findAll();

		for (ReservationEntity reservationEntity : entities) {
			Reservation reservationDto = reservationBuilder.convertReservationEntityToReservation(reservationEntity);
			reservations.add(reservationDto);
		}
		for (Reservation reservation : reservations) {
			ReservationRequest request = reservationResquestBuilder
					.getReservartionObjectReservationRequest(reservation);
			requests.add(request);
		}
		return requests;
	}

	@Override
	public void addReservationRequest(ReservationRequest reservationRequest) {
		Reservation reservation = reservationResquestBuilder.divisionReservationRequest(reservationRequest);
		reservationRepositoryInMemory.addReservation(reservation);

	}

	@Override
	public ReservationRequest ReservationRequestById(int id) {
		ReservationEntity reservationEntity = reservationDao.findById(id);
		Reservation reservation = reservationBuilder.convertReservationEntityToReservation(reservationEntity);
		ReservationRequest reservationRequest = reservationResquestBuilder
				.getReservartionObjectReservationRequest(reservation);
		return reservationRequest;
	}

	@Override
	public void editReservationRequest(ReservationRequest reservationRequest) {
		Reservation reservation = reservationResquestBuilder.divisionReservationRequest(reservationRequest);
		ReservationEntity reservationEntity = reservationBuilder.convertReservationToReservationEntity(reservation);
		reservationDao.save(reservationEntity);
	}

	@Override
	public ReservationRequest deleteReservationRequest(int id) {
		ReservationEntity reservationEntity = reservationDao.findById(id);
		Reservation reservation = reservationBuilder.convertReservationEntityToReservation(reservationEntity);
		ReservationRequest reservationRequest = reservationResquestBuilder
				.getReservartionObjectReservationRequest(reservation);
		if (reservation != null) {
			reservationDao.delete(reservationEntity);
		}
		return reservationRequest;
	}

}
