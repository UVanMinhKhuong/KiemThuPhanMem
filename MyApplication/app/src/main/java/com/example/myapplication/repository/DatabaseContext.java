package com.example.myapplication.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Shoes;

import java.util.ArrayList;
import java.util.List;

public class DatabaseContext extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shoes_store_v2.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        // Insert CART table
        db.execSQL("CREATE TABLE IF NOT EXISTS CART (ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_ID INTEGER NOT NULL, USER_ID INTEGER NOT NULL, QUANTITY INTEGER NOT NULL, SIZE TEXT NOT NULL, COLOR TEXT NOT NULL, CREATED_DATE DATETIME NOT NULL, FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID))");
        // Insert CATEGORY table
        db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORY (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
        // Insert COLOR table
        db.execSQL("CREATE TABLE IF NOT EXISTS COLOR (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, RGB TEXT)");
        // Insert DELIVERY_ORDER table
        db.execSQL("CREATE TABLE IF NOT EXISTS DELIVERY_ORDER (ID INTEGER PRIMARY KEY AUTOINCREMENT, CART_ID INTEGER NOT NULL, TOTAL_PRICE INTEGER NOT NULL, TOTAL_QUANTITY INTEGER NOT NULL, USER_ADDRESS_ID INTEGER NOT NULL, DELIVERY_DATE DATE, CREATED_DATE DATETIME NOT NULL, FOREIGN KEY (CART_ID) REFERENCES CART (ID), FOREIGN KEY (USER_ADDRESS_ID) REFERENCES USER_ADDRESS (ID))");
        // Insert DISTRICT table
        db.execSQL("CREATE TABLE IF NOT EXISTS DISTRICT (ID INTEGER PRIMARY KEY AUTOINCREMENT, PROVINCE_ID INTEGER NOT NULL, NAME TEXT NOT NULL, FOREIGN KEY (PROVINCE_ID) REFERENCES PROVINCE (ID))");
        // Insert product table
        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT (ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY_ID INTEGER NOT NULL, NAME TEXT NOT NULL, CODE VARCHAR(50), DESCRIPTION TEXT, PRICE INTEGER NOT NULL, STATUS INTEGER, CREATED_BY INTEGER NOT NULL, CREATED_DATE DATETIME NOT NULL, FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT_COLOR (ID INTEGER PRIMARY KEY AUTOINCREMENT, COLOR_ID INTEGER NOT NULL, PRODUCT_ID INTEGER NOT NULL, FOREIGN KEY (COLOR_ID) REFERENCES COLOR (ID), FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT_IMAGE (ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_ID INTEGER, IMAGE TEXT, FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PRODUCT_SIZE (ID INTEGER PRIMARY KEY AUTOINCREMENT, SIZE_ID INTEGER NOT NULL, PRODUCT_ID INTEGER NOT NULL, FOREIGN KEY (SIZE_ID) REFERENCES SIZE (ID), FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PROVINCE (ID INTEGER PRIMARY KEY, NAME TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS ROLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS SIZE (ID INTEGER PRIMARY KEY AUTOINCREMENT, SIZE INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS USER (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT NOT NULL, PASSWORD TEXT NOT NULL, FIRST_NAME TEXT, LAST_NAME TEXT, EMAIL TEXT, PHONE TEXT, JOINED_DATE DATE)");

        db.execSQL("CREATE TABLE IF NOT EXISTS USER_ADDRESS (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER NOT NULL, ADDRESS TEXT NOT NULL, PHONE BLOB NOT NULL, NOTE TEXT, FOREIGN KEY (USER_ID) REFERENCES USER (ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS USER_ROLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER NOT NULL, ROLE_ID INTEGER NOT NULL, FOREIGN KEY (USER_ID) REFERENCES USER (ID), FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        String sql = "ALTER TABLE USER ADD COLUMN PHONE TEXT";
//        System.out.println(oldVersion);
//        System.out.println(newVersion);
//        // If you need to add a column
//        if (newVersion > oldVersion) {
//            db.execSQL(sql);
//        }
    }
}
