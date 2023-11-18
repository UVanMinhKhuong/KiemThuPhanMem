package com.example.myapplication.enumeration;

import com.example.myapplication.activity.category.CategoryActivity;
import com.example.myapplication.activity.product.ProductActivity;
import com.example.myapplication.activity.role.RoleActivity;
import com.example.myapplication.activity.user.UserEditFragment;

public enum CardViewItemEnum {
    CATEGORY(CategoryActivity.class),
    PRODUCT(ProductActivity.class),
    ROLE(RoleActivity.class),
    USER(RoleActivity.class);

    private final Class<?> activity;

    CardViewItemEnum(Class<?> activity) {
        this.activity = activity;
    }

    public Class<?> getActivity() {
        return activity;
    }
}
