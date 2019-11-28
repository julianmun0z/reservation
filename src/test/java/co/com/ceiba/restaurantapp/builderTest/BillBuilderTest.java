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
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.BillEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ReservationEntity;

public class BillBuilderTest {

	private static final int DISCOUNT_FOR_PEOPLE = 5000;
	private static final int DISCOUNT_FOR_DAY = 6000;
	private static final float PRICE = 350000;
	private static final float EXPECTED_PRICE = 350000;
	private static final Calendar TUESDAY = new GregorianCalendar(2019, 8, 10);

	@Mock
	private Client client;

	@Mock
    Reservation reservation;
	@Mock
	private Bill bill;
	@Mock
	private BillEntity billEntity;

	@Mock
	private ReservationEntity reservationEntity;
	
	@Mock
	private ClientEntity clientEntity;
	
	@Mock
	private BillBuilder billBuilder;

	@InjectMocks
	BillBuilderTest billBuilderTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		billBuilder = new BillBuilder();
	}


	@Test
	public void converDtoToEntity() {
		// arrange
		when(bill.getPrice()).thenReturn(PRICE);
		when(bill.getDiscountForPeople()).thenReturn(DISCOUNT_FOR_PEOPLE);
		when(bill.getDiscpuntForDays()).thenReturn(DISCOUNT_FOR_DAY);

		float expected = EXPECTED_PRICE;
		int expectedDiscountPeople = DISCOUNT_FOR_PEOPLE;
		int expectedDiscountDay = DISCOUNT_FOR_DAY;
		// act
		BillEntity result = billBuilder.converBillToBillEntity(bill);

		// assert
		assertEquals(expected, result.getPrice(), 0);
		assertEquals(expectedDiscountPeople, result.getDiscountForPeople());
		assertEquals(expectedDiscountDay, result.getDiscpuntForDays());

	}
	
	@Test
	public void convertBillEntityToBillWhitReservationTest() {
		//arrange

		when(reservationEntity.getNumberPeople()).thenReturn(4);
		when(reservationEntity.getReservationDate()).thenReturn(TUESDAY);
		when(reservationEntity.isDecor()).thenReturn(true);
		when(reservationEntity.getClientEntity()).thenReturn(clientEntity);
		when(reservationEntity.getClientEntity().getFirstName()).thenReturn("julian");
		when(reservationEntity.getClientEntity().getLastName()).thenReturn("arias");
		when(reservationEntity.getClientEntity().getPhoneNumber()).thenReturn("3676578");
		when(reservationEntity.getClientEntity().getEmail()).thenReturn("j@b.com");
		when(billEntity.getBillId()).thenReturn(2);
		when(billEntity.getPrice()).thenReturn(PRICE);
		when(billEntity.getDiscountForPeople()).thenReturn(DISCOUNT_FOR_PEOPLE);
		when(billEntity.getDiscpuntForDays()).thenReturn(DISCOUNT_FOR_DAY);
		float expected = 238000;
		int expectedDiscountPeople = 0;
		int expectedDiscountDay = 47600;

		//act
		
		Bill result=  billBuilder.convertBillEntityToBillWhitReservation(billEntity,reservationEntity);
		//asssert
		
		assertEquals(expected, result.getPrice(), 0);
		assertEquals(expectedDiscountPeople, result.getDiscountForPeople());
		assertEquals(expectedDiscountDay, result.getDiscpuntForDays());
		
		
	}

}
