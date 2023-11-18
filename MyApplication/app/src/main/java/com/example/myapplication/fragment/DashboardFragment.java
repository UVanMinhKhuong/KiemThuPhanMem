package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.dashboard.GridViewAdapter;
import com.example.myapplication.activity.dashboard.GridViewModel;
import com.example.myapplication.enumeration.CardViewItemEnum;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardFragment extends Fragment {
    GridView gridView;
    ArrayList<GridViewModel> courseModelArrayList = new ArrayList<>(
            Arrays.asList(
                    // Init data
                    new GridViewModel("Vai Trò", R.drawable.adminuserprofile, CardViewItemEnum.ROLE),
                    new GridViewModel("Người Dùng", R.drawable.adminusers, CardViewItemEnum.USER),
                    new GridViewModel("Loại Sản Phẩm", R.drawable.admincategory, CardViewItemEnum.CATEGORY),
                    new GridViewModel("Sản Phẩm", R.drawable.admin_products, CardViewItemEnum.PRODUCT)
            )
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_dashboard_activity, container, false);

        gridView = rootView.findViewById(R.id.idGVcourses);


        GridViewAdapter gridViewAdapter = new GridViewAdapter(requireActivity(), R.layout.card_item_example, courseModelArrayList);

        gridView.setAdapter(gridViewAdapter);

        return rootView;
    }


}
