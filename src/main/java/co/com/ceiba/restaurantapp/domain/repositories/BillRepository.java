package co.com.ceiba.restaurantapp.domain.repositories;

import java.util.List;

import co.com.ceiba.restaurantapp.domain.model.Bill;

public interface BillRepository {

	public List<Bill> getBills();

	public Bill getBillById(int id);

	public void addBill(Bill bill);
}
