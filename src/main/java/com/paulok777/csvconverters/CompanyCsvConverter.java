package com.paulok777.csvconverters;

import com.paulok777.models.Company;

import java.util.List;

import static java.util.Optional.ofNullable;

public class CompanyCsvConverter implements CsvConverter<Company> {

    public static final String CSV_HEADER = "id,name,users_count";

    @Override
    public String getCsvBody(Company company) {
        return company.getId() + "," + company.getName() + ","
                + ofNullable(company.getUsersCount()).map(String::valueOf).orElse("");
    }

    @Override
    public String getCsvForList(List<Company> companies) {
        StringBuilder csvCompanies = new StringBuilder(CSV_HEADER + System.lineSeparator());
        companies.forEach(company -> csvCompanies.append(getCsvBody(company)).append(System.lineSeparator()));
        return csvCompanies.toString();
    }
}
