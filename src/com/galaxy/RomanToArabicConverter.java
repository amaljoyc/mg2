package com.galaxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanToArabicConverter {
	Map<Character, Integer> symbolValueMap = new HashMap<Character, Integer>() {
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

	Map<Character, Integer> nonRepetableSymbols = new HashMap<Character, Integer>() {
		{
			put('V', 0);
			put('L', 0);
			put('D', 0);
		}
	};

	List<Character> repetableSymbolCache = new ArrayList<>();

	Map<Character, List<Character>> validSubtractionMap = new HashMap<Character, List<Character>>() {
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

	public void checkForNonRepetableSymbols(Character symbol) {
		Integer count = nonRepetableSymbols.get(symbol);
		if (count != null) {
			if ((count + 1) == 2) {
				System.err.println("D, L, and V can never be repeated");
				System.exit(0);
			} else {
				nonRepetableSymbols.put(symbol, 1);
			}
		}
	}

	public void checkForRepeatableSymbols(Character symbol) {
		if (repetableSymbolCache.contains(symbol)) {
			if (repetableSymbolCache.size() == 3) {
				System.err.println("Only 3 successive repetition allowed for I, X, C and M");
				System.exit(0);
			}
			repetableSymbolCache.add(symbol);
		} else {
			repetableSymbolCache.clear();
			repetableSymbolCache.add(symbol);
		}
	}

	public void validateSubtraction(Character currentSymbol, Character nextSymbol) {
		List<Character> allowedSymbols = validSubtractionMap.get(currentSymbol);
		if (!allowedSymbols.contains(nextSymbol)) {
			System.err.println("Subtraction of " + currentSymbol + " from " + nextSymbol + " is not allowed");
			System.exit(0);
		}
	}

	public int getArabicNumeral(String romanNumeral) {
		char[] romanArray = romanNumeral.toCharArray();
		int arabicNumeral = 0;
		for (int i = 0; i < romanArray.length; i++) {
			char currentSymbol = romanArray[i];
			this.checkForNonRepetableSymbols(currentSymbol);
			this.checkForRepeatableSymbols(currentSymbol);
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
				this.checkForNonRepetableSymbols(nextSymbol);
				this.validateSubtraction(currentSymbol, nextSymbol);
				arabicNumeral += (nextValue - currentValue);
				i++;
			}
		}
		return arabicNumeral;
	}

	public static void main(String[] args) {
		RomanToArabicConverter converter = new RomanToArabicConverter();
		int n = converter.getArabicNumeral("MMXIV");
		System.out.println(n);
	}
}