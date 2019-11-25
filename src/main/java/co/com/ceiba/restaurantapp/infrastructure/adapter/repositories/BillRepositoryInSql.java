package co.com.ceiba.restaurantapp.infrastructure.adapter.repositories;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.repositories.BillRepository;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationResquestBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.BillDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ReservationDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

@Service
public class BillRepositoryInSql implements BillRepository {

	@Autowired
	BillDao billDao;

	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	BillBuilder billBuilder;

	ReservationRequest reservationRequest;

	@Autowired
	ReservationResquestBuilder reservationResquestBuilder;

	@Autowired
	ReservationBuilder reservationBuilder;

	@Override
	public List<Bill> getBills() {
		List<Bill> bills = new ArrayList<>();
		List<BillEntity> billEntities = billDao.findAll();
		for (BillEntity billEntity : billEntities) {
			Bill bill = billBuilder.convertBillEntityToBill(billEntity);
			bills.add(bill);
		}
		return bills;
	}

	@Override
	public Bill getBillById(int id) {
		BillEntity billEntity = billDao.findById(id);
		ReservationEntity reservationEntity = reservationDao.findById(id);
 		Bill bill = billBuilder.convertBillEntityToBillWhitReservation(billEntity,reservationEntity);
		return bill;
	}

	@Override
	public void addBill(Bill bill) {
		BillEntity billEntity = billBuilder.converBillToBillEntity(bill);
		billDao.save(billEntity);
	}

}
