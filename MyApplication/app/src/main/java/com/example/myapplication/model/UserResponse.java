package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class UserResponse extends UserModel{

    private List<RoleModel> roles;

    public void setRoles(List<RoleModel> roles){
        this.roles = new ArrayList<>(roles);
    }

    public List<RoleModel> getRoles(){
        return this.roles;
    }

    public UserResponse(UserModel userModel, List<RoleModel> roleModels){
        super(userModel.id, userModel.username, userModel.email, userModel.firstName, userModel.lastName, userModel.phone);
        this.roles =  new ArrayList<>(roleModels);
    }
    public String makeTextViewRoleName(){
        if(roles != null && !roles.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for (RoleModel role: roles) {
                stringBuilder.append(role.getName());
            }
            return stringBuilder.toString();
        }
        return "Chưa Có";

    }

}
