package co.com.ceiba.restaurantapp.TestDataBuilder;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

public class BillTestbuilder {

	private static final int ID_BILL = 2;
	private static final float PRICE = 350000;
	private static final int DISCOUNTFORPEOPLE = 15;
	private static final int DISCOUNTFORDAYS = 20;

	private int idBill;
	private float price;
	private int discountForPeople;
	private int discpuntForDays;
	private Reservation reservation;

	public BillTestbuilder() {
		this.idBill  = ID_BILL;
		this.price = PRICE;
		this.discountForPeople = DISCOUNTFORPEOPLE;
		this.discpuntForDays = DISCOUNTFORDAYS;

	}

	public BillTestbuilder whitePrice(float price) {
		this.price = price;
		return this;

	}

	public BillTestbuilder whiteDiscountForPeople(int discountPeople) {
		this.discountForPeople = discountPeople;
		return this;

	}

	public BillTestbuilder whiteDiscountForDays(int discountDays) {
		this.discpuntForDays = discountDays;
		return this;

	}

	public Bill build() {
		return new Bill(reservation);
	}

}
