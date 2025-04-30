package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.newkolbo.R;
import com.example.newkolbo.User;
import com.example.newkolbo.fragment.LoginFragment;
import com.example.newkolbo.fragment.RegFragment;
import com.google.android.material.tabs.TabLayout;

public class LoginRegActivity extends AppCompatActivity {

    public static User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_reg);

        Fragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                if (tab.getPosition() == 0)
                    fragment = new LoginFragment();
                else
                    fragment = new RegFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}