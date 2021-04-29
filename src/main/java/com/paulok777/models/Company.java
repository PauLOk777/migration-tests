package com.paulok777.models;

public class Company {

    private Long id;
    private String name;
    private Long usersCount;

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUsersCount() {
        return usersCount;
    }
}
