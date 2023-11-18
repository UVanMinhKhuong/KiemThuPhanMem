package com.example.myapplication.model;

import java.io.Serializable;


public class UserRoleModel implements Serializable {
    private int id;
    private int roleId;
    private int userId;

    public UserRoleModel(int id, int userId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getUserId() {
        return userId;
    }
}
