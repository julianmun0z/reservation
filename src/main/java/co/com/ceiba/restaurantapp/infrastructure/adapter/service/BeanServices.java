package co.com.ceiba.restaurantapp.infrastructure.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryBill;
import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryReservation;
import co.com.ceiba.restaurantapp.aplicacion.command.handler.HandlerDeleteReservation;
import co.com.ceiba.restaurantapp.aplicacion.command.handler.HandlerUpdateReservation;
import co.com.ceiba.restaurantapp.aplicacion.query.HandlerGetForIdBill;
import co.com.ceiba.restaurantapp.aplicacion.query.HandlerGetForIdReservation;
import co.com.ceiba.restaurantapp.aplicacion.query.HandlerListReservation;
import co.com.ceiba.restaurantapp.domain.repositories.BillRepository;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRepository;
import co.com.ceiba.restaurantapp.domain.repositories.ReservationRequestRepository;
import co.com.ceiba.restaurantapp.domain.services.BillRequetSerivice;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.BillDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ReservationDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.repositories.BillRepositoryInSql;

@Configuration
public class BeanServices {

	@Autowired
	BillDao billDao;
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	BillRepository billRepository;
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ReservationRequestRepository reservationRequestRepository;
	@Autowired
	private FactoryReservation factoryReservation;

	@Autowired
	private FactoryBill factoryBill;

	@Bean
	public BillRequetSerivice billRequetSerivice() {
		return new BillRequetSerivice(billRepository, factoryBill);
	}

	@Bean
	public BillRepositoryInSql billRepositoryInSql() {
		return new BillRepositoryInSql(billDao, reservationDao);
	}

	@Bean
	public FactoryBill factoryBill() {
		return new FactoryBill();
	}

	@Bean
	public HandlerListReservation handlerListReservation() {
		return new HandlerListReservation(reservationRequestRepository);
	}

	@Bean
	public HandlerUpdateReservation handlerUpdateReservation() {
		return new HandlerUpdateReservation(factoryReservation, reservationRepository);
	}

	@Bean
	public HandlerDeleteReservation handlerDeleteReservation() {
		return new HandlerDeleteReservation(reservationRequestRepository);
	}

	@Bean
	public HandlerGetForIdReservation handlerGetForIdReservation() {
		return new HandlerGetForIdReservation(reservationRequestRepository);
	}

	@Bean
	public HandlerGetForIdBill handlerGetForIdBill() {
		return new HandlerGetForIdBill(billRepository);
	}

}
