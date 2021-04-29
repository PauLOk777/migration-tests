package com.paulok777.queryexecutors;

import com.paulok777.mappers.UsersCountByAgeMapper;
import com.paulok777.models.UsersCountByAge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersCountByAgeQueryExecutor implements QueryExecutor<UsersCountByAge> {

    private UsersCountByAgeMapper mapper = new UsersCountByAgeMapper();

    @Override
    public List<UsersCountByAge> execute(Connection connection, String query) {
        List<UsersCountByAge> usersCountByAges = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                usersCountByAges.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return usersCountByAges;
    }
}
