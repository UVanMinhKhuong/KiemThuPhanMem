package com.example.myapplication.activity.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.activity.role.RoleActivity;
import com.example.myapplication.activity.user.UserAdminFragment;
import com.example.myapplication.enumeration.CardViewItemEnum;
import com.example.myapplication.fragment.AdminFragmentActivity;

import java.util.ArrayList;

public class GridViewAdapter
        extends ArrayAdapter<GridViewModel> {
    private ArrayList<GridViewModel> gridViewModels;
    private Activity activity;
    private int layoutId;

    public GridViewAdapter(Activity activity, int layout, ArrayList<GridViewModel> gridViewModels) {
        super(activity, layout, gridViewModels);
        this.activity = activity;
        this.layoutId = layout;
        this.gridViewModels = gridViewModels;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View cardView = convertView;
        if (cardView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            cardView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        GridViewModel gridViewModel = getItem(position);
        TextView txtName = cardView.findViewById(R.id.txtName);

        ImageView imageView = cardView.findViewById(R.id.iwLogo);

        txtName.setText(gridViewModel.getCourseName());
        imageView.setImageResource(gridViewModel.getImageId());

        cardView.setOnClickListener(handleOnclickCardItem(gridViewModel));

        return cardView;
    }

    public View.OnClickListener handleOnclickCardItem(GridViewModel gridViewModel) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardViewItemEnum cardViewItemEnum = gridViewModel.getCardViewItemEnum();
                if (cardViewItemEnum.getActivity() != null) {

                    switch (cardViewItemEnum) {
                        case ROLE: {
                            onCartHandleSwitchRole();
                            break;
                        }
                        case USER: {
                            onCartHandleSwitchUser();
                            break;
                        }
                        default: {
                            Intent intent = new Intent(activity, cardViewItemEnum.getActivity());

                            activity.startActivity(intent);
                        }
                    }

                }
            }

        };
    }

    // Phương thức xử lý sự kiện click trên cart view
    private void onCartHandleSwitchRole() {
        // Xử lý việc chuyển fragment ở đây, ví dụ:
        Fragment newFragment = new RoleActivity();
        FragmentTransaction transaction = ((AdminFragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Phương thức xử lý sự kiện click trên cart view
    private void onCartHandleSwitchUser() {
        // Xử lý việc chuyển fragment ở đây, ví dụ:
        Fragment newFragment = new UserAdminFragment();
        FragmentTransaction transaction = ((AdminFragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, newFragment); // R.id.fragment_container là id của container fragment trong MainActivity
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
