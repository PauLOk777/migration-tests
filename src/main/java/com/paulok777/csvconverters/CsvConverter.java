package com.paulok777.csvconverters;

import java.util.List;

public interface CsvConverter<T> {

    String getCsvBody(T t);

    String getCsvForList(List<T> list);
}
