package com.example.myapplication.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdminAdapter;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.service.ProductService;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ProductService productService;
    EditText edtName;
    ListView lvProducts;

    Button btnAdd;

    ArrayList<ProductModel> productModels = new ArrayList<>();

    ProductAdminAdapter productAdminAdapter;

    public ProductActivity() {
        productService = new ProductService(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);

        btnAdd = findViewById(R.id.btnAdd);
        lvProducts = findViewById(R.id.lvProduct);
        edtName = findViewById(R.id.editTextName);

        productModels.addAll(productService.findAll());

        productAdminAdapter = new ProductAdminAdapter(this, R.layout.product_item_adapter, productModels);
        lvProducts.setAdapter(productAdminAdapter);


        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productModels.clear();

                if (charSequence.toString().trim().length() == 0) {
                    productModels.addAll(productService.findAll());
                } else {
                    productModels.addAll(productService.findAllByName(charSequence.toString()));
                }

//                productAdminAdapter.notify();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ProductActivity.this, ProductEditActivity.class);
                ProductModel productModel = productModels.get(position);
                intent.putExtra("product", productModel);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(view ->
        {
            Intent intent = new Intent(ProductActivity.this, ProductEditActivity.class);
            startActivity(intent);
        });
    }
}
