package com.galaxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {
	public static final String QUESTION_MARK = "?";
	public static final String SPACE = " ";
	public static final String HOW = "how";
	public static final String MANY = "many";
	public static final String MUCH = "much";
	public static final String CREDITS = "Credits";
	public static final String IS = "is";

	Map<String, String> unitMap = new HashMap<>();
	Map<String, Float> metalMap = new HashMap<>();
	List<String> answers = new ArrayList<>();

	void parseQuestion(String[] words) {
		RomanToArabicConverter converter = new RomanToArabicConverter();
		StringBuilder romanNumeral = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		boolean isCreditQuery = Arrays.asList(words).contains(HOW) && Arrays.asList(words).contains(MANY)
				&& Arrays.asList(words).contains(CREDITS) && Arrays.asList(words).contains(IS);
		boolean isUnitValueQuery = Arrays.asList(words).contains(HOW) && Arrays.asList(words).contains(MUCH)
				&& Arrays.asList(words).contains(IS);

		if (isCreditQuery) {
			for (int i = 0; i < words.length; i++) {
				String romanSymbol = unitMap.get(words[i]);
				if (romanSymbol != null) {
					answer.append(words[i]);
					answer.append(SPACE);
					romanNumeral.append(romanSymbol);
				} else if (words[i].equals(QUESTION_MARK)) {
					float totalValue = converter.getArabicNumeral(romanNumeral.toString()) * metalMap.get(words[i - 1]);
					answer.append(words[i - 1]);
					answer.append(SPACE);
					answer.append(IS);
					answer.append(SPACE);
					answer.append((int) totalValue);
					answer.append(SPACE);
					answer.append(CREDITS);
				}
			}
		} else if (isUnitValueQuery) {
			for (int i = 0; i < words.length; i++) {
				String romanSymbol = unitMap.get(words[i]);
				if (romanSymbol != null) {
					answer.append(words[i]);
					answer.append(SPACE);
					romanNumeral.append(romanSymbol);
				} else if (words[i].equals(QUESTION_MARK)) {
					answer.append(IS);
					answer.append(SPACE);
					answer.append(converter.getArabicNumeral(romanNumeral.toString()));
				}
			}
		} else {
			answers.add("I have no idea what you are talking about");
		}
		answers.add(answer.toString());
	}

	void parseMetal(String[] words) {
		RomanToArabicConverter converter = new RomanToArabicConverter();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String romanSymbol = unitMap.get(words[i]);
			if (romanSymbol != null) {
				sb.append(romanSymbol);
			} else {
				float unitValue = converter.getArabicNumeral(sb.toString());
				float credits = Integer.parseInt(words[i + 2]);
				metalMap.put(words[i], credits / unitValue);
				break;
			}
		}
	}

	void parseUnit(String[] words) {
		unitMap.put(words[0], words[2]);
	}

	public static void main(String[] args) {
		InputParser parser = new InputParser();
		InputReader reader = new InputReader();
		List<String> inputs = reader.fileReader();
		String[] words;

		for (String s : inputs) {
			words = s.split(SPACE);
			List<String> wordList = Arrays.asList(words);
			if (wordList.contains(QUESTION_MARK)) {
				parser.parseQuestion(words);
			} else if (wordList.contains(CREDITS)) {
				parser.parseMetal(words);
			} else {
				parser.parseUnit(words);
			}
		}

		System.out.println(parser.unitMap);
		System.out.println(parser.metalMap);
		for (String s : parser.answers) {
			System.out.println(s);
		}
	}
}
