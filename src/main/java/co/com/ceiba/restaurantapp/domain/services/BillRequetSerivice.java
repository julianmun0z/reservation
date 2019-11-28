package co.com.ceiba.restaurantapp.domain.services;


import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryBill;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.repositories.BillRepository;

public class BillRequetSerivice {

	private final BillRepository billRepository;
	private final FactoryBill factoryBill;

	
	public BillRequetSerivice(BillRepository billRepository, FactoryBill factoryBill) {
		this.billRepository = billRepository;
		this.factoryBill = factoryBill;
	}


	public void createBill(ReservationRequest reservationRequest) {
		Bill bill = this.factoryBill.crearBill(reservationRequest);
		this.billRepository.addBill(bill);

	}
}
