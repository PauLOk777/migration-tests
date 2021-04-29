package com.paulok777.queryexecutors;

import java.sql.Connection;
import java.util.List;

public interface QueryExecutor<T> {

    List<T> execute(Connection connection, String query);
}
