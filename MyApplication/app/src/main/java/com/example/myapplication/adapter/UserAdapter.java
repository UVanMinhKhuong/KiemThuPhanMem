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
import com.example.myapplication.model.UserResponse;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<UserResponse> {
    private int layoutId;
    private Activity activity;
    private ArrayList<UserResponse> userResponses;


    public UserAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<UserResponse> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.layoutId = resource;
        this.userResponses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.user_item_adapter, null);

        UserResponse userResponse = userResponses.get(position);
        TextView txtFullName = convertView.findViewById(R.id.txtFullName);
        TextView txtEmail = convertView.findViewById(R.id.txtEmail);
        TextView txtJoinedDate = convertView.findViewById(R.id.txtJoinDate);
        TextView txtRoleName = convertView.findViewById(R.id.txtRoleName);

        txtFullName.setText(userResponse.getFullName());
        txtEmail.setText(userResponse.email);
        txtJoinedDate.setText(userResponse.joinedDate);
        txtRoleName.setText(userResponse.makeTextViewRoleName());

        return convertView;
    }
}
