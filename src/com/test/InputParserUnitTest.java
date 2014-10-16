package com.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.main.InputParser;
import com.main.RomanFormatException;

public class InputParserUnitTest {

	private static final String SAMPLE_INPUT_1 = "glob is I";
	private static final String SAMPLE_INPUT_2 = "prok is V";
	private static final String SAMPLE_INPUT_3 = "pish is X";
	private static final String SAMPLE_INPUT_4 = "tegj is L";
	private static final String SAMPLE_INPUT_5 = "glob glob Silver is 34 Credits";
	private static final String INVALID_SAMPLE_INPUT_5 = "prok pish Silver is 34 Credits";

	private static final String SAMPLE_QST_1 = "how much is pish tegj glob glob ?";
	private static final String SAMPLE_QST_2 = "how many Credits is glob prok Silver ?";
	private static final String SAMPLE_QST_3 = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";

	private static final String SAMPLE_ANS_1 = "pish tegj glob glob is 42";
	private static final String SAMPLE_ANS_2 = "glob prok Silver is 68 Credits";
	private static final String SAMPLE_ANS_3 = "I have no idea what you are talking about";

	@Test
	public void testParseSuccess() throws RomanFormatException {
		List<String> inputs = Arrays.asList(SAMPLE_INPUT_1, SAMPLE_INPUT_2, SAMPLE_INPUT_3, SAMPLE_INPUT_4,
				SAMPLE_INPUT_5, SAMPLE_QST_1, SAMPLE_QST_2, SAMPLE_QST_3);
		List<String> actual = Arrays.asList(SAMPLE_ANS_1, SAMPLE_ANS_2, SAMPLE_ANS_3);
		InputParser parser = new InputParser();
		List<String> expected = parser.parseInput(inputs);
		assertThat(actual, is(expected));
	}

	@Test(expected = RomanFormatException.class)
	public void testParseFailureDueToException() throws RomanFormatException {
		List<String> inputs = Arrays.asList(SAMPLE_INPUT_1, SAMPLE_INPUT_2, SAMPLE_INPUT_3, SAMPLE_INPUT_4,
				INVALID_SAMPLE_INPUT_5, SAMPLE_QST_1, SAMPLE_QST_2, SAMPLE_QST_3);
		List<String> actual = Arrays.asList(SAMPLE_ANS_1, SAMPLE_ANS_2, SAMPLE_ANS_3);
		InputParser parser = new InputParser();
		List<String> expected = parser.parseInput(inputs);
		assertThat(actual, is(expected));
	}
}
