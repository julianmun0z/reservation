package co.com.ceiba.restaurantapp.aplicacion.command.factoryTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryBill;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class FactoryBillTest {

	@Mock
	private BillBuilder billBuilder;

	@Mock
	private Bill bill;

	@Mock
	private Reservation reservation;
	
	@Mock
	private ReservationRequest reservationRequest;

	@Mock
	private Client client;
	@Mock
	private ClientEntity clientEntity;

	@Mock
	private ReservationEntity reservationEntity;
	
	@Mock
	private FactoryBill factoryBill;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		factoryBill = new FactoryBill();

	}
	
	
	private static final String FIRSTNAME = "juan";
	private static final String LASTNAME = "gomez";
	private static final String EMAIL = "J@J.COM";
	private static final String PHONENUMBER = "123456789";
	private static final int ID_RESERVATION = 9;
	private static final Boolean DECOR = true;
	private static final int NUMBER_PEOPLE = 5;
	private static final Calendar DATE_FOR_DIVISION_DTO = new GregorianCalendar(2020, 8, 1);
	private static final float EXPECTED_PRICE = 240800;
	private static final int DISCOUNT_DAYS = 48160;
	private static final int DISCOUNT_PEOPLE = 36120;
	
	@Test
	public void crearBillTest() {
		//arrange 
		when(reservationRequest.getId()).thenReturn(ID_RESERVATION);
		when(reservationRequest.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservationRequest.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservationRequest.isDecor()).thenReturn(DECOR);
		when(reservationRequest.getFirstName()).thenReturn(FIRSTNAME);
		when(reservationRequest.getLastName()).thenReturn(LASTNAME);
		when(reservationRequest.getEmail()).thenReturn(EMAIL);
		when(reservationRequest.getPhoneNumber()).thenReturn(PHONENUMBER);
		float expectedPrice = EXPECTED_PRICE; 
		int expectedDiscountDay = DISCOUNT_DAYS;
		int expectedDiscountPeople = DISCOUNT_PEOPLE;


		//act
		
		Bill resultbill = factoryBill.crearBill(reservationRequest);
		//assert
		assertEquals(expectedPrice, resultbill.getPrice(),0);
		assertEquals(expectedDiscountDay, resultbill.getDiscpuntForDays());
		assertEquals(expectedDiscountPeople, resultbill.getDiscountForPeople());

	}
}
