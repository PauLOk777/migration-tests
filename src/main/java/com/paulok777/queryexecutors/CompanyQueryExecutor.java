package com.paulok777.queryexecutors;

import com.paulok777.mappers.CompanyMapper;
import com.paulok777.models.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyQueryExecutor implements QueryExecutor<Company> {

    private CompanyMapper mapper = new CompanyMapper();

    @Override
    public List<Company> execute(Connection connection, String query) {
        List<Company> companies = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                companies.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return companies;
    }
}
