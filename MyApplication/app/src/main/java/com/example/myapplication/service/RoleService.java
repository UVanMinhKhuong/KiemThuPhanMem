package com.example.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.constant.TableName;
import com.example.myapplication.model.RoleModel;
import com.example.myapplication.repository.DatabaseContext;

import java.util.ArrayList;
import java.util.List;

public class RoleService extends DatabaseContext {

    public RoleService(Context context) {
        super(context);
    }

    public boolean insert(String name) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", name);


            sqLiteDatabase.insert(TableName.ROLE, null, contentValues);
            sqLiteDatabase.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void update(int id, String name) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", name);


            sqLiteDatabase.update(TableName.ROLE, contentValues, "id = ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public RoleModel findById(int id) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {

            Cursor cursor = sqLiteDatabase.query(TableName.ROLE, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();


                return new RoleModel(
                        cursor.getInt(0),
                        cursor.getString(1));


        } catch (Exception exception) {
            exception.printStackTrace();

        }

        return null;
    }

    public List<RoleModel> findAll() {
        List<RoleModel> roleModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TableName.ROLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            roleModels.add(new RoleModel(
                    cursor.getInt(0),
                    cursor.getString(1)
            ));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return roleModels;

    }

    public boolean deleteById(int id) {

        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {

            sqLiteDatabase.delete(TableName.ROLE, "id = ?", new String[]{String.valueOf(id)});

            return true;

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return false;
    }
}
