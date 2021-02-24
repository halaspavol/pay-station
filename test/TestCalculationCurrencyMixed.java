package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 cent and 50 øre should make the display report 4 minutes parking time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd50Ore() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 4;	// In minutes
		
		int coinValueDkk = 50;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeDkk = Currency.ValidCoinType.FRACTION;
		
		int coinValueEur = 1;
		Currency.ValidCurrency coinCurrencyEur = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeEur = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValueDkk, coinCurrencyDkk, coinTypeDkk);
		ps.addPayment(coinValueEur, coinCurrencyEur, coinTypeEur);

		// Assert
		assertEquals("Should display 4 min for 1 øre and 1 eur", expectedParkingTime, ps.readDisplay());		
	}

	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
