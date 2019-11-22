package co.com.ceiba.restaurantapp.builderTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.restaurantapp.domain.model.Bill;
import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.model.Reservation;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ClientBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class ClientBuilderTest {

	private static final String FIRSTNAME = "juan";
	private static final String LASTNAME = "gomez";
	private static final String EMAIL = "J@J.COM";
	private static final String PHONENUMBER = "123456789";

	@Mock
	private Client client;

	@Mock
	private ClientEntity clientEntity;

	@Mock
	private ClientBuilder clientBuilder;

	@InjectMocks
	private ClientBuilderTest clientBuilderTest;

	@Mock
	private Reservation reservation;

	@Mock
	private ReservationEntity reservationEntity;

	@Mock
	private Bill bill;

	@Mock
	private BillEntity billEntity;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		clientBuilder = new ClientBuilder();
	}

	@Test
	public void convertClientToClientEntity() {
		// arrange

		when(client.getFirstName()).thenReturn(FIRSTNAME);
		when(client.getLastName()).thenReturn(LASTNAME);
		when(client.getEmail()).thenReturn(EMAIL);
		when(client.getPhoneNumber()).thenReturn(PHONENUMBER);

		String expectedFirstname = FIRSTNAME;
		String expectedFiLastName = LASTNAME;
		String expectedEmail = EMAIL;
		String expectedPhoneNumber = PHONENUMBER;

		// act

		ClientEntity resultClientEntity = clientBuilder.convertClientToRClientEntity(client);

		// assert
		assertEquals(expectedFirstname, resultClientEntity.getFirstName());
		assertEquals(expectedFiLastName, resultClientEntity.getLastName());
		assertEquals(expectedEmail, resultClientEntity.getEmail());
		assertEquals(expectedPhoneNumber, resultClientEntity.getPhoneNumber());
	}

	@Test
	public void convertClientEntityToClient() {
		// arrange

		when(clientEntity.getFirstName()).thenReturn(FIRSTNAME);
		when(clientEntity.getLastName()).thenReturn(LASTNAME);
		when(clientEntity.getEmail()).thenReturn(EMAIL);
		when(clientEntity.getPhoneNumber()).thenReturn(PHONENUMBER);

		String expectedFirstname = FIRSTNAME;
		String expectedFiLastName = LASTNAME;
		String expectedEmail = EMAIL;
		String expectedPhoneNumber = PHONENUMBER;

		// act
		Client resultClient = clientBuilder.convertClientEntityToClient(clientEntity);

		// assert
		assertEquals(expectedFirstname, resultClient.getFirstName());
		assertEquals(expectedFiLastName, resultClient.getLastName());
		assertEquals(expectedEmail, resultClient.getEmail());
		assertEquals(expectedPhoneNumber, resultClient.getPhoneNumber());
	}

}
