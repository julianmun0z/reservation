package co.com.ceiba.restaurantapp.infrastructure.adapter.repositories;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.repositories.BillRepository;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;

import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.BillDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ReservationDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class BillRepositoryInSql implements BillRepository {

	private final BillDao billDao;

	private final ReservationDao reservationDao;

	public BillRepositoryInSql(BillDao billDao, ReservationDao reservationDao) {
		this.billDao = billDao;
		this.reservationDao = reservationDao;
	}

	@Override
	public Bill getBillById(int id) {
		ReservationEntity reservationEntity = this.reservationDao.findById(id);
		Bill bill = new BillBuilder().convertBillEntityToBillWhitReservation(reservationEntity);
		return bill;
	}

	@Override
	public void addBill(Bill bill) {
		BillEntity billEntity = new BillBuilder().converBillToBillEntity(bill);
		billDao.save(billEntity);
	}

}
