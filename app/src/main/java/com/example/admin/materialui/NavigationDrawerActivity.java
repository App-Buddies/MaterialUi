package com.example.admin.materialui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class NavigationDrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        NavigationDrawerFragment navigationDrawerFragment=(NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationDrawerFragment.init(R.id.fragment2,drawerLayout,toolbar);
    }
}
