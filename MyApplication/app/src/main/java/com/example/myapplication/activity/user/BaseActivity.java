package com.example.myapplication.activity.user;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.constant.SharedPreference;
import com.example.myapplication.fragment.AdminFragmentActivity;

public class BaseActivity extends AppCompatActivity {

    protected  void authorizedUser(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreference.USER_SESSION, MODE_PRIVATE);
        // Get the user current login by username
        String usernameSession = sharedPreferences.getString("Username", null);
        if (usernameSession == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AdminFragmentActivity.class);
            startActivity(intent);
        }
    }
}
