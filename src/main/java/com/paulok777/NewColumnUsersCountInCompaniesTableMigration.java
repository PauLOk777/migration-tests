package com.paulok777;

import com.paulok777.csvconverters.CompanyCsvConverter;
import com.paulok777.csvconverters.CsvConverter;
import com.paulok777.dao.DaoFactory;
import com.paulok777.io.FileWriter;
import com.paulok777.models.Company;
import com.paulok777.dao.CompanyDao;

import java.io.IOException;
import java.util.List;

public class NewColumnUsersCountInCompaniesTableMigration {

    public static final String PATH = "NewColumnUsersCountInCompaniesTableMigration.csv";

    public static void main(String[] args) throws IOException {
        DaoFactory daoFactory = new DaoFactory();
        CompanyDao companyDao = daoFactory.createCompanyDao();
        CsvConverter<Company> csvConverter = new CompanyCsvConverter();
        List<Company> companies = companyDao.findAll();
        FileWriter.write(csvConverter.getCsvForList(companies), PATH);
    }
}
