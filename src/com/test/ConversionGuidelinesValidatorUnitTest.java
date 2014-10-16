package com.test;

import org.junit.Test;

import com.main.ConversionGuidelinesValidator;
import com.main.RomanFormatException;

public class ConversionGuidelinesValidatorUnitTest {

	@Test
	public void testSomeValidSubtraction() throws RomanFormatException {
		ConversionGuidelinesValidator.validateSubtraction('I', 'V');
		ConversionGuidelinesValidator.validateSubtraction('X', 'C');
		ConversionGuidelinesValidator.validateSubtraction('C', 'D');
	}

	@Test(expected = RomanFormatException.class)
	public void testInvalidSubtraction1() throws RomanFormatException {
		ConversionGuidelinesValidator.validateSubtraction('X', 'D');
	}

	@Test(expected = RomanFormatException.class)
	public void testInvalidSubtraction2() throws RomanFormatException {
		ConversionGuidelinesValidator.validateSubtraction('V', 'X');
	}

	@Test(expected = RomanFormatException.class)
	public void testCheckForRepeatableSymbols() throws RomanFormatException {
		for (int i = 0; i < 4; i++) {
			ConversionGuidelinesValidator.checkForRepeatableSymbols('I');
		}
	}

	@Test(expected = RomanFormatException.class)
	public void testCheckForNonRepetableSymbols() throws RomanFormatException {
		for (int i = 0; i < 2; i++) {
			ConversionGuidelinesValidator.checkForNonRepetableSymbols('D');
		}
	}
}