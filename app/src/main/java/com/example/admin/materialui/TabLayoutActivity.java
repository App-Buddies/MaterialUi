package com.example.admin.materialui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public int tabicons[]={R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setTab(viewPager);
        setTabicons();


    }

    private void setTabicons() {
        tabLayout.getTabAt(0).setIcon(tabicons[0]);
        tabLayout.getTabAt(1).setIcon(tabicons[0]);
        tabLayout.getTabAt(2).setIcon(tabicons[0]);
    }

    private void setTab(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add("one", new BlankFragment());
        viewPagerAdapter.add("two", new BlankFragment());
        viewPagerAdapter.add("three", new BlankFragment());
        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        List<String> titles = new ArrayList<>();
        List<Fragment> frag = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        public void add(String one, BlankFragment blankFragment) {
            titles.add(one);
            frag.add(blankFragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position);
        }
    }
}
