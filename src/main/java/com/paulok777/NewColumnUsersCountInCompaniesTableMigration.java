package com.paulok777;

import com.paulok777.csv.CompanyCsvConverter;
import com.paulok777.csv.CsvConverter;
import com.paulok777.io.FileWriter;
import com.paulok777.models.Company;
import com.paulok777.db.ConnectionProvider;
import com.paulok777.queryexecutors.CompanyQueryExecutor;
import com.paulok777.queryexecutors.QueryExecutor;

import java.io.IOException;
import java.util.List;

public class NewColumnUsersCountInCompaniesTableMigration {

    private static final String QUERY = "select * from companies";
    public static final String PATH = "NewColumnUsersCountInCompaniesTableMigration.csv";

    public static void main(String[] args) throws IOException {
        QueryExecutor<Company> queryExecutor = new CompanyQueryExecutor();
        ConnectionProvider provider = new ConnectionProvider();
        CsvConverter<Company> csvConverter = new CompanyCsvConverter();
        List<Company> companies = queryExecutor.execute(provider.getConnection(), QUERY);
        FileWriter.write(csvConverter.getCsvForList(companies), PATH);
    }
}
