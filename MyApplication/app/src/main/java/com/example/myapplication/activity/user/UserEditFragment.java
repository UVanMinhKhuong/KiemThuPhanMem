package com.example.myapplication.activity.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.model.RoleModel;
import com.example.myapplication.model.UserModel;
import com.example.myapplication.service.RoleService;
import com.example.myapplication.service.UserRoleService;
import com.example.myapplication.service.UserService;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserEditFragment extends Fragment {
    EditText edtUsername, edtEmail, edtPassword, edtFirstName, edtLastName, edtPhone;

    Button btnAdd, btnEdit, btnDelete;

    Spinner spinnerRoles;
    private UserService userService;
    private RoleService roleService;
    private UserRoleService userRoleService;
    private ArrayList<RoleModel> roleModels;
    private Integer roleId;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.userService = new UserService(requireContext());
        this.roleService = new RoleService(requireContext());
        this.userRoleService = new UserRoleService(requireContext());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_admin_edit_activity, container, false);


        edtUsername = rootView.findViewById(R.id.edtUsername);
        edtPassword = rootView.findViewById(R.id.edtPassword);
        edtFirstName = rootView.findViewById(R.id.edtFirstName);
        edtLastName = rootView.findViewById(R.id.edtLastName);
        edtPhone = rootView.findViewById(R.id.edtPhone);
        edtEmail = rootView.findViewById(R.id.edtEmail);

        btnAdd = rootView.findViewById(R.id.btnAdd);
        btnEdit = rootView.findViewById(R.id.btnEdit);
        btnDelete = rootView.findViewById(R.id.btnDelete);

        spinnerRoles = rootView.findViewById(R.id.spinnerRoles);

        roleModels = new ArrayList<>(roleService.findAll());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                roleModels.stream().map(role -> role.getName()).collect(Collectors.toList())
                );
        spinnerRoles.setAdapter(adapter);

        // On select spinner role
        spinnerRoles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                RoleModel roleModelSelected = roleModels.get(position);
                roleId = roleModelSelected.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnAdd.setOnClickListener(view -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String email = edtEmail.getText().toString();
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String phone = edtPhone.getText().toString();

            UserModel userModel = new UserModel(
                    username,
                    email,
                    firstName,
                    lastName,
                    phone
            );

            userModel.setPassword(password);

            long userId = userService.insert(userModel);

            userRoleService.insert((int) userId, roleId);

            Toast.makeText(getContext(), "Thêm mới người dùng thành công", Toast.LENGTH_SHORT).show();

        });

        return rootView;
    }
}
