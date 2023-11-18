package com.example.myapplication.activity.role;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.fragment.AdminFragmentActivity;
import com.example.myapplication.service.RoleService;

public class RoleEditActivity extends Fragment {

    private RoleService roleService;

    Button btnAdd, btnEdit, btnDelete;
    TextView txtTitle;
    EditText edtName;

    int id;
    String name;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.roleService = new RoleService(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.role_admin_edit_activity, container, false);

        btnAdd = rootView.findViewById(R.id.btnAdd);
        btnEdit = rootView.findViewById(R.id.btnEdit);
        btnDelete = rootView.findViewById(R.id.btnDelete);

        txtTitle = rootView.findViewById(R.id.txtTitle);
        edtName = rootView.findViewById(R.id.editTextName);

        Bundle bundle = getArguments();

        if(bundle != null){
            id = bundle.getInt("roleId", 0);
            name = bundle.getString("roleName", "");
        }

        if (id > 0) {
            txtTitle.setText("Chỉnh Sửa");
            edtName.setText(name);
            btnAdd.setEnabled(false);
        } else {
            txtTitle.setText("Thêm Mới Vai Trò");
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }

        btnDelete.setOnClickListener(view -> {
            if (id > 0) {
                roleService.deleteById(id);

                Fragment newFragment = new RoleActivity();
                FragmentTransaction transaction = ((AdminFragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(rootView.getContext(), "Tên vai trò không thể rỗng.", Toast.LENGTH_SHORT);
            } else {
                roleService.insert(name);

                Toast.makeText(rootView.getContext(), String.format("Thêm mới vai trò %s thành công.", name), Toast.LENGTH_SHORT);
                Fragment newFragment = new RoleActivity();
                FragmentTransaction transaction = ((AdminFragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnEdit.setOnClickListener(view -> {
            String name = edtName.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(rootView.getContext(), "Tên vai trò không thể rỗng.", Toast.LENGTH_SHORT).show();
            } else {
                roleService.update(id, name);

                Toast.makeText(rootView.getContext(), String.format("Cập nhật vai trò %s thành công.", name), Toast.LENGTH_SHORT).show();

                Fragment newFragment = new RoleActivity();
                FragmentTransaction transaction = ((AdminFragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return rootView;
    }
}
