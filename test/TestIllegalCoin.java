package test;

import org.junit.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {
		// Arrange
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		//Act
		ps.addPayment(coinValue, coinCurrency, coinType);
	}
	
	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		// Arrange
		int coinValue = 999;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		//Act
		ps.addPayment(coinValue, coinCurrency, coinType);
	}
}
