package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.main.RomanFormatException;
import com.main.RomanToArabicConverter;

public class RomanToArabicConverterUnitTest {

	@Test
	public void testSomeValidConversions() throws RomanFormatException {
		assertEquals(2006, RomanToArabicConverter.getArabicNumeral("MMVI"));
		assertEquals(1944, RomanToArabicConverter.getArabicNumeral("MCMXLIV"));
		assertEquals(1903, RomanToArabicConverter.getArabicNumeral("MCMIII"));
		assertEquals(2014, RomanToArabicConverter.getArabicNumeral("MMXIV"));
		assertEquals(13, RomanToArabicConverter.getArabicNumeral("XIII"));
		assertEquals(207, RomanToArabicConverter.getArabicNumeral("CCVII"));
		assertEquals(1066, RomanToArabicConverter.getArabicNumeral("MLXVI"));
		assertEquals(1954, RomanToArabicConverter.getArabicNumeral("MCMLIV"));
		assertEquals(1990, RomanToArabicConverter.getArabicNumeral("MCMXC"));
		assertEquals(900, RomanToArabicConverter.getArabicNumeral("CM"));
	}
}
