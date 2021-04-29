package com.paulok777.mappers;

import com.paulok777.models.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements Mapper<Company> {

    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Company map(ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getLong(ID), resultSet.getString(NAME));
    }
}
