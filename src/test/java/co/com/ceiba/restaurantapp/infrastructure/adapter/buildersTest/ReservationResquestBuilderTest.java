package co.com.ceiba.restaurantapp.infrastructure.adapter.buildersTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.aplicacion.dto.ReservationRequest;
import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationResquestBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class ReservationResquestBuilderTest {

	private static final int NUMBER_PEOPLE = 5;

	private static final String FIRSTNAME = "juan";
	private static final String LASTNAME = "gomez";
	private static final String EMAIL = "J@J.COM";
	private static final String PHONENUMBER = "123456789";

	private static final int EXPECTED_ID = 4;
	private static final boolean DECOR = true;
	private static final Calendar DATE_WITH_TUESDAY_AND_WENESDAY = new GregorianCalendar(2019, 8, 01);
	private static final Calendar DATE_FOR_DIVISION_DTO = new GregorianCalendar(3020, 9, 12);

	@Mock
	private ReservationRequest reservationRequest;

	@Mock
	private Bill bill;
	@Mock
	private Reservation reservation;

	@Mock
	private Client client;

	@Mock
	private ReservationEntity reservationEntity;

	@InjectMocks
	private ReservationResquestBuilder reservationResquestBuilder;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reservationResquestBuilder = new ReservationResquestBuilder();
	}



	@Test
	public void getReservartionObjectReservationRequestTest() {
		// arrange

		when(reservation.getIdReservation()).thenReturn(EXPECTED_ID);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR);
		when(reservation.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservation.getCurrentDate()).thenReturn(DATE_WITH_TUESDAY_AND_WENESDAY);
		when(reservation.getClient()).thenReturn(client);
		when(client.getFirstName()).thenReturn(FIRSTNAME);
		when(client.getLastName()).thenReturn(LASTNAME);
		when(client.getEmail()).thenReturn(EMAIL);
		when(client.getPhoneNumber()).thenReturn(PHONENUMBER);

		int expectedid = EXPECTED_ID;
		String expectedFisrtName = FIRSTNAME;
		String expectedLastName = LASTNAME;
		String expectedEmail = EMAIL;
		String expectedPhoneNumber = PHONENUMBER;
		Calendar expectedReservationDate = DATE_FOR_DIVISION_DTO;
		int expecteNumberPeople = NUMBER_PEOPLE;
		boolean expedtedDecor = DECOR;

		// act
		ReservationRequest result = reservationResquestBuilder.getReservartionObjectReservationRequest(reservation);

		// assert
		assertEquals(expectedid, result.getId());
		assertEquals(expectedFisrtName, result.getFirstName());
		assertEquals(expectedLastName, result.getLastName());
		assertEquals(expectedEmail, result.getEmail());
		assertEquals(expectedPhoneNumber, result.getPhoneNumber());
		assertEquals(expectedReservationDate, result.getReservationDate());
		assertEquals(expecteNumberPeople, result.getNumberPeople());
		assertEquals(expedtedDecor, result.isDecor());

	}
}
