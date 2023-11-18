package com.example.myapplication.activity.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.service.CategoryService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private CategoryService categoryService;

    private TextInputEditText edtName;

    private Button btnSave, btnSeeAll;

    private TextView txtNoData;

    private ListView categoriesListView;



    ArrayList<CategoryModel> categoryDataListView = new ArrayList<>();
    CategoryAdapter categoryAdapter;

    public CategoryActivity() {
        this.categoryService = new CategoryService(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_admin);


        edtName = findViewById(R.id.editTextName);

        btnSave = findViewById(R.id.btnSave);
        btnSeeAll = findViewById(R.id.btnSeeAll);

        categoriesListView = findViewById(R.id.lvCategories);

        txtNoData = findViewById(R.id.txtNoData);

        if (edtName.getText().toString().length() == 0) {
            btnSave.setEnabled(false);
        }
        List<CategoryModel> categoryModels = categoryService.findAll();
        if (categoryModels != null && !categoryModels.isEmpty()) {
            categoryDataListView.addAll(categoryModels);

            categoryAdapter = new CategoryAdapter(this, R.layout.category_item_adapter, categoryDataListView);
            categoriesListView.setAdapter(categoryAdapter);
        } else {
            txtNoData.setVisibility(View.VISIBLE);
            categoriesListView.setVisibility(View.GONE);
        }


        categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CategoryModel categoryModel = categoryDataListView.get(position);
                showDialog(categoryModel);
            }
        });

        btnSave.setOnClickListener(view -> {
            CategoryModel categoryModel = new CategoryModel(edtName.getText().toString());
            categoryService.insert(categoryModel);

            Toast.makeText(CategoryActivity.this, "Thêm danh mục thành công.", Toast.LENGTH_SHORT).show();

            edtName.setText("");
            refreshListView();
        });

        btnSeeAll.setOnClickListener(view -> {
            refreshListView();
        });


        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtName.getText().toString().length() == 0) {
                    btnSave.setEnabled(false);
                } else {
                    btnSave.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void refreshListView() {
        categoryDataListView.clear();
        categoryDataListView.addAll(categoryService.findAll());
        categoryAdapter.notifyDataSetChanged();
        if (categoriesListView.getVisibility() == View.GONE) {
            txtNoData.setVisibility(View.VISIBLE);
            categoriesListView.setVisibility(View.GONE);
        }
    }

    private void showDialog(CategoryModel categoryModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quản Lý Danh Mục")
                .setView(R.layout.category_dialog_activity)
                .setNeutralButton("Cập Nhật", (dialog, id) -> {
                    categoryService.update(categoryModel);
                    Toast.makeText(CategoryActivity.this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                    refreshListView();
                    dialog.dismiss();
                })
                .setPositiveButton("Xóa", (dialog, id) -> {
                    categoryService.deleteById(categoryModel.id);

                    // Handle OK button click
                    Toast.makeText(CategoryActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                    refreshListView();
                    dialog.dismiss();
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Handle Cancel button click
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();

        // Set the text of the EditText to the value of categoryModel's name
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                EditText editText = dialog.findViewById(R.id.edtName);
                if (editText != null) {
                    editText.setText(categoryModel.name);
                }
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        categoryModel.name = charSequence.toString();
                        System.out.println(categoryModel.name);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });
        dialog.show();
    }
}
