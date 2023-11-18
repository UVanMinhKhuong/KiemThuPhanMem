package com.example.myapplication.adapter;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Shoes;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<ProductModel> productList;



    public ProductAdapter(Context context, int layout, ArrayList<ProductModel> listProduct){
        myContext = context;
        myLayout = layout;
        productList = listProduct;

    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout,null);

        // ánh xạ và gán giá trị
        TextView txtName = (TextView) view.findViewById(R.id.textViewName);
        txtName.setText(productList.get(i).getName());
        TextView txtPrice = (TextView) view.findViewById(R.id.textViewPrice);
        txtPrice.setText(productList.get(i).getPrice() + " VNĐ");

        //ImageView img = (ImageView) view.findViewById(R.id.imageViewImage);
        //img.setImageResource(productList.get(i).Image);

        return view;
    }


}
