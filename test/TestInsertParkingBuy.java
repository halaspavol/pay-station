package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.PReceipt;

public class TestInsertParkingBuy {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 5 cents should buy ticket for 2 minutes parking time
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldBuy2MinTicketFor5Cents() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 2;	// In minutes		
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		PReceipt recipt = ps.buy();
		
		// Assert
		assertEquals("Should get 2 min ticket for 5 coins", expectedParkingTime, recipt.getValue());
	}
}
