package com.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversionGuidelinesValidator {

	private static Map<Character, Integer> nonRepeatableSymbols = new HashMap<Character, Integer>() {
		{
			put('V', 0);
			put('L', 0);
			put('D', 0);
		}
	};

	private static List<Character> repeatableSymbolCache = new ArrayList<>();

	private static Map<Character, List<Character>> validSubtractionMap = new HashMap<Character, List<Character>>() {
		{
			put('I', Arrays.asList('V', 'X'));
			put('V', Collections.<Character> emptyList());
			put('X', Arrays.asList('L', 'C'));
			put('L', Collections.<Character> emptyList());
			put('C', Arrays.asList('D', 'M'));
			put('D', Collections.<Character> emptyList());
			put('M', Collections.<Character> emptyList());
		}
	};

	public static void clearNonRepeatableSymbols() {
		nonRepeatableSymbols.put('V', 0);
		nonRepeatableSymbols.put('L', 0);
		nonRepeatableSymbols.put('D', 0);
	}

	public static void clearRepeatableSymbolCache() {
		repeatableSymbolCache.clear();
	}

	public static void checkForNonRepetableSymbols(Character symbol) throws RomanFormatException {
		Integer count = nonRepeatableSymbols.get(symbol);
		if (count != null) {
			if ((count + 1) == 2) {
				throw new RomanFormatException("D, L and V can never be repeated");
			} else {
				nonRepeatableSymbols.put(symbol, 1);
			}
		}
	}

	public static void checkForRepeatableSymbols(Character symbol) throws RomanFormatException {
		if (repeatableSymbolCache.contains(symbol)) {
			if (repeatableSymbolCache.size() == 3) {
				throw new RomanFormatException("Only 3 successive repetition allowed for I, X, C and M");
			}
			repeatableSymbolCache.add(symbol);
		} else {
			clearRepeatableSymbolCache();
			repeatableSymbolCache.add(symbol);
		}
	}

	public static void validateSubtraction(Character currentSymbol, Character nextSymbol) throws RomanFormatException {
		List<Character> allowedSymbols = validSubtractionMap.get(currentSymbol);
		if (!allowedSymbols.contains(nextSymbol)) {
			throw new RomanFormatException("Subtraction of " + currentSymbol + " from " + nextSymbol
					+ " is not allowed");
		}
	}
}