package com.example.myapplication.model;

import android.os.Build;

import com.example.myapplication.utility.BCryptUtil;

import java.time.LocalDateTime;

public class UserModel {
    public int id;
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String joinedDate;

    public UserModel(){}

    public UserModel(String username, String password, String email, String firstName, String lastName, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.joinedDate = LocalDateTime.now().toString();
        }
    }
    public UserModel(int id, String username, String email, String firstName, String lastName, String phone) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.joinedDate = LocalDateTime.now().toString();
        }
    }

    public UserModel(String username, String email, String firstName, String lastName, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public void setPassword(String password){
        this.password = BCryptUtil.decodePassword(password);
    }

    public String getFullName(){
        if(firstName != null && lastName != null){
            return firstName + " " + lastName;
        } else {
            if(firstName != null && lastName == null){
                return firstName;
            } else if (firstName == null && lastName != null){
                return lastName;
            } else {
                return "Không xác định";
            }
        }
    }
}