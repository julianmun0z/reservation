package co.com.ceiba.restaurantapp.aplicacion.query;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.repositories.BillRepository;

public class HandlerGetForIdBill {

	private final BillRepository billRepository;

	public HandlerGetForIdBill(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public Bill execute(int id) {
		return this.billRepository.getBillById(id);
	}

}
