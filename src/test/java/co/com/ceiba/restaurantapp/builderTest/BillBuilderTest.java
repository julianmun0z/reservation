package co.com.ceiba.restaurantapp.builderTest;

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
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class BillBuilderTest {

	private static final String FIRSTNAME = "juan";
	private static final String LASTNAME = "gomez";
	private static final String EMAIL = "J@J.COM";
	private static final String PHONENUMBER = "123456789";
	private static final int ID_RESERVATION = 9;
	private static final Boolean DECOR = true;
	private static final int NUMBER_PEOPLE = 5;
	private static final Calendar DATE_FOR_DIVISION_DTO = new GregorianCalendar(2020, 8, 1);
	private static final float EXPECTED_PRICE = 60000;
	private static final int BILL_ID = 1;
	private static final int DISCOUNT_DAYS = 500;
	private static final int DISCOUNT_PEOPLE = 300;

	@Mock
	private BillBuilder billBuilder;

	@Mock
	private Bill bill;

	@Mock
	private Reservation reservation;

	@Mock
	private Client client;
	@Mock
	private ClientEntity clientEntity;

	@Mock
	private ReservationEntity reservationEntity;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		billBuilder = new BillBuilder();

	}

	@Test
	public void converBillToBillEntityTest() {
		// arrange

		when(bill.getIdBill()).thenReturn(BILL_ID);
		when(bill.getPrice()).thenReturn(EXPECTED_PRICE);
		when(bill.getDiscpuntForDays()).thenReturn(DISCOUNT_DAYS);
		when(bill.getDiscountForPeople()).thenReturn(DISCOUNT_PEOPLE);

		float expectedValue = EXPECTED_PRICE;
		int expedtedId = BILL_ID;
		int expedtedDiscountDay = DISCOUNT_DAYS;
		int expedtedDiscountPeople = DISCOUNT_PEOPLE;

		// act
		BillEntity billEntity = billBuilder.converBillToBillEntity(bill);

		// assert
		assertEquals(expectedValue, billEntity.getPrice(), 0);
		assertEquals(expedtedId, billEntity.getBillId());
		assertEquals(expedtedDiscountDay, billEntity.getDiscpuntForDays());
		assertEquals(expedtedDiscountPeople, billEntity.getDiscountForPeople());
	}

	@Test
	public void convertBillEntityToBillWhitReservationTest() {
		
		when(reservationEntity.getIdReservation()).thenReturn(ID_RESERVATION);
		when(reservationEntity.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservationEntity.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservationEntity.isDecor()).thenReturn(DECOR);
		when(reservationEntity.getClientEntity()).thenReturn(clientEntity);
		when(clientEntity.getClientId()).thenReturn(1);
		when(clientEntity.getFirstName()).thenReturn(FIRSTNAME);
		when(clientEntity.getLastName()).thenReturn(LASTNAME);
		when(clientEntity.getEmail()).thenReturn(EMAIL);
		when(clientEntity.getPhoneNumber()).thenReturn(PHONENUMBER);

		float expectedPriceToBill = 240800;
		int expedtedId = 0;
		int expedtedDiscountDay = 48160;
		int expedtedDiscountPeople = 36120;
		
		
		Bill bill = billBuilder.convertBillEntityToBillWhitReservation(reservationEntity);

		assertEquals(expectedPriceToBill, bill.getPrice(), 0);
		assertEquals(expedtedId, bill.getIdBill());
		assertEquals(expedtedDiscountDay, bill.getDiscpuntForDays());
		assertEquals(expedtedDiscountPeople, bill.getDiscountForPeople());
		assertEquals(DECOR, bill.getReservation().isDecor());
	}

}
