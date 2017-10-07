package com.example.admin.materialui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

public class ToolbarActivity extends AppCompatActivity implements View.OnClickListener {
    CoordinatorLayout linearLayout;
    FloatingActionButton floatingActionButton;
    DrawerLayout drawerLayout;
    NavigationDrawer navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.root);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        drawerLayout=findViewById(R.id.drawer);
        navigationDrawer=(NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment);
        navigationDrawer.add(R.id.fragment,drawerLayout,toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.app_bar_search) {
            final Snackbar snackbar = Snackbar.make(linearLayout, "Finish", Snackbar.LENGTH_LONG);
            snackbar.setAction("okay", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(linearLayout, "done", Snackbar.LENGTH_LONG).show();
                }
            });
            snackbar.show();
        }
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pop, menu);
        return true;

    }

    @Override
    public void onClick(View view) {
        Snackbar.make(linearLayout, "done", Snackbar.LENGTH_LONG).show();
    }
}
