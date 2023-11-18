package com.example.myapplication.activity.dashboard;

import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.enumeration.CardViewItemEnum;

import java.util.ArrayList;

public class AdminGridViewFeature extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard_activity);

        gridView = findViewById(R.id.idGVcourses);
        ArrayList<GridViewModel> courseModelArrayList = new ArrayList<>();

        // Init data
        courseModelArrayList.add(new GridViewModel("Cá Nhân", R.drawable.adminuserprofile, null));
        courseModelArrayList.add(new GridViewModel("Người Dùng", R.drawable.adminusers, null));
        courseModelArrayList.add(new GridViewModel("Loại Sản Phẩm", R.drawable.admincategory, CardViewItemEnum.CATEGORY));
        courseModelArrayList.add(new GridViewModel("Sản Phẩm", R.drawable.admin_products, CardViewItemEnum.PRODUCT));


        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, R.layout.card_item_example, courseModelArrayList);

        gridView.setAdapter(gridViewAdapter);
    }
}
