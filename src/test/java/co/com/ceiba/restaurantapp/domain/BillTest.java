package co.com.ceiba.restaurantapp.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;

public class BillTest {

	private static final float DECORATION_VALUE = 30000;
	private static final int EXPECTED_DISCOUNT_PEOPLE_ZERO = 0;
	private static final int EXPECTED_DISCOUNT_DAYS_ZERO = 0;

	private static final float TUESDAY_DISCOUNT = 70000;

	private static final float EXPECTED_PRICE_ZERO = 0;
	private static final float STARTING_PRICE = 60000;
	private static final int NUMBER_PEOPLE = 4;
	private static final float VALUE_PER_PEOPLE = 200000;

	private static final int NUMBER_PEOPLE_MORE_FIVE = 6;
	private static final int NUMBER_PEOPLE_LESS_FIVE = 4;
	private static final Calendar RESERVATION_NULL = null;

	private static final int DELTA = 0;

	private static final boolean DECOR_TRUE = true;
	private static final boolean DECOR_FALSE = false;

	private static final Calendar DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_ONE = new GregorianCalendar(2019, 9, 11);
	private static final Calendar DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO = new GregorianCalendar(2019, 9, 7);
	private static final Calendar DATE_ONE = new GregorianCalendar(2019, 9, 8);
	private static final Calendar DATE_TWO = new GregorianCalendar(2019, 9, 9);

	private static final Calendar FRIDAY_DAY_FOR_TEST = new GregorianCalendar(2019, 11, 1);
	private static final Calendar DATE_OF_DIFFERENCE_REQUIRED = new GregorianCalendar(2019, 8, 11);
	private static final Calendar DATE_INITIAL_LES_DAY_REQUIRED = new GregorianCalendar(2019, 8, 11);

	private static final Calendar FRIDAY = new GregorianCalendar(2019, 8, 13);
	private static final Calendar SATURDAY = new GregorianCalendar(2019, 8, 14);

	private static final Calendar TUESDAY = new GregorianCalendar(2019, 8, 10);
	private static final Calendar WEDNESDAY = new GregorianCalendar(2019, 8, 11);
	private static final String MENSAGE_FOR_RESTRICTION = "LA RESERVA PARA LOS DIAS VIERNES Y SABADOS DEBEN TENER 15 DIAS DE ANTICIPACION";

	@Mock
	private Bill bill;

	@Mock
	private Client client;

	@Mock
	Reservation reservation;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		@SuppressWarnings("unused")
		Reservation reservation;
	}

	private static final float PRICE = 350000;

	@Test
	public void getCaculatePriceAndDiscountsTest() {
		// arrange

		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE_MORE_FIVE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_TWO);
		Bill bill = new Bill(reservation);
		float expectedPrice = 274800;
		int expectedDiscountPeople = 41220;
		int expectedDiscountDays = 54960;

		// act
		bill.getCaculatePriceAndDiscounts();

		// assert
		assertEquals(expectedPrice, bill.getPrice(), 0);
		assertEquals(expectedDiscountPeople, bill.getDiscountForPeople());
		assertEquals(expectedDiscountDays, bill.getDiscpuntForDays());

	}

	@Test
	public void getPriceResultAndZeroDiscountTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(FRIDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE_MORE_FIVE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		float expectedPrice = EXPECTED_PRICE_ZERO;
		int expectedDiscountPeople = EXPECTED_DISCOUNT_PEOPLE_ZERO;
		int expectedDiscountDays = EXPECTED_DISCOUNT_DAYS_ZERO;
		String expectedMenssage = MENSAGE_FOR_RESTRICTION;
		String messageResult = "";

		// act

		try {
			Bill bill = new Bill(reservation);
			try {
				bill.getCaculatePriceAndDiscounts();
			} catch (Exception e) {
				messageResult = e.getMessage();
				throw e;
			}
		} catch (Exception e) {
			messageResult = e.getMessage();
		}

		// assert
		assertEquals(expectedMenssage, messageResult);
		assertEquals(expectedPrice, bill.getPrice(), 0);
		assertEquals(expectedDiscountPeople, bill.getDiscountForPeople());
		assertEquals(expectedDiscountDays, bill.getDiscpuntForDays());

	}

	@Test
	public void giveValueToThePriceTest() {

		// arrange
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE_MORE_FIVE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		Bill bill = new Bill(reservation);
		float expeted = STARTING_PRICE;
		// act

		float result = bill.giveValueToThePrice();

		// assert
		assertEquals(expeted, result, 0);

	}

	@Test
	public void getDiscuontPerPeople() {
		// arrange
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE_LESS_FIVE);
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		Bill bill = new Bill(reservation);
		float price = PRICE;
		float expected = EXPECTED_PRICE_ZERO;
		// act
		float result = bill.getDiscuontPerPeople(price);

		// assert
		assertEquals(expected, result, 0);

	}

	@Test
	public void ValueToThePriceIsZeroTest() {
		// arrange
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE_LESS_FIVE);
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		Bill bill = new Bill(reservation);
		when(reservation.getReservationDate()).thenReturn(RESERVATION_NULL);

		float expeted = EXPECTED_PRICE_ZERO;
		// act
		float result = bill.giveValueToThePrice();

		// assert
		assertEquals(expeted, result, 0);

	}

	@Test
	public void getValueForPersonTest() {
		// arrange
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		Bill bill = new Bill(reservation);
		float expected = VALUE_PER_PEOPLE;
		// act
		float result = bill.getValueForPerson();

		// assert
		assertEquals(expected, result, DELTA);

	}

	@Test
	public void getDiscountForDaysTuesdayTest() {
		// arrange
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		float price = PRICE;
		float expected = TUESDAY_DISCOUNT;
		Bill bill = new Bill(reservation);

		// act
		float result = bill.getDiscountForDaysTuesdayAndWednesday(price);

		// assert
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void getDiscountForDaysWednesdayTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(WEDNESDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		float price = PRICE;
		float expected = TUESDAY_DISCOUNT;
		Bill bill = new Bill(reservation);

		// act
		float result = bill.getDiscountForDaysTuesdayAndWednesday(price);

		// assert
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void getFixedValueDecorTest() {
		// arrange
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		when(reservation.getReservationDate()).thenReturn(WEDNESDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		float expectedPrice = DECORATION_VALUE;
		Bill bill = new Bill(reservation);
		// act
		float resultPrice = bill.getFixedValueDecor();

		// assert
		assertEquals(expectedPrice, resultPrice, DELTA);
	}

	@Test
	public void getFixedValueDecorIsZeroTest() {
		// arrange
		when(reservation.isDecor()).thenReturn(DECOR_FALSE);
		when(reservation.getReservationDate()).thenReturn(WEDNESDAY);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.getCurrentDate()).thenReturn(DATE_INITIAL_LES_DAY_REQUIRED);
		float expectedPrice = EXPECTED_PRICE_ZERO;
		Bill bill = new Bill(reservation);

		// act
		float resultPrice = bill.getFixedValueDecor();

		// assert
		assertEquals(expectedPrice, resultPrice, DELTA);
	}

	@Test
	public void daysWithRestrictionDiferentZeroTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(FRIDAY_DAY_FOR_TEST);
		when(reservation.getCurrentDate()).thenReturn(DATE_OF_DIFFERENCE_REQUIRED);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		Bill bill = new Bill(reservation);
		float price = PRICE;
		float expected = PRICE;
		// act
		float result = bill.daysWithRestriction(price);

		// assert
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void daysWithRestrictionIsZeroTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		Bill bill = new Bill(reservation);
		when(reservation.getReservationDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_ONE);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		float price = PRICE;
		float expected = EXPECTED_PRICE_ZERO;
		// act
		float result = bill.daysWithRestriction(price);

		// assert
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void daysWithRestrictionWhiteFirfayTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		Bill bill = new Bill(reservation);
		when(reservation.getReservationDate()).thenReturn(FRIDAY);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		float price = PRICE;
		float expected = EXPECTED_PRICE_ZERO;
		// act
		float result = bill.daysWithRestriction(price);

		// assert
		assertEquals(result, expected, DELTA);
	}

	@Test
	public void daysWithRestrictionWhiteSaturdayTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(TUESDAY);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		Bill bill = new Bill(reservation);
		when(reservation.getReservationDate()).thenReturn(SATURDAY);
		when(reservation.getCurrentDate()).thenReturn(DATE_TO_PROVE_DIFFERENCE_BETWEEN_DAYS_TWO);
		float price = PRICE;
		float expected = EXPECTED_PRICE_ZERO;
		// act
		float result = bill.daysWithRestriction(price);

		// assert
		assertEquals(result, expected, DELTA);
	}

	@Test
	public void differenceBetweenCurrentDateAndReservationDateTest() {
		// arrange
		when(reservation.getReservationDate()).thenReturn(DATE_TWO);
		when(reservation.getCurrentDate()).thenReturn(DATE_ONE);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR_TRUE);
		Bill bill = new Bill(reservation);
		long expected = 1;
		// act

		long result = bill.differenceBetweenCurrentDateAndReservationDate();

		// assert
		assertEquals(expected, result);

	}

}
