package com.main;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		InputReader reader = new InputReader();
		List<String> inputs = reader.fileReader();
		InputParser parser = new InputParser();

		try {
			List<String> outputs = parser.parseInput(inputs);
			for (String s : outputs) {
				System.out.println(s);
			}
		} catch (RomanFormatException e) {
			System.err.println(e.getMessage());
		}
	}
}