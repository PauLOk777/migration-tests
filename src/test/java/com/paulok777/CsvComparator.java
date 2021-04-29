package com.paulok777;

import com.paulok777.io.FileReader;
import com.paulok777.io.FileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class CsvComparator {

    private static final String ERROR_MESSAGE = "Comparison failed. See more details in %s";
    private static final String DIFFERENT_COLUMNS = "You have different columns in your CSV files.";
    private static final String DIFFERENT_NUMBER_OF_ROWS = "You have different number of rows in your CSV files.";
    private static final String INCORRECT_FORMAT = "%s file has incorrect format in a row %d";
    private static final String DIFFERENCES_IN_ROW_AND_COLUMN = "Difference in a %d row and in %s column. " +
            "Expected: <%s>, but was: <%s>";

    public void compare(String expectedFilePath, String actualFilePath, String differenceFilePath)
            throws IOException {
        List<String> expectedList = FileReader.read(expectedFilePath);
        List<String> actualList = FileReader.read(actualFilePath);

        Map<Integer, String> expected = IntStream.range(1, expectedList.size() + 1)
                .boxed()
                .collect(toMap(i -> i, i -> expectedList.get(i - 1)));
        Map<Integer, String> actual = IntStream.range(1, actualList.size() + 1)
                .boxed()
                .collect(toMap(i -> i, i -> actualList.get(i - 1)));

        checkForColumnsNumber(expected, actual, differenceFilePath);
        checkForRowsNumber(expected, actual, differenceFilePath);

        StringBuilder differences = new StringBuilder();
        String[] headerParts = expected.get(1).split(",", -1);

        for (int i = 2; i <= expected.size(); i++) {
            String[] expectedParts = expected.get(i).split(",", -1);
            String[] actualParts = actual.get(i).split(",", -1);

            checkForRowCommasNumber(expectedParts.length, headerParts.length, i,
                    expectedFilePath, differenceFilePath);
            checkForRowCommasNumber(actualParts.length, headerParts.length, i,
                    actualFilePath, differenceFilePath);

            for (int j = 0; j < expectedParts.length; j++) {
                if (!expectedParts[j].equals(actualParts[j])) {
                    differences.append(String.format(DIFFERENCES_IN_ROW_AND_COLUMN, i, headerParts[j],
                            expectedParts[j], actualParts[j])).append(System.lineSeparator());
                }
            }
        }

        if (differences.length() != 0) {
            FileWriter.write(differences.toString(), differenceFilePath);
            throw new RuntimeException(String.format(ERROR_MESSAGE, differenceFilePath));
        }
    }

    private void checkForColumnsNumber(Map<Integer, String> expected, Map<Integer, String> actual,
                                       String differenceFilePath) throws IOException {
        if (!expected.get(1).equals(actual.get(1))) {
            FileWriter.write(DIFFERENT_COLUMNS, differenceFilePath);
            throw new RuntimeException(String.format(ERROR_MESSAGE, differenceFilePath));
        }
    }

    private void checkForRowsNumber(Map<Integer, String> expected, Map<Integer, String> actual,
                                             String differenceFilePath) throws IOException {
        if (expected.size() != actual.size()) {
            FileWriter.write(DIFFERENT_NUMBER_OF_ROWS, differenceFilePath);
            throw new RuntimeException(String.format(ERROR_MESSAGE, differenceFilePath));
        }
    }

    private void checkForRowCommasNumber(int rowLength, int headerLength, int row, String rowFilePath,
                                                 String differenceFilePath) throws IOException {
        if (rowLength != headerLength) {
            FileWriter.write(String.format(INCORRECT_FORMAT, rowFilePath, row), differenceFilePath);
            throw new RuntimeException(String.format(ERROR_MESSAGE, differenceFilePath));
        }
    }
}
