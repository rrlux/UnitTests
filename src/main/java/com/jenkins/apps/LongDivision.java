package com.jenkins.apps;

import java.util.List;
public class LongDivision {
    private final String NEW_LINE = "\n";
    private final String SPACE = " ";
    private final String VERTICAL_LINE = "|";
    private final String HORIZONTAL_LINE = "-";
    public String runLongDivision(String dividend, String divisor) {
        LongDivision division = new LongDivision();
        String printedResult = division.printLongDivision(dividend, divisor, divide(dividend, divisor));
        return printedResult;
    }
    public void runAndPrintLongDivision(String dividend, String divisor) {
        System.out.println(runLongDivision(dividend, divisor));
    }
    LongDivisionResult divide(String dividend, String divisor) {
        LongDivisionResult result = new LongDivisionResult();
        int remainderDigit = 0;
        for (int i = 0; i < dividend.length(); i++) {
            int number = remainderDigit + getDividedDigit(i, dividend);
            int intDivisor = Integer.valueOf(divisor);
            if (number >= intDivisor) {
                result.addIncompleteDividend(number);
                int incompleteQuotientDigit = calculateIncompleteProduct(number, intDivisor);
                result.addIncompleteQuotient(calculateIncompleteQuotient(number, intDivisor));
                result.addIncompleteProduct(incompleteQuotientDigit);
                remainderDigit = (number - incompleteQuotientDigit) * 10;
                result.addRemainder(number - incompleteQuotientDigit);
            }
            if (number < intDivisor) {
                remainderDigit = number * 10;
                if (!result.getIncompleteDividends().isEmpty()) {
                    result.addRemainder(number - calculateIncompleteProduct(number, intDivisor));
                    result.addIncompleteQuotient(calculateIncompleteQuotient(number, intDivisor));
                    result.addIncompleteDividend(number);
                    result.addIncompleteProduct(0);
                }
            }
        }
        return result;
    }
    String printLongDivision(String dividend, String divisor, LongDivisionResult result) {
        StringBuilder printedLongDivision = new StringBuilder();
        int numberOfSpacesBeforeSymbol = 1;
        int numberOfSpacesBeforeLastRemainder = 1;
        printedLongDivision.append(appendIncomingDataToDivision(dividend, divisor));
        printedLongDivision.append(appendIncompleteProduct(numberOfSpacesBeforeSymbol, 0, result));
        printedLongDivision.append(appendLinesAboveQuotient(dividend, 0, result, numberOfSpacesBeforeSymbol));
        numberOfSpacesBeforeSymbol = calculateNecessaryNumberOfSpacesBeforeDigits(numberOfSpacesBeforeSymbol, result.getIncompleteProducts().get(0), result.getRemainders().get(0));
        printedLongDivision.append(NEW_LINE);
        printedLongDivision.append(appendDevidedLines(numberOfSpacesBeforeSymbol, 0, result));
        printedLongDivision.append(appendQuotient(dividend, 0, result));
        printedLongDivision.append(NEW_LINE);
        for (int i = 1; i < result.getIncompleteProducts().size(); i++) {
            if (result.getRemainders().get(i - 1) == 0) {
                numberOfSpacesBeforeSymbol++;
            }
            if (result.getIncompleteProducts().get(i) != 0) {
                printedLongDivision.append(appendIncompleteDividend(numberOfSpacesBeforeSymbol, i, result));
            }
            numberOfSpacesBeforeSymbol = calculateNecessaryNumberOfSpacesBeforeDigits(numberOfSpacesBeforeSymbol, result.getIncompleteDividends().get(i), result.getIncompleteProducts().get(i));
            if (result.getIncompleteProducts().get(i) != 0) {
                printedLongDivision.append(appendIncompleteProduct(numberOfSpacesBeforeSymbol, i, result));
                printedLongDivision.append(NEW_LINE);
            }
            numberOfSpacesBeforeSymbol = calculateNecessaryNumberOfSpacesBeforeDigits(numberOfSpacesBeforeSymbol, result.getIncompleteProducts().get(i), result.getRemainders().get(i));
            if (result.getIncompleteProducts().get(i) != 0) {
                printedLongDivision.append(appendDevidedLines(numberOfSpacesBeforeSymbol, i, result));
                numberOfSpacesBeforeLastRemainder = numberOfSpacesBeforeSymbol;
                printedLongDivision.append(NEW_LINE);
            }
        }
        printedLongDivision.append(appendLastRemainder(numberOfSpacesBeforeLastRemainder, result));
        return printedLongDivision.toString();
    }
    int getDividedDigit(int index, String dividend) {
        return Character.getNumericValue(dividend.charAt(index));
    }
    int calculateIncompleteProduct(int digit, int divisor) {
        return calculateIncompleteQuotient(digit, divisor) * divisor;
    }
    int calculateIncompleteQuotient(int digit, int divisor) {
        int incompleteQuotient = 0;
        while (digit >= 0) {
            digit -= divisor;
            incompleteQuotient++;
        }
        incompleteQuotient--;
        return incompleteQuotient;
    }
    String appendIncomingDataToDivision(String dividend, String divisor) {
        StringBuilder incomingDataToDivision = new StringBuilder();
        incomingDataToDivision.append(appendWhiteSpace(1));
        incomingDataToDivision.append(dividend);
        incomingDataToDivision.append(VERTICAL_LINE);
        incomingDataToDivision.append(divisor);
        incomingDataToDivision.append(NEW_LINE);
        return incomingDataToDivision.toString();
    }
    String appendQuotient(String dividend, int i, LongDivisionResult result) {
        StringBuilder quotient = new StringBuilder();
        quotient.append(appendWhiteSpace(dividend.length() - Integer.toString(result.getIncompleteProducts().get(i)).length()));
        quotient.append(VERTICAL_LINE);
        quotient.append(appendList(result.getIncompleteQuotients()));
        if (result.getRemainders().get(result.getRemainders().size() - 1) != 0) {
            quotient.append(".(");
            quotient.append(result.getRemainders().get(result.getRemainders().size() - 1));
            quotient.append(")");
        }
        return quotient.toString();
    }
    String appendLinesAboveQuotient(String dividend, int i, LongDivisionResult result, int numberOfSpacesBeforeDigits) {
        StringBuilder linesAboveQuotient = new StringBuilder();
        linesAboveQuotient.append(appendWhiteSpace(dividend.length() - Integer.toString(result.getIncompleteProducts().get(i)).length() - (numberOfSpacesBeforeDigits - 1)));
        linesAboveQuotient.append(VERTICAL_LINE);
        linesAboveQuotient.append(appendLine(result.getIncompleteQuotients().size()));
        if (result.getRemainders().get(result.getRemainders().size() - 1) != 0) {
            linesAboveQuotient.append(appendLine(Integer.toString(result.getRemainders().get(result.getRemainders().size() - 1)).length() + 3));
        }
        return linesAboveQuotient.toString();
    }
    String appendIncompleteDividend(int numberOfSpacesBeforeSymbol, int i, LongDivisionResult result) {
        StringBuilder incompleteDividend = new StringBuilder();
        incompleteDividend.append(appendWhiteSpace(numberOfSpacesBeforeSymbol));
        incompleteDividend.append(result.getIncompleteDividends().get(i));
        incompleteDividend.append(NEW_LINE);
        return incompleteDividend.toString();
    }
    String appendIncompleteProduct(int numberOfSpacesBeforeSymbol, int i, LongDivisionResult result) {
        StringBuilder incompleteProduct = new StringBuilder();
        incompleteProduct.append(appendWhiteSpace(numberOfSpacesBeforeSymbol - 1));
        incompleteProduct.append(appendLine(1));
        incompleteProduct.append(result.getIncompleteProducts().get(i));
        return incompleteProduct.toString();
    }
    String appendLastRemainder(int numberOfSpacesBeforeSymbol, LongDivisionResult result) {
        String lastRemainder = appendWhiteSpace(numberOfSpacesBeforeSymbol);
        lastRemainder += result.getRemainders().get(result.getRemainders().size() - 1);
        return lastRemainder;
    }
    String appendWhiteSpace(int numberOfSpacesBeforeSymbol) {
        StringBuilder whiteSpaces = new StringBuilder();
        for (int i = 0; i < numberOfSpacesBeforeSymbol; i++) {
            whiteSpaces.append(SPACE);
        }
        return whiteSpaces.toString();
    }
    String appendDevidedLines(int numberOfSpacesBeforeSymbol, int i, LongDivisionResult result) {
        String devidedLines = appendWhiteSpace(numberOfSpacesBeforeSymbol);
        devidedLines += appendLine(Integer.toString(result.getRemainders().get(i)).length());
        return devidedLines;
    }
    String appendLine(int quantity) {
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            lines.append(HORIZONTAL_LINE);
        }
        return lines.toString();
    }
    String appendList(List list) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            listString.append(list.get(i));
        }
        return listString.toString();
    }
    int calculateNecessaryNumberOfSpacesBeforeDigits(int numberOfSpacesBeforeDigits, int argument1, int argument2) {
        numberOfSpacesBeforeDigits += (Integer.toString(argument1).length() - Integer.toString(argument2).length());
        return numberOfSpacesBeforeDigits;
    }
}
