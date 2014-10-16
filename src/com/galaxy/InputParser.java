package com.galaxy;

import static com.galaxy.InputParseHelper.ALL_CHAR_MATCH;
import static com.galaxy.InputParseHelper.CREDITS;
import static com.galaxy.InputParseHelper.CREDITS_QUEST;
import static com.galaxy.InputParseHelper.IS;
import static com.galaxy.InputParseHelper.QUESTION_MARK;
import static com.galaxy.InputParseHelper.SPACE;
import static com.galaxy.InputParseHelper.UNIT_QUEST;
import static com.galaxy.InputParseHelper.WRONG_QUEST_ANS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {

	private Map<String, String> unitMap = new HashMap<>();
	private Map<String, Float> metalMap = new HashMap<>();
	private List<String> answers = new ArrayList<>();

	public List<String> parseInput(List<String> inputs) {
		String[] words;
		for (String s : inputs) {
			words = s.split(SPACE);
			List<String> wordList = Arrays.asList(words);
			if (wordList.contains(QUESTION_MARK)) {
				this.parseQuestion(s);
			} else if (wordList.contains(CREDITS)) {
				this.parseMetal(words);
			} else {
				this.parseUnit(words);
			}
		}
		return answers;
	}

	private void parseQuestion(String question) {
		StringBuilder romanNumeral = new StringBuilder();
		StringBuilder answer = new StringBuilder();

		if (question.matches(CREDITS_QUEST + ALL_CHAR_MATCH)) {
			String[] part2 = question.split(CREDITS_QUEST);
			String[] words = part2[1].split(SPACE);
			for (int i = 0; i < words.length; i++) {
				String romanSymbol = unitMap.get(words[i]);
				if (romanSymbol != null) {
					answer.append(words[i]).append(SPACE);
					romanNumeral.append(romanSymbol);
				} else if (words[i].equals(QUESTION_MARK)) {
					float arabicNumeral = RomanToArabicConverter.getArabicNumeral(romanNumeral.toString());
					float totalValue = arabicNumeral * metalMap.get(words[i - 1]);
					answer.append(words[i - 1]).append(SPACE).append(IS).append(SPACE);
					answer.append((int) totalValue).append(SPACE).append(CREDITS);
				}
			}
		} else if (question.matches(UNIT_QUEST + ALL_CHAR_MATCH)) {
			String[] part2 = question.split(UNIT_QUEST);
			String[] words = part2[1].split(SPACE);
			for (int i = 0; i < words.length; i++) {
				String romanSymbol = unitMap.get(words[i]);
				if (romanSymbol != null) {
					answer.append(words[i]).append(SPACE);
					romanNumeral.append(romanSymbol);
				} else if (words[i].equals(QUESTION_MARK)) {
					int arabicNumeral = RomanToArabicConverter.getArabicNumeral(romanNumeral.toString());
					answer.append(IS).append(SPACE).append(arabicNumeral);
				}
			}
		} else {
			answers.add(WRONG_QUEST_ANS);
		}
		answers.add(answer.toString());
	}

	private void parseMetal(String[] words) {
		StringBuilder romanNumeral = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String romanSymbol = unitMap.get(words[i]);
			if (romanSymbol != null) {
				romanNumeral.append(romanSymbol);
			} else {
				float unitValue = RomanToArabicConverter.getArabicNumeral(romanNumeral.toString());
				float credits = Integer.parseInt(words[i + 2]);
				metalMap.put(words[i], credits / unitValue);
				break;
			}
		}
	}

	private void parseUnit(String[] words) {
		unitMap.put(words[0], words[2]);
	}
}
