package com.paulok777.dao;

import com.paulok777.db.ConnectionPoolHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public CompanyDao createCompanyDao() {
        return new CompanyDao(getConnection());
    }

    public UsersCountByAgeDao createUsersCountByAgeDao() {
        return new UsersCountByAgeDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
