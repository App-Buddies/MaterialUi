package com.example.admin.materialui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NavigationDrawer extends Fragment {
    ActionBarDrawerToggle mActionBarDarwerDoggle;
    DrawerLayout mDrawer;
    private boolean mSavedInstance;
    public boolean mUserLearnedDrawer;
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    View view;


    public NavigationDrawer() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readSettings(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mSavedInstance = true;
        }
    }

    public void add(int fragment, DrawerLayout drawerLayout, Toolbar toolbar) {
        view = getActivity().findViewById(fragment);

        mDrawer = drawerLayout;
        mActionBarDarwerDoggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedSettings(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().supportInvalidateOptionsMenu();
            }
        };

        if (!mSavedInstance && !mUserLearnedDrawer) {
            mDrawer.openDrawer(view);
        }
        drawerLayout.addDrawerListener(mActionBarDarwerDoggle);
        mDrawer.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDarwerDoggle.syncState();
            }
        });

    }


    public static void savedSettings(Context context, String prefName, String prefValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(prefName, prefValue);
        edit.apply();
    }

    public static String readSettings(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, prefValue);
    }
}
