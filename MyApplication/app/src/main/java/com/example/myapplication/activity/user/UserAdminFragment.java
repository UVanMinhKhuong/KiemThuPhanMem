package com.example.myapplication.activity.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.fragment.AdminFragmentActivity;
import com.example.myapplication.model.UserResponse;
import com.example.myapplication.service.UserService;

import java.util.ArrayList;

public class UserAdminFragment extends Fragment {

    private UserService userService;

    ListView lvUser;
    Button btnAdd;
    ArrayList<UserResponse> userResponses;
    UserAdapter userAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.userService = new UserService(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_admin_activity, null);

        lvUser = rootView.findViewById(R.id.lvUser);
        btnAdd = rootView.findViewById(R.id.btnAdd);

        userResponses = new ArrayList<>(this.userService.findUserResponseAll());

        userAdapter = new UserAdapter(getActivity(), R.layout.user_item_adapter, userResponses);

        lvUser.setAdapter(userAdapter);


        btnAdd.setOnClickListener(view -> {
            Fragment newFragment = new UserEditFragment();
            FragmentTransaction transaction = ((AdminFragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return rootView;
    }
}
