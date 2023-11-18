package com.example.myapplication.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.service.CategoryService;
import com.example.myapplication.service.ProductService;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductEditActivity extends AppCompatActivity {
    private CategoryService categoryService;
    private ProductService productService;

    ArrayList<CategoryModel> categoryModels = new ArrayList<>();

    EditText edtName, edtCode, edtDescription, edtPrice;

    Button btnAdd, btnEdit, btnDelete;

    Spinner spinnerCategory;

    int categoryId;

    public ProductEditActivity() {
        this.categoryService = new CategoryService(this);
        this.productService = new ProductService(this);
    }

    ProductModel productModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_admin_edit_activity);

        edtName = findViewById(R.id.editTextNameProduct);
        edtCode = findViewById(R.id.editTextCodeProduct);
        edtPrice = findViewById(R.id.editTextPriceProduct);
        edtDescription = findViewById(R.id.editTextDescriptionProduct);

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        spinnerCategory = findViewById(R.id.spinnerCategories);
        categoryModels.addAll(categoryService.findAll());

        Intent intent = getIntent();
        Object object = intent.getSerializableExtra("product");
        if (object != null) {
            productModel = (ProductModel) object;

            edtName.setText(productModel.getName());
            edtCode.setText(productModel.getCode());
            edtDescription.setText(productModel.getDescription());
            edtPrice.setText(String.valueOf(productModel.getPrice()));

            spinnerCategory.setSelection(categoryModels.indexOf(categoryModels.stream().filter(categoryModel -> categoryModel.id == productModel.getId()).findAny().get()));

        }


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                categoryModels.stream().map(categoryModel -> categoryModel.name).collect(Collectors.toList()));
        ;
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCategory.setAdapter(adapter);


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                CategoryModel categoryModel = categoryModels.get(position);
                categoryId = categoryModel.id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDelete.setOnClickListener(view ->
        {
            if (productModel != null) {
                this.productService.deleteById(productModel.getId());
            }
        });

        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String code = edtCode.getText().toString();
            String description = edtDescription.getText().toString();
            String price = edtPrice.getText().toString();

            ProductModel product = new ProductModel(name, code, description, Long.valueOf(price), false, categoryId);
            boolean isInsertSuccess = this.productService.insert(product);
            if(isInsertSuccess){
                Intent intentProductActivity = new Intent(ProductEditActivity.this, ProductActivity.class);
                startActivity(intentProductActivity);
            }else {
                Toast.makeText(ProductEditActivity.this, "Thêm sản phẩm thất bại.", Toast.LENGTH_SHORT);
            }
        });
    }

}
