package com.paulok777;

import com.paulok777.csv.CsvConverter;
import com.paulok777.csv.UsersCountByAgeCsvConverter;
import com.paulok777.io.FileWriter;
import com.paulok777.db.ConnectionProvider;
import com.paulok777.models.UsersCountByAge;
import com.paulok777.queryexecutors.QueryExecutor;
import com.paulok777.queryexecutors.UsersCountByAgeQueryExecutor;

import java.io.IOException;
import java.util.List;

public class NewTableNumberOfUsersWithAgeMigration {

    private static final String QUERY = "select count(u.age), u.age from users u " +
            "group by u.age order by count(u.age) desc";
    private static final String FILENAME = "NewTableNumberOfUsersWithAgeMigration.csv";

    public static void main(String[] args) throws IOException {
        QueryExecutor<UsersCountByAge> queryExecutor = new UsersCountByAgeQueryExecutor();
        ConnectionProvider provider = new ConnectionProvider();
        CsvConverter<UsersCountByAge> csvConverter = new UsersCountByAgeCsvConverter();
        List<UsersCountByAge> usersCountByAges = queryExecutor.execute(provider.getConnection(), QUERY);
        FileWriter.write(csvConverter.getCsvForList(usersCountByAges), FILENAME);
    }
}
