package com.jenkins.apps;

import static org.junit.Assert.*;


import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
public class LongDivisionTest {
    LongDivision division = new LongDivision();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Test(expected = NullPointerException.class)
    public void testRunLongDivisionNullOnNull() throws NullPointerException {
        division.runLongDivision(null, null);
    }
    @Test
    public void testRunLongDivision3015on5() {
        String result = division.runLongDivision("3015", "5");
        assertThat(result, equalTo(" 3015|5\n-30  |---\n  -  |603\n   15\n  -15\n    -\n    0"));
    }
    @Test
    public void testRunLongDivision512on8() {
        String result = division.runLongDivision("512", "8");
        assertThat(result, equalTo(" 512|8\n-48 |--\n  - |64\n  32\n -32\n   -\n   0"));
    }
    @Test
    public void testRunLongDivision3000on3() {
        String result = division.runLongDivision("3000", "3");
        assertThat(result, equalTo(" 3000|3\n-3   |----\n -   |1000\n 0"));
    }
    @Test
    public void testRunLongDivision4000on3() {
        String result = division.runLongDivision("4000", "3");
        assertThat(result, equalTo(" 4000|3\n-3   |--------\n -   |1333.(1)\n 10\n -9\n  -\n  10\n  -9\n   -\n   10\n   -9\n    -\n    1"));
    }
    @Test
    public void testRunLongDivision612on2() {
        String result = division.runLongDivision("612", "2");
        assertThat(result, equalTo(" 612|2\n-6  |---\n -  |306\n  12\n -12\n   -\n   0"));
    }
    @Test
    public void testRunLongDivision30015on5() {
        LongDivision division = new LongDivision();
        String result = division.runLongDivision("30015", "5");
        assertThat(result, equalTo(" 30015|5\n-30   |----\n  -   |6003\n    15\n   -15\n     -\n     0"));
    }
    @Test
    public void testDivide() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        LongDivisionResult expectedLongDivisionResult = new LongDivisionResult();
        expectedLongDivisionResult.addIncompleteDividend(6);
        expectedLongDivisionResult.addIncompleteDividend(1);
        expectedLongDivisionResult.addIncompleteDividend(12);
        expectedLongDivisionResult.addIncompleteProduct(6);
        expectedLongDivisionResult.addIncompleteProduct(0);
        expectedLongDivisionResult.addIncompleteProduct(12);
        expectedLongDivisionResult.addRemainder(0);
        expectedLongDivisionResult.addRemainder(1);
        expectedLongDivisionResult.addRemainder(0);
        expectedLongDivisionResult.addIncompleteQuotient(3);
        expectedLongDivisionResult.addIncompleteQuotient(0);
        expectedLongDivisionResult.addIncompleteQuotient(6);
        longDivisionResult = division.divide("612", "2");
        assertEquals(longDivisionResult.getIncompleteDividends(), expectedLongDivisionResult.getIncompleteDividends());
        assertEquals(longDivisionResult.getIncompleteProducts(), expectedLongDivisionResult.getIncompleteProducts());
        assertEquals(longDivisionResult.getRemainders(), expectedLongDivisionResult.getRemainders());
        assertEquals(longDivisionResult.getIncompleteQuotients(), expectedLongDivisionResult.getIncompleteQuotients());
    }
    @Test
    public void testPrintLongDivision() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteDividend(6);
        longDivisionResult.addIncompleteDividend(1);
        longDivisionResult.addIncompleteDividend(12);
        longDivisionResult.addIncompleteProduct(6);
        longDivisionResult.addIncompleteProduct(0);
        longDivisionResult.addIncompleteProduct(12);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addRemainder(1);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addIncompleteQuotient(3);
        longDivisionResult.addIncompleteQuotient(0);
        longDivisionResult.addIncompleteQuotient(6);
        String result = division.printLongDivision("612", "2", longDivisionResult);
        assertThat(result, equalTo(" 612|2\n-6  |---\n -  |306\n  12\n -12\n   -\n   0"));
    }
    @Test
    public void testGetDividedDigit() {
        int result = division.getDividedDigit(2, "87654");
        assertThat(result, equalTo(6));
    }
    @Test
    public void testCalculateIncompleteProduct() {
        int result = division.calculateIncompleteProduct(6, 2);
        assertThat(result, equalTo(6));
    }
    @Test
    public void testCalculateIncompleteQuotient() {
        int result = division.calculateIncompleteQuotient(6, 2);
        assertThat(result, equalTo(3));
    }
    @Test
    public void testAppendIncomingDataToDivision() {
        String result = division.appendIncomingDataToDivision("612", "2");
        assertThat(result, equalTo(" 612|2\n"));
    }
    @Test
    public void testAppendQuotient() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteProduct(6);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addRemainder(1);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addIncompleteQuotient(3);
        longDivisionResult.addIncompleteQuotient(0);
        longDivisionResult.addIncompleteQuotient(6);
        String result = division.appendQuotient("612", 0, longDivisionResult);
        assertThat(result, equalTo("  |306"));
    }
    @Test
    public void testAppendLinesAboveQuotient() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteProduct(6);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addRemainder(1);
        longDivisionResult.addRemainder(0);
        longDivisionResult.addIncompleteQuotient(3);
        longDivisionResult.addIncompleteQuotient(0);
        longDivisionResult.addIncompleteQuotient(6);
        String result = division.appendLinesAboveQuotient("612", 0, longDivisionResult, 1);
        assertThat(result, equalTo("  |---"));
    }
    @Test
    public void testAppendIncompleteDividend() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteDividend(6);
        longDivisionResult.addIncompleteDividend(1);
        longDivisionResult.addIncompleteDividend(12);
        String result = division.appendIncompleteDividend(1, 0, longDivisionResult);
        assertThat(result, equalTo(" 6\n"));
    }
    @Test
    public void testAppendIncompleteProduct() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteProduct(6);
        longDivisionResult.addIncompleteProduct(0);
        longDivisionResult.addIncompleteProduct(12);
        String result = division.appendIncompleteProduct(1, 0, longDivisionResult);
        assertThat(result, equalTo("-6"));
    }
    @Test
    public void testAppendLastRemainder() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addRemainder(0);
        longDivisionResult.addRemainder(1);
        longDivisionResult.addRemainder(0);
        String result = division.appendLastRemainder(1, longDivisionResult);
        assertThat(result, equalTo(" 0"));
    }
    @Test
    public void testAppendWhiteSpace() {
        String result = division.appendWhiteSpace(3);
        assertThat(result, equalTo("   "));
    }
    @Test
    public void testAppendDevidedLines() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addRemainder(0);
        String result = division.appendDevidedLines(3, 0, longDivisionResult);
        assertThat(result, equalTo("   -"));
    }
    @Test
    public void testAppendLine() {
        String result = division.appendLine(5);
        assertThat(result, equalTo("-----"));
    }
    @Test
    public void testAppendList() {
        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.addIncompleteQuotient(3);
        longDivisionResult.addIncompleteQuotient(0);
        longDivisionResult.addIncompleteQuotient(6);
        String result = division.appendList(longDivisionResult.getIncompleteQuotients());
        assertThat(result, equalTo("306"));
    }
    @Test
    public void testCalculateNecessaryNumberOfSpacesBeforeDigits() {
        int result = division.calculateNecessaryNumberOfSpacesBeforeDigits(1, 12345, 123);
        assertThat(result, equalTo(3));
    }
    @Test
    public void testAddGetIncompleteDividends() {
        LongDivisionResult result = new LongDivisionResult();
        List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        result.addIncompleteDividend(1);
        result.addIncompleteDividend(2);
        result.addIncompleteDividend(3);
        assertEquals(expectedResult, result.getIncompleteDividends());
    }
    @Test
    public void testAddGetIncompleteProducts() {
        LongDivisionResult result = new LongDivisionResult();
        List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        result.addIncompleteProduct(1);
        result.addIncompleteProduct(2);
        result.addIncompleteProduct(3);
        assertEquals(expectedResult, result.getIncompleteProducts());
    }
    @Test
    public void testAddGetRemainders() {
        LongDivisionResult result = new LongDivisionResult();
        List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        result.addRemainder(1);
        result.addRemainder(2);
        result.addRemainder(3);
        assertEquals(expectedResult, result.getRemainders());
    }
    @Test
    public void testAddGetIncompleteQuotients() {
        LongDivisionResult result = new LongDivisionResult();
        List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        result.addIncompleteQuotient(1);
        result.addIncompleteQuotient(2);
        result.addIncompleteQuotient(3);
        assertEquals(expectedResult, result.getIncompleteQuotients());
    }
}
