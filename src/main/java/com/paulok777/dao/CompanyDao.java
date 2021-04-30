package com.paulok777.dao;

import com.paulok777.mappers.CompanyMapper;
import com.paulok777.models.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {

    private static final String FIND_ALL = "select * from companies";

    private CompanyMapper mapper;
    private Connection connection;

    public CompanyDao(Connection connection) {
        this.connection = connection;
        this.mapper = new CompanyMapper();
    }

    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next()) {
                companies.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return companies;
    }
}
