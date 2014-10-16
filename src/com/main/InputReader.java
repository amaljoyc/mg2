package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
	public static final String inputFileLocation = "src/resources/input.txt";

	public List<String> fileReader() {
		List<String> inputs = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFileLocation))) {
			String line = br.readLine();
			while (line != null) {
				inputs.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		return inputs;
	}
}
