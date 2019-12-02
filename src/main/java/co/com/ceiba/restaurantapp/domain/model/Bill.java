package co.com.ceiba.restaurantapp.domain.model;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.restaurantapp.domain.strategies.ArgumentsValidator;

public class Bill {

	private static final float FIXED_PRICE = 60000;
	private static final int INITIALIZING_VALUE = 0;
	private static final float VALUE_FOR_PERSON = 50000;
	private static final int MINIMUM_OF_PEOPLE_FOR_DISCOUNT = 5;
	private static final int PERCENT_FOR_PEOPLE = 15;
	private static final int DISCOUNT_SPLITTER = 100;
	private static final int TUESDAY = 3;
	private static final int WEDNESDAY = 4;
	private static final int PERCENT_DAYS = 20;
	private static final int FIXED_DECOR = 30000;
	private static final int FRIDAY = 6;
	private static final int SATURDAY = 7;
	private static final int MAXIMUM_FOR_DAYS_FOR_RESTRICTION = 15;
	private static final int VALUE_SSIGNED_TO_THE_PRICE_FOR_RESTRICTION = 0;

	private static final int MILLISECOND = 86400000;

	private static final String LA_RESERERVA_PARA_VIERNES_SABADO_DEBE_TENER_15_DIAS_ANTICIPACIONRERVA_PARA_VIERNES_SABADO_DEBE_TENER_15_DIAS_ANTICIPACION = "LA RESERVA PARA LOS DIAS VIERNES Y SABADOS DEBEN TENER 15 DIAS DE ANTICIPACION";

	private int idBill;
	private float price;
	private int discountForPeople;
	private int discpuntForDays;
	private Reservation reservation;

	public Bill(Reservation reservation) {
		this.reservation = reservation;
		this.getCaculatePriceAndDiscounts();
	}

	public int getIdBill() {
		return idBill;
	}

	public float getPrice() {
		return price;
	}

	public int getDiscountForPeople() {
		return discountForPeople;
	}

	public int getDiscpuntForDays() {
		return discpuntForDays;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void getCaculatePriceAndDiscounts() {
		price = giveValueToThePrice();
		price += getValueForPerson();
		price -= getDiscuontPerPeople(price);
		price -= getDiscountForDaysTuesdayAndWednesday(price);
		price += getFixedValueDecor();
		price = daysWithRestriction(price);
		discountForPeople = (int) getDiscuontPerPeople(price);
		discpuntForDays = (int) getDiscountForDaysTuesdayAndWednesday(price);
		validationForFridatAndSaturday(price);
	}

	public float giveValueToThePrice() {
		float newPrice = INITIALIZING_VALUE;
		if (this.reservation.getReservationDate() != null) {
			newPrice = FIXED_PRICE;
		}
		return newPrice;
	}

	public float getValueForPerson() {
		float priceForPerson;
		priceForPerson = VALUE_FOR_PERSON * this.reservation.getNumberPeople();
		return priceForPerson;

	}

	public float getDiscuontPerPeople(float price) {
		float discuont = INITIALIZING_VALUE;
		if (reservation.getNumberPeople() >= MINIMUM_OF_PEOPLE_FOR_DISCOUNT) {
			discuont = price * PERCENT_FOR_PEOPLE / DISCOUNT_SPLITTER;
		}
		return discuont;
	}

	public float getDiscountForDaysTuesdayAndWednesday(float price) {
		float discountDay = INITIALIZING_VALUE;
		int day = reservation.getReservationDate().get(Calendar.DAY_OF_WEEK);

		if (day == TUESDAY || day == WEDNESDAY) {
			discountDay = price * PERCENT_DAYS / DISCOUNT_SPLITTER;

		}
		return discountDay;
	}

	public float getFixedValueDecor() {
		float valueDecor = INITIALIZING_VALUE;
		if (this.reservation.isDecor()) {
			valueDecor = FIXED_DECOR;
		}
		return valueDecor;
	}

	public float daysWithRestriction(float price) {
		float restriction;
		int day = reservation.getReservationDate().get(Calendar.DAY_OF_WEEK);
		long differenceBetweenDates = differenceBetweenCurrentDateAndReservationDate();
		if ((day == FRIDAY || day == SATURDAY) && (differenceBetweenDates <= MAXIMUM_FOR_DAYS_FOR_RESTRICTION)) {
			restriction = VALUE_SSIGNED_TO_THE_PRICE_FOR_RESTRICTION;
		} else {
			restriction = price;
		}
		return restriction;
	}

	public long differenceBetweenCurrentDateAndReservationDate() {
		long daysDifference;
		Date fechaEntrada = reservation.getReservationDate().getTime();
		Date fechaHoy = reservation.getCurrentDate().getTime();
		daysDifference = (fechaEntrada.getTime() - fechaHoy.getTime()) / MILLISECOND;
		return daysDifference;
	}

	public void validationForFridatAndSaturday(float price) {
		ArgumentsValidator.restrictionForValueZero(price,
				LA_RESERERVA_PARA_VIERNES_SABADO_DEBE_TENER_15_DIAS_ANTICIPACIONRERVA_PARA_VIERNES_SABADO_DEBE_TENER_15_DIAS_ANTICIPACION);
	}

}
