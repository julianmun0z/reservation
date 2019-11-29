package co.com.ceiba.restaurantapp.aplicacion.command.factoryTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryBill;
import co.com.ceiba.restaurantapp.aplicacion.command.factory.FactoryReservation;
import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class FactoryReservationTest {

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
	private FactoryReservation factoryReservation;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		factoryReservation = new FactoryReservation();

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
	public void createReservation() {
		//arrange 
		when(reservationRequest.getId()).thenReturn(ID_RESERVATION);
		when(reservationRequest.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservationRequest.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservationRequest.isDecor()).thenReturn(DECOR);
		when(reservationRequest.getFirstName()).thenReturn(FIRSTNAME);
		when(reservationRequest.getLastName()).thenReturn(LASTNAME);
		when(reservationRequest.getEmail()).thenReturn(EMAIL);
		when(reservationRequest.getPhoneNumber()).thenReturn(PHONENUMBER);
		Calendar expectedDate = DATE_FOR_DIVISION_DTO; 
		boolean expectedDecor = DECOR;
		int expectedPeople = NUMBER_PEOPLE;
		String expectedName = FIRSTNAME;


		//act
		
		Reservation resultreReservation = factoryReservation.createReservation(reservationRequest);
		//assert
		assertEquals(expectedDate, resultreReservation.getReservationDate());
		assertEquals(expectedDecor, resultreReservation.isDecor());
		assertEquals(expectedPeople, resultreReservation.getNumberPeople());
		assertEquals(expectedName, resultreReservation.getClient().getFirstName());



	}
	
}
