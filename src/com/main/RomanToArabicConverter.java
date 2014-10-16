package com.main;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabicConverter {

	private static Map<Character, Integer> symbolValueMap = new HashMap<Character, Integer>() {
		{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}
	};

	public static int getArabicNumeral(String romanNumeral) throws RomanFormatException {
		char[] romanArray = romanNumeral.toCharArray();
		int arabicNumeral = 0;
		for (int i = 0; i < romanArray.length; i++) {
			char currentSymbol = romanArray[i];
			ConversionGuidelinesValidator.checkForNonRepetableSymbols(currentSymbol);
			ConversionGuidelinesValidator.checkForRepeatableSymbols(currentSymbol);
			int currentValue = symbolValueMap.get(currentSymbol);
			if (i + 1 == romanArray.length) {
				arabicNumeral += currentValue;
				break;
			}

			char nextSymbol = romanArray[i + 1];
			int nextValue = symbolValueMap.get(nextSymbol);
			if (currentValue >= nextValue) {
				arabicNumeral += currentValue;
			} else {
				ConversionGuidelinesValidator.checkForNonRepetableSymbols(nextSymbol);
				ConversionGuidelinesValidator.validateSubtraction(currentSymbol, nextSymbol);
				arabicNumeral += (nextValue - currentValue);
				i++;
			}
		}
		ConversionGuidelinesValidator.clearNonRepeatableSymbols();
		ConversionGuidelinesValidator.clearRepeatableSymbolCache();
		return arabicNumeral;
	}
}