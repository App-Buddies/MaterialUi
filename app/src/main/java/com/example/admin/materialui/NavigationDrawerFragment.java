package com.example.admin.materialui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View mContainer;
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    public static final String PREF_FILE_NAME = "filename";
    private boolean mDrawerOpened = false;
    RecyclerView recyclerView;
    public String names[] = {"one", "two", "three", "four", "five", "six", "seven"};

    public NavigationDrawerFragment() {

    }

    public List<Information> getData() {
        ArrayList<Information> arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Information information = new Information();
            information.name = names[i];
            arrayList.add(information);
        }
        return arrayList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readfromPrefrence(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        mFromSavedInstanceState = savedInstanceState != null ? true : false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawerr, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecycleAdapter recycleAdapter = new RecycleAdapter(getData(), getContext());
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouch(getContext(), recyclerView, new ClickListner() {
            @Override
            public void onClick(View view, int position) {
                Log.d("pos",String.valueOf(position));
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d("pos",String.valueOf(position));

            }
        }));

    }

    public void init(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainer = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
                if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
                    mDrawerLayout.openDrawer(mContainer);
                }
            }
        });


    }

    public void saveToPreferences(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();

    }

    public static String readfromPrefrence(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, prefValue);

    }


    public class RecycleAdapter extends RecyclerView.Adapter<MyviewHolder> {
        LayoutInflater inflater;
        List<Information> data = Collections.emptyList();

        public RecycleAdapter(List<Information> data, Context context) {
            inflater = LayoutInflater.from(getContext());
            this.data = data;
        }

        @Override
        public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_extra, parent, false);
            return new MyviewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyviewHolder holder, int position) {
            Information information = data.get(position);
            holder.textView.setText(information.getName());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyviewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tec);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public class RecyclerViewTouch implements RecyclerView.OnItemTouchListener {
        GestureDetector gestureDetector;
        ClickListner clickListener;

        public RecyclerViewTouch(Context context, final RecyclerView recyclerView, final ClickListner clickListner) {
            this.clickListener = clickListner;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListner != null) {
                        clickListner.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null) {
                clickListener.onClick(child, recyclerView.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListner {
        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }
}
