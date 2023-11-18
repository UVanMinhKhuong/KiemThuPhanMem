package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminFragmentActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_navigation_main);

        Toast.makeText(this, "Chào mừng " + getIntent().getStringExtra("Email") + " đến với thế giới của tôi", Toast.LENGTH_LONG);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.itemHome);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int idItem = item.getItemId();

        if (idItem == R.id.itemHome) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new FirstFragment())
                    .commit();
            return true;
        } else if (idItem == R.id.itemDashboard) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new DashboardFragment())
                    .commit();
            return true;
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new SecondFragment())
                    .commit();
            return true;
        }
    }
}
