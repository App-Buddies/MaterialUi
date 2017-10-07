package com.example.admin.materialui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showtoolbar(View view) {
        startActivity(new Intent(this, ToolbarActivity.class));
    }

    public void showAppBarLayout(View view) {
        startActivity(new Intent(this, AppBarActivity.class));

    }

    public void showColapsingToolbar(View view) {
        startActivity(new Intent(this, CollapsingBarActivity.class));

    }

    public void showTextIput(View view) {
        startActivity(new Intent(this, TextInputActivity.class));

    }

    public void showTab(View view) {
        startActivity(new Intent(this, TabLayoutActivity.class));

    }

    public void showNav(View view) {
        startActivity(new Intent(this, NavigationActivity.class));

    }

    public void showBottomNav(View view) {
        startActivity(new Intent(this, BottomActivity.class));

    }

    public void showBottomsheet(View view) {
        startActivity(new Intent(this, BottomSheetActivity.class));

    }

    public void showRecycler(View view) {
        startActivity(new Intent(this, RecyclerActivity.class));

    }

    public void showContextualMeu(View view) {
        startActivity(new Intent(this, ContextMenuActivity.class));

    }

    public void notificationActivity(View view) {
        startActivity(new Intent(this, NotificationActivity.class));

    }
    public void navigationDrawerActivity(View view) {
        startActivity(new Intent(this, NavigationDrawerActivity.class));

    }

}
