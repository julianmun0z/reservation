package co.com.ceiba.restaurantapp.infrastructure.adapter.builders;

import org.springframework.context.annotation.Configuration;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;

@Configuration
public class BillBuilder {

	ClientBuilder clientBuilder;
	
	ReservationBuilder reservationBuilder;
	
	Reservation reservation;

	public BillEntity converBillToBillEntity(Bill bill) {

		BillEntity billEntity = new BillEntity();


		billEntity.setPrice(bill.getPrice());
		billEntity.setDiscountForPeople(bill.getDiscountForPeople());
		billEntity.setDiscpuntForDays(bill.getDiscpuntForDays());
		return billEntity;
	}

	public Bill convertBillEntityToBill(BillEntity billEntity) {
		
		Bill bill = new Bill(billEntity.getBillId(), billEntity.getPrice(), billEntity.getDiscountForPeople(), billEntity.getDiscpuntForDays());
		return bill;
	}
}
