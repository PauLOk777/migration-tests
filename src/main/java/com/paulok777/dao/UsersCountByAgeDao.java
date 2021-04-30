package com.paulok777.dao;

import com.paulok777.mappers.UsersCountByAgeMapper;
import com.paulok777.models.UsersCountByAge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersCountByAgeDao {

    private static final String GROUP_BY_AGE_GET_COUNT_AND_AGE = "select count(u.age), u.age from users u " +
            "group by u.age order by count(u.age) desc";

    private UsersCountByAgeMapper mapper;
    private Connection connection;

    public UsersCountByAgeDao(Connection connection) {
        this.connection = connection;
        this.mapper = new UsersCountByAgeMapper();
    }

    public List<UsersCountByAge> getCountOfUsersWithAppropriateAge() {
        List<UsersCountByAge> usersCountByAges = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GROUP_BY_AGE_GET_COUNT_AND_AGE);
            while (rs.next()) {
                usersCountByAges.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return usersCountByAges;
    }
}
