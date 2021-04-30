package com.paulok777.csvconverters;

import com.paulok777.models.UsersCountByAge;

import java.util.List;

public class UsersCountByAgeCsvConverter implements CsvConverter<UsersCountByAge> {

    public static final String CSV_HEADER = "age,count";

    @Override
    public String getCsvBody(UsersCountByAge usersCountByAge) {
        return usersCountByAge.getAge() + "," + usersCountByAge.getCount();
    }

    @Override
    public String getCsvForList(List<UsersCountByAge> usersCountByAges) {
        StringBuilder csvCompanies = new StringBuilder(CSV_HEADER + System.lineSeparator());
        usersCountByAges.forEach(usersCountByAge ->
                csvCompanies.append(getCsvBody(usersCountByAge)).append(System.lineSeparator()));
        return csvCompanies.toString();
    }
}
