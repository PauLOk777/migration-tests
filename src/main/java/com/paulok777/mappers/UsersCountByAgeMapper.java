package com.paulok777.mappers;

import com.paulok777.models.UsersCountByAge;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCountByAgeMapper implements Mapper<UsersCountByAge> {

    @Override
    public UsersCountByAge map(ResultSet resultSet) throws SQLException {
        return new UsersCountByAge(resultSet.getLong(1), resultSet.getInt(2));
    }
}
