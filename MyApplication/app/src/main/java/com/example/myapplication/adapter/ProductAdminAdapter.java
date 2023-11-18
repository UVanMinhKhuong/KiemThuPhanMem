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
import com.example.myapplication.model.ProductModel;

import java.util.ArrayList;

public class ProductAdminAdapter extends ArrayAdapter<ProductModel> {
    private ArrayList<ProductModel> products;
    private Activity activity;
    private int layoutId;

    public ProductAdminAdapter(Activity activity, int layout, ArrayList<ProductModel> products) {
        super(activity, layout, products);
        this.activity = activity;
        this.layoutId = layout;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layoutId, null);

        ProductModel productModel = products.get(position);

        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(productModel.getName());
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        // create description
        String description = String.format("Id: %s - Code: %s - Created Date: %s",
                productModel.getId(),
                productModel.getCode(),
                productModel.getCreatedDate() != null ? productModel.getCreatedDate().toString() : "unknown"
        );
        txtDescription.setText(description);

        return convertView;
    }
}
