package com.example.admin.materialui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.cordinator);
        FloatingActionButton floatingActionButton = findViewById(R.id.gmail_fab);
        View bottom = coordinatorLayout.findViewById(R.id.gmail_bottomsheet);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Toast.makeText(getApplicationContext(), "dragging", Toast.LENGTH_SHORT).show();
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Toast.makeText(getApplicationContext(), "collpsed", Toast.LENGTH_SHORT).show();
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Toast.makeText(getApplicationContext(), "Expended", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}
