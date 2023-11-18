package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.CategoryModel;
import com.example.myapplication.model.RoleModel;

import java.util.ArrayList;
import java.util.List;

public class RoleAdapter extends ArrayAdapter<RoleModel> {
    private List<RoleModel> roles;
    private Activity activity;
    private int layoutId;

    public RoleAdapter(Activity activity, int layoutId, List<RoleModel> roles) {
        super(activity, layoutId, roles);
        this.activity = activity;
        this.layoutId = layoutId;
        this.roles = roles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layoutId, null);

        RoleModel role = roles.get(position);

        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(role.getName());

        TextView txtId = convertView.findViewById(R.id.txtId);
        txtId.setText(String.valueOf(role.getId()));

        return convertView;
    }
}
