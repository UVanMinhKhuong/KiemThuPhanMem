package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.user.LoginActivity;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.service.ProductService;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    ListView lvShoes;
    ArrayList<ProductModel> productList  = new ArrayList<ProductModel>();

    ProductService productService;

    Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        productService = new ProductService(getActivity());
        productList.addAll(productService.findAll());
        lvShoes = (ListView) view.findViewById(R.id.lvShoes);
        btnAdd = (Button) view.findViewById(R.id.btnAddToCart);
        ProductAdapter anAdapter = new ProductAdapter(
                getActivity(),
                R.layout.product_custom,
                productList
        );

        lvShoes.setAdapter(anAdapter);
        anAdapter.notifyDataSetChanged();


        lvShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                btnAdd.setVisibility(View.VISIBLE);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
        lvShoes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                btnAdd.setVisibility(View.GONE);
                return false;
            }
        });

        return view;
    }


}
