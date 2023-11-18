package com.example.myapplication.model;


public class RoleModel {
    private int id;
    private String name;

    public RoleModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
