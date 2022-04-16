package com.example.admissionvido.UI.ClientUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admissionvido.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    String getValueFragment, getValueUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout contentLayout = findViewById(R.id.contentPanel);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            //Intent intent = new Intent(HomeActivity.this, );
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setBackground(null);

        bottomNav.setOnItemSelectedListener(item -> {
            //switch (item.getItemId()){
                //case R.id.home;
                    //getSupportFragmentManager().beginTransaction().replace().commit();
            //}
            return false;
        });

        if(getValueUser.equals("")){
            contentLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_not_interested_warning));
        }
    }
}