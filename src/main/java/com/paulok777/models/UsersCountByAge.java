package com.paulok777.models;

public class UsersCountByAge {

    private Long count;
    private Integer age;

    public UsersCountByAge(long count, int age) {
        this.count = count;
        this.age = age;
    }

    public Long getCount() {
        return count;
    }

    public Integer getAge() {
        return age;
    }
}
