package com.example.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.RoleModel;
import com.example.myapplication.model.UserModel;
import com.example.myapplication.model.UserResponse;
import com.example.myapplication.repository.DatabaseContext;

import java.util.ArrayList;
import java.util.List;

public class UserService extends DatabaseContext {
    private UserRoleService userRoleService;

    public UserService(Context context) {
        super(context);
        this.userRoleService = new UserRoleService(context);
    }

    public long insert(UserModel user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", user.username);
        contentValues.put("PASSWORD", user.password);
        contentValues.put("FIRST_NAME", user.firstName);
        contentValues.put("LAST_NAME", user.lastName);
        contentValues.put("EMAIL", user.email);
        contentValues.put("PHONE", user.phone);
        contentValues.put("JOINED_DATE", user.joinedDate);

        long userId = sqLiteDatabase.insert("USER", null, contentValues);
        sqLiteDatabase.close();
        return userId;
    }

    public UserModel findByUserId(long userId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT username, email, first_name, last_name, phone from USER WHERE ID = ?", new String[]{String.valueOf(userId)});
        UserModel user = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String username = cursor.getString(0);
            String email = cursor.getString(1);
            String firstName = cursor.getString(2);
            String lastName = cursor.getString(3);
            String phone = cursor.getString(4);
            user = new UserModel(username, email, firstName, lastName, phone);
        }
        cursor.close();

        return user;
    }

    public UserModel findByUsername(String username) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT username, password, email, first_name, last_name, phone from USER WHERE USERNAME = ?", new String[]{username});
        UserModel user = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            username = cursor.getString(0);
            String password = cursor.getString(1);
            String email = cursor.getString(2);
            String firstName = cursor.getString(3);
            String lastName = cursor.getString(4);
            String phone = cursor.getString(5);
            user = new UserModel(username, password, email, firstName, lastName, phone);
            break;
        }
        cursor.close();

        return user;
    }

    /**
     * Checks exists with the user name
     *
     * @param username The username of the user model
     * @return Return true if the username exists.
     */
    public boolean isExistsUsername(String username) {
        if (findByUsername(username) != null)
            return true;
        return false;
    }

    public List<UserModel> findAll() {
        List<UserModel> userModels = new ArrayList<>();
        try (SQLiteDatabase sqLiteDatabase = this.getReadableDatabase()) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id, username, email, first_name, last_name, phone from USER", null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String email = cursor.getString(2);
                String firstName = cursor.getString(3);
                String lastName = cursor.getString(4);
                String phone = cursor.getString(5);

                userModels.add(new UserModel(id, username, email, firstName, lastName, phone));

                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userModels;
    }

    public List<UserResponse> findUserResponseAll() {
        List<UserModel> userModels = findAll();

        List<UserResponse> userResponses = new ArrayList<>();

        for (UserModel user : userModels) {
            List<RoleModel> roleModels = userRoleService.getRoleModelByUserId(user.id);
            userResponses.add(new UserResponse(user, roleModels));
        }

        return userResponses;

    }
}
