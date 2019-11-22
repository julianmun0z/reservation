package co.com.ceiba.restaurantapp.builderTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.BillBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ReservationBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class ReservationBuilderTest {

	private static final int ID_RESERVATION = 9;
	private static final Boolean DECOR = true;
	private static final int NUMBER_PEOPLE = 5;

	private static final float PRICE = 60000;

	private static final String FIRSTNAME = "juan";
	private static final String LASTNAME = "gomez";
	private static final String EMAIL = "J@J.COM";
	private static final String PHONENUMBER = "123456789";

	private static final Calendar DATE_FOR_DIVISION_DTO = new GregorianCalendar(2019, 9, 12);

	@Mock
	private Bill bill;

	@Mock
	private Client client;

	@Mock
	private Reservation reservation;

	@Mock
	private ReservationEntity reservationEntity;

	@Mock
	private BillEntity billEntity;

	@Mock
	private ClientEntity clientEntity;

	@Mock
	ReservationBuilder reservationBuilder;

	@Mock
	private BillBuilder billBuilder;

	@InjectMocks
	ReservationBuilderTest reservationBuilderTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reservationBuilder = new ReservationBuilder();

	}

	@Test
	public void converReservationToEntityTest() {

		// arrange
		when(reservation.getIdReservation()).thenReturn(ID_RESERVATION);
		when(reservation.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservation.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservation.isDecor()).thenReturn(DECOR);
		when(reservation.getBill()).thenReturn(bill);
		when(reservation.getBill().getPrice()).thenReturn(PRICE);
		when(reservation.getClient()).thenReturn(client);
		when(reservation.getClient().getFirstName()).thenReturn(FIRSTNAME);

		Calendar expectedReservationDay = DATE_FOR_DIVISION_DTO;
		boolean expectedDecor = DECOR;
		int expectedPeople = NUMBER_PEOPLE;
		int expectedIdReservation = ID_RESERVATION;
		float expectedPrice = PRICE;
		String expectedFirstName = FIRSTNAME;

		// act
		ReservationEntity resultReservationEntity = reservationBuilder
				.convertReservationToReservationEntity(reservation);

		// assert

		assertEquals(expectedIdReservation, resultReservationEntity.getIdReservation(), 0);
		assertEquals(expectedReservationDay, resultReservationEntity.getReservationDate());
		assertEquals(expectedDecor, resultReservationEntity.isDecor());
		assertEquals(expectedPeople, resultReservationEntity.getNumberPeople());
		assertEquals(expectedPrice, resultReservationEntity.getBillEntity().getPrice(), 0);
		assertEquals(expectedFirstName, resultReservationEntity.getClientEntity().getFirstName());
	}

	@Test
	public void convertEntityToReservation() {
		// arrange
		when(reservationEntity.getReservationDate()).thenReturn(DATE_FOR_DIVISION_DTO);
		when(reservationEntity.getNumberPeople()).thenReturn(NUMBER_PEOPLE);
		when(reservationEntity.isDecor()).thenReturn(DECOR);
		when(reservationEntity.getBillEntity()).thenReturn(billEntity);
		when(reservationEntity.getClientEntity()).thenReturn(clientEntity);
		when(reservationEntity.getClientEntity().getFirstName()).thenReturn(FIRSTNAME);
		when(reservationEntity.getClientEntity().getLastName()).thenReturn(LASTNAME);
		when(reservationEntity.getClientEntity().getEmail()).thenReturn(EMAIL);
		when(reservationEntity.getClientEntity().getPhoneNumber()).thenReturn(PHONENUMBER);

		Calendar expectedReservationDay = DATE_FOR_DIVISION_DTO;
		boolean expectedDecor = DECOR;
		int expectedPeople = NUMBER_PEOPLE;
		String expectedFirstName = FIRSTNAME;
		String expectedLastName = LASTNAME;
		String expectedEmail = EMAIL;
		String expectedPhoneNumber = PHONENUMBER;
		// act
		Reservation resultReservation = reservationBuilder.convertReservationEntityToReservation(reservationEntity);

		// assert
		assertEquals(expectedReservationDay, resultReservation.getReservationDate());
		assertEquals(expectedDecor, resultReservation.isDecor());
		assertEquals(expectedPeople, resultReservation.getNumberPeople());
		assertEquals(expectedFirstName, resultReservation.getClient().getFirstName());
		assertEquals(expectedLastName, resultReservation.getClient().getLastName());
		assertEquals(expectedEmail, resultReservation.getClient().getEmail());
		assertEquals(expectedPhoneNumber, resultReservation.getClient().getPhoneNumber());

	}

}
