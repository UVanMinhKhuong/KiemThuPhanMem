package com.example.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.constant.TableName;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.repository.DatabaseContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryService extends DatabaseContext {
    public CategoryService(Context context) {
        super(context);
    }

    /**
     * Creates a new category
     *
     * @param categoryModel
     * @return
     */
    public boolean insert(CategoryModel categoryModel) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", categoryModel.name);
            sqLiteDatabase.insert(TableName.CATEGORY, null, contentValues);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Edits category
     *
     * @param categoryModel
     * @return
     */
    public boolean update(CategoryModel categoryModel) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", categoryModel.name);

            sqLiteDatabase.update(TableName.CATEGORY, contentValues, "id = ?", new String[]{String.valueOf(categoryModel.id)});

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a category by the given id.
     *
     * @param id
     * @return
     */
    public boolean deleteById(int id) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {

            sqLiteDatabase.delete(TableName.CATEGORY, "id = ?", new String[]{String.valueOf(id)});

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all category
     *
     * @return The list of the category model
     */
    public List<CategoryModel> findAll() {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            List<CategoryModel> categories = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.query(TableName.CATEGORY, null, null, null, null, null, "id desc", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                categories.add(new CategoryModel(id, name));
                cursor.moveToNext();
            }
            return categories;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Finds a category by the given id.
     *
     * @return The category model
     */
    public CategoryModel findById(int categoryId) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            CategoryModel category = new CategoryModel();
            Cursor cursor = sqLiteDatabase.query(TableName.CATEGORY, null, "id = ?", new String[]{String.valueOf(categoryId)}, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                category.id = id;
                category.name = name;
                break;
            }
            return category;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
