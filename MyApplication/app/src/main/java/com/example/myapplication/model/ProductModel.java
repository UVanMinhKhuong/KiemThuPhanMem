package com.example.myapplication.model;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductModel implements Serializable {
    private long id;
    private String name;
    private String code;
    private String description;
    private long price;
    private boolean status;
    private long createdBy;
    private LocalDateTime createdDate;
    private int categoryId;

    public ProductModel(long id, String name, String code, String description, long price, boolean status,
                        long createdBy, LocalDateTime createdDate, int categoryId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.categoryId = categoryId;
    }

    public ProductModel(String name, String code, String description, long price, boolean status, int categoryId) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdBy = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.createdDate = LocalDateTime.now();
        }
        this.categoryId = categoryId;
    }

    public ProductModel(long id,int categoryId,String name, String code, String description, long price, boolean status, long createdBy) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
