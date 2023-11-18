package com.example.myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.RoleModel;
import com.example.myapplication.model.UserRoleModel;
import com.example.myapplication.repository.DatabaseContext;

import java.util.ArrayList;
import java.util.List;

public class UserRoleService extends DatabaseContext {

    private final String TABLE = "USER_ROLE";
    private final RoleService roleService;

    public UserRoleService(Context context) {
        super(context);
        roleService = new RoleService(context);
    }

    public boolean insert(int userId, int roleId) {
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("USER_ID", userId);
            contentValues.put("ROLE_ID", roleId);
            sqLiteDatabase.insert(TABLE, null, contentValues);

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public List<UserRoleModel> getByUserId(int userId) {
        List<UserRoleModel> userRoleModels = new ArrayList<>();
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,user_id, role_id from USER_ROLE WHERE user_id = ?", new String[]{String.valueOf(userId)});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                UserRoleModel userRoleModel = new UserRoleModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2)
                );

                userRoleModels.add(userRoleModel);

                cursor.moveToNext();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return userRoleModels;
    }

    public List<RoleModel> getRoleModelByUserId(int userId) {
        List<RoleModel> roleModels = new ArrayList<>();
        List<UserRoleModel> userRoleModels = getByUserId(userId);
       if(userRoleModels != null && !userRoleModels.isEmpty()){

           for (UserRoleModel userRole: userRoleModels) {
               RoleModel roleModel = roleService.findById(userRole.getRoleId());
               roleModels.add(roleModel);
           }

       }

        return roleModels;
    }

    public List<UserRoleModel> getByRoleId(int roleId) {
        List<UserRoleModel> userRoleModels = new ArrayList<>();
        try (SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()) {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id,user_id, role_id from USER_ROLE WHERE role_id = ?", new String[]{String.valueOf(roleId)});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                UserRoleModel userRoleModel = new UserRoleModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2)
                );
                userRoleModels
                        .add(userRoleModel);

                cursor.moveToNext();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userRoleModels;
    }
}
