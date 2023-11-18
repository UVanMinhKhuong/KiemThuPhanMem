package com.example.myapplication.activity.role;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.category.CategoryActivity;
import com.example.myapplication.adapter.RoleAdapter;
import com.example.myapplication.fragment.FirstFragment;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.model.RoleModel;
import com.example.myapplication.service.RoleService;
import com.example.myapplication.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class RoleActivity extends Fragment {
    private RoleService roleService;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.roleService = new RoleService(requireContext());
    }

    Button btnAdd;

    ListView lvRole;
    List<RoleModel> roleModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.role_admin_activity, container, false);

        btnAdd = rootView.findViewById(R.id.btnAddRole);
        lvRole = rootView.findViewById(R.id.lvRoles);

        roleModels = roleService.findAll();

        RoleAdapter roleAdapter = new RoleAdapter(getActivity(), R.layout.role_item_adapter, roleModels);

        lvRole.setAdapter(roleAdapter);

        btnAdd.setOnClickListener(view -> {

            RoleEditActivity roleActivity = new RoleEditActivity();

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, roleActivity)
                    .commit();
        });


        lvRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                RoleModel roleModel = roleModels.get(position);

                RoleEditActivity roleActivity = new RoleEditActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("roleId", roleModel.getId());
                bundle.putString("roleName", roleModel.getName());

                roleActivity.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, roleActivity)
                        .commit();
            }
        });
        return rootView;
    }

}
