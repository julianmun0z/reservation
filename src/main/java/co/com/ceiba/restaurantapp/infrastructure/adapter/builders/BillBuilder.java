package co.com.ceiba.restaurantapp.infrastructure.adapter.builders;

import org.springframework.context.annotation.Configuration;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

@Configuration
public class BillBuilder {

	ClientBuilder clientBuilder;

	ReservationBuilder reservationBuilder;

	Reservation reservation;

	BillBuilder billBuilder;

	public BillEntity converBillToBillEntity(Bill bill) {

		BillEntity billEntity = new BillEntity();

		billEntity.setBillId(bill.getIdBill());
		billEntity.setPrice(bill.getPrice());
		billEntity.setDiscountForPeople(bill.getDiscountForPeople());
		billEntity.setDiscpuntForDays(bill.getDiscpuntForDays());
		return billEntity;
	}

	public Bill convertBillEntityToBill(BillEntity billEntity) {

		Bill bill = new Bill(billEntity.getBillId(), billEntity.getPrice(), billEntity.getDiscountForPeople(),
				billEntity.getDiscpuntForDays());
		return bill;
	}

	public Bill convertBillEntityToBillWhitReservation(BillEntity billEntity, ReservationEntity reservationEntity) {
		Client client = new Client(reservationEntity.getClientEntity().getFirstName(), 
				reservationEntity.getClientEntity().getLastName(), reservationEntity.getClientEntity().getEmail(),
				reservationEntity.getClientEntity().getPhoneNumber());
		Reservation reservations = new Reservation(reservationEntity.getIdReservation(),
				reservationEntity.getReservationDate(), reservationEntity.getNumberPeople(),
				reservationEntity.isDecor(), client);

		Bill bill = new Bill(billEntity.getBillId(), billEntity.getPrice(), billEntity.getDiscountForPeople(),
				billEntity.getDiscpuntForDays(), reservations);
		return bill;
	}

}
