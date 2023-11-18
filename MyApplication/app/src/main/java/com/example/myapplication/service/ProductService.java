package com.example.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.constant.TableName;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.repository.DatabaseContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductService extends DatabaseContext {
    public ProductService(Context context) {
        super(context);
    }

    /**
     * Creates a new product
     *
     * @param productModel
     * @return
     */
    public boolean insert(ProductModel productModel) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("category_id", productModel.getCategoryId());
            contentValues.put("name", productModel.getName());
            contentValues.put("code", productModel.getCode());
            contentValues.put("description", productModel.getDescription());
            contentValues.put("price", productModel.getPrice());
            contentValues.put("status", productModel.isStatus());
            contentValues.put("created_by", productModel.getCreatedBy());
            contentValues.put("CREATED_DATE", productModel.getCreatedDate().toString());
            sqLiteDatabase.insert(TableName.PRODUCT, null, contentValues);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Edits category
     *
     * @return
     */
    public boolean update(ProductModel productModel) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("category_id", productModel.getCategoryId());
            contentValues.put("name", productModel.getName());
            contentValues.put("code", productModel.getCode());
            contentValues.put("description", productModel.getDescription());
            contentValues.put("price", productModel.getPrice());
            contentValues.put("status", productModel.isStatus());

            sqLiteDatabase.update(TableName.PRODUCT, contentValues, "id = ?", new String[]{String.valueOf(productModel.getId())});

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a product by the given id.
     *
     * @param id
     * @return
     */
    public boolean deleteById(long id) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {

            sqLiteDatabase.delete(TableName.PRODUCT, "id = ?", new String[]{String.valueOf(id)});

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all product
     *
     * @return The list of the category model
     */
    public List<ProductModel> findAll() {
        List<ProductModel> products = new ArrayList<>();
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            Cursor cursor = sqLiteDatabase.query(TableName.PRODUCT, new String[]{"id", "category_id", "name", "code", "description", "price", "status", "created_by", "created_date"}, null, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                int categoryId = cursor.getInt(1);
                String name = cursor.getString(2);
                String code = cursor.getString(3);
                String description = cursor.getString(4);
                long price = cursor.getLong(5);
                boolean status = Boolean.valueOf(String.valueOf(cursor.getInt(6)));
                long createdBy = cursor.getLong(7);

                products.add(new ProductModel(id, categoryId, name, code, description, price, status, createdBy));
                cursor.moveToNext();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return products;
        }
    }

    public List<ProductModel> findAllByName(String nameProduct) {
        List<ProductModel> products = new ArrayList<>();
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            Cursor cursor = sqLiteDatabase.query(TableName.PRODUCT, new String[]{"id", "category_id", "name", "code", "description", "price", "status", "created_by", "created_date"}, "name like '%?%'", new String[]{nameProduct}, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                int categoryId = cursor.getInt(1);
                String name = cursor.getString(2);
                String code = cursor.getString(3);
                String description = cursor.getString(4);
                long price = cursor.getLong(5);
                boolean status = Boolean.valueOf(String.valueOf(cursor.getInt(6)));
                long createdBy = cursor.getLong(7);

                products.add(new ProductModel(id, categoryId, name, code, description, price, status, createdBy));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return products;
        }
    }

    /**
     * Finds a product by the given id.
     *
     * @return The category model
     */
    public ProductModel findById(long productId) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            Cursor cursor = sqLiteDatabase.query(TableName.PRODUCT, new String[]{"id", "category_id", "name", "code", "description", "price", "status", "created_by", "created_date"}, "id = ?", new String[]{String.valueOf(productId)}, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                int categoryId = cursor.getInt(1);
                String name = cursor.getString(2);
                String code = cursor.getString(3);
                String description = cursor.getString(4);
                long price = cursor.getLong(5);
                boolean status = Boolean.valueOf(String.valueOf(cursor.getInt(6)));
                long createdBy = cursor.getLong(7);

                ProductModel product = new ProductModel(id, categoryId, name, code, description, price, status, createdBy);

                sqLiteDatabase.close();
                return product;
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
