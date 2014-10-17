package com.test;

import static com.test.InputParserUnitTestHelper.INVALID_SAMPLE_INPUT_5;
import static com.test.InputParserUnitTestHelper.SAMPLE_ANS_1;
import static com.test.InputParserUnitTestHelper.SAMPLE_ANS_2;
import static com.test.InputParserUnitTestHelper.SAMPLE_ANS_3;
import static com.test.InputParserUnitTestHelper.SAMPLE_INPUT_1;
import static com.test.InputParserUnitTestHelper.SAMPLE_INPUT_2;
import static com.test.InputParserUnitTestHelper.SAMPLE_INPUT_3;
import static com.test.InputParserUnitTestHelper.SAMPLE_INPUT_4;
import static com.test.InputParserUnitTestHelper.SAMPLE_INPUT_5;
import static com.test.InputParserUnitTestHelper.SAMPLE_QST_1;
import static com.test.InputParserUnitTestHelper.SAMPLE_QST_2;
import static com.test.InputParserUnitTestHelper.SAMPLE_QST_3;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.main.InputParser;
import com.main.RomanFormatException;

public class InputParserUnitTest {

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