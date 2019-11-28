package co.com.ceiba.restaurantapp.domain.repositories;

import co.com.ceiba.restaurantapp.domain.model.Bill;

public interface BillRepository {

	public Bill getBillById(int id);

	public void addBill(Bill bill);

}
