package com.paulok777;

import com.paulok777.csvconverters.CsvConverter;
import com.paulok777.csvconverters.UsersCountByAgeCsvConverter;
import com.paulok777.dao.DaoFactory;
import com.paulok777.dao.UsersCountByAgeDao;
import com.paulok777.io.FileWriter;
import com.paulok777.models.UsersCountByAge;

import java.io.IOException;
import java.util.List;

public class NewTableNumberOfUsersWithAgeMigration {

    public static final String PATH = "NewTableNumberOfUsersWithAgeMigration.csv";

    public static void main(String[] args) throws IOException {
        DaoFactory daoFactory = new DaoFactory();
        UsersCountByAgeDao dao = daoFactory.createUsersCountByAgeDao();
        CsvConverter<UsersCountByAge> csvConverter = new UsersCountByAgeCsvConverter();
        List<UsersCountByAge> usersCountByAges = dao.getCountOfUsersWithAppropriateAge();
        FileWriter.write(csvConverter.getCsvForList(usersCountByAges), PATH);
    }
}
