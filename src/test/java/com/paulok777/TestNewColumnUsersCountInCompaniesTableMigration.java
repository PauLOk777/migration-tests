package com.paulok777;

import java.io.IOException;

public class TestNewColumnUsersCountInCompaniesTableMigration {

    private static final String EXPECTATION_FILE_PATH = "ExcpectationsForNewColumnUsersCountMigration.csv";
    private static final String DIFFERENCES_FILE_PATH = "DifferencesForNewColumnUsersCountMigration.txt";
    private static final String SUCCESS = "Congratulations. No differences!";
    private static final String IO_EXCEPTION_MESSAGE = "Wrong path to files";

    public static void main(String[] args) {
        CsvComparator csvComparator = new CsvComparator();

        try {
            NewColumnUsersCountInCompaniesTableMigration.main(new String[0]);
            csvComparator.compare(EXPECTATION_FILE_PATH,
                    NewColumnUsersCountInCompaniesTableMigration.PATH, DIFFERENCES_FILE_PATH);
        } catch (IOException e) {
            System.err.println(IO_EXCEPTION_MESSAGE + System.lineSeparator() + e.getMessage());
        }

        System.out.println(SUCCESS);
    }
}
