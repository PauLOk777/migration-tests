package com.paulok777.db;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_MIN_IDLE = "db.min.idle";
    private static final String DB_MAX_IDLE = "db.max.idle";
    private static final String DB_MAX_OPEN_PREPARED_STATEMENT = "db.max.open.prepared.statement";
    private static final String DB_DRIVER_CLASS_NAME = "db.driver.class.name";

    private static final String DB_PROPERTIES = "src/main/resources/db.properties";
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try (FileReader reader = new FileReader(DB_PROPERTIES)) {
                        Properties p = new Properties();
                        p.load(reader);
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(p.getProperty(DB_URL));
                        ds.setUsername(p.getProperty(DB_USERNAME));
                        ds.setPassword(p.getProperty(DB_PASSWORD));
                        ds.setMinIdle(Integer.parseInt(p.getProperty(DB_MIN_IDLE)));
                        ds.setMaxIdle(Integer.parseInt(p.getProperty(DB_MAX_IDLE)));
                        ds.setMaxOpenPreparedStatements(Integer.parseInt(
                                p.getProperty(DB_MAX_OPEN_PREPARED_STATEMENT)));
                        ds.setDriverClassName(p.getProperty(DB_DRIVER_CLASS_NAME));
                        dataSource = ds;
                    } catch (Exception e) {
                        System.exit(-1);
                    }
                }
            }
        }
        return dataSource;
    }
}
