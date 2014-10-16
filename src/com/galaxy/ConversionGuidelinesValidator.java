package com.galaxy;

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

	public static void checkForNonRepetableSymbols(Character symbol) {
		Integer count = nonRepeatableSymbols.get(symbol);
		if (count != null) {
			if ((count + 1) == 2) {
				System.err.println("D, L, and V can never be repeated");
				System.exit(0);
			} else {
				nonRepeatableSymbols.put(symbol, 1);
			}
		}
	}

	public static void checkForRepeatableSymbols(Character symbol) {
		if (repeatableSymbolCache.contains(symbol)) {
			if (repeatableSymbolCache.size() == 3) {
				System.err.println("Only 3 successive repetition allowed for I, X, C and M");
				System.exit(0);
			}
			repeatableSymbolCache.add(symbol);
		} else {
			repeatableSymbolCache.clear();
			repeatableSymbolCache.add(symbol);
		}
	}

	public static void validateSubtraction(Character currentSymbol, Character nextSymbol) {
		List<Character> allowedSymbols = validSubtractionMap.get(currentSymbol);
		if (!allowedSymbols.contains(nextSymbol)) {
			System.err.println("Subtraction of " + currentSymbol + " from " + nextSymbol + " is not allowed");
			System.exit(0);
		}
	}
}