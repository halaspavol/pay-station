package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import modellayer.Currency;

public class TestCustomThings {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 cent should make the display report 1 minutes parking time.
	 */
	@Test
	public void shouldDisplay1MinFor1Eur() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 1; // In minutes
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);

		// Assert
		assertEquals("Should display 1 min for 1 øre", expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Entering 1 euro + 2 euro should make the display report 120 minutes parking
	 * time.
	 */
	@Test
	public void shouldDisplay120MinFor3Eur() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 120; // In minutes
		int firstCoinValue = 1;
		int secondCoinValue = 2;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		ps.addPayment(firstCoinValue, coinCurrency, coinType);
		ps.addPayment(secondCoinValue, coinCurrency, coinType);

		// Assert
		assertEquals("Should display 120 min for 3 eur", expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Entering 10NOK and 1kr should make the display report 120 minutes parking
	 * time.
	 */
	@Test(expected = IllegalCoinException.class)
	public void shouldDisplayErrorFor10NOKAnd1Kr() throws IllegalCoinException {

		// Arrange
		int firstCoinValue = 10;
		int secondCoinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinTypeFraction = Currency.ValidCoinType.FRACTION;
		Currency.ValidCoinType coinTypeInt = Currency.ValidCoinType.INTEGER;

		// Act
		ps.addPayment(firstCoinValue, coinCurrency, coinTypeFraction);
		ps.addPayment(secondCoinValue, coinCurrency, coinTypeInt);
	}

	/**
	 * Entering 1 eur and 50 dkk should make the display report 46 minutes parking
	 * time.
	 */
	@Test
	public void shouldDisplay46MinFor1EurAnd50Dkk() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 46; // In minutes

		int coinValueDkk = 1;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeDkk = Currency.ValidCoinType.INTEGER;

		int coinValueEur = 1;
		Currency.ValidCurrency coinCurrencyEur = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeEur = Currency.ValidCoinType.INTEGER;

		// Act
		ps.addPayment(coinValueDkk, coinCurrencyDkk, coinTypeDkk);
		ps.addPayment(coinValueEur, coinCurrencyEur, coinTypeEur);

		// Assert
		assertEquals("Should display 46 min for 1 dkk and 1 eur", expectedParkingTime, ps.readDisplay());
	}

}
