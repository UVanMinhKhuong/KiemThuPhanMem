package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<CategoryModel> {
    private ArrayList<CategoryModel> categories;
    private Activity activity;
    private int layoutId;

    public CategoryAdapter(Activity activity, int layout, ArrayList<CategoryModel> categories) {
        super(activity, layout, categories);
        this.activity = activity;
        this.layoutId = layout;
        this.categories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layoutId, null);

        CategoryModel categoryModel = categories.get(position);
        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(categoryModel.name);

        TextView txtQuantityProduct = convertView.findViewById(R.id.txtId);
        txtQuantityProduct.setText(String.valueOf(5));
        return convertView;
    }
}
