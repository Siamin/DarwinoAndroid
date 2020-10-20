package com.ahmad.darwino.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmad.darwino.CusrtomViews.MyEditText;
import com.ahmad.darwino.CusrtomViews.NonSwipeableViewPager;
import com.ahmad.darwino.PersianCalendar;
import com.ahmad.darwino.R;
import com.ahmad.darwino.adapters.DialogFilterAdapter;
import com.ahmad.darwino.fragments.bottombar.BlogFragment;
import com.ahmad.darwino.fragments.bottombar.CoursesFragment;
import com.ahmad.darwino.fragments.bottombar.DarwinLandFragment;
import com.ahmad.darwino.fragments.bottombar.HomeFragment;
import com.ahmad.darwino.fragments.bottombar.ProfileFragment;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    public NonSwipeableViewPager viewPager;
    TextView toolbar_title;
    MyEditText search;
    Toolbar toolbar;
    AppCompatButton filter;
    String TAG = "TAG_HomeActivity";
    List<Category> categories;
    BottomBarAdapter adapter;
    BottomNavigationView navigation;
    List<Integer> undo = new ArrayList<Integer>();
    int IdPage = R.id.navigation_home;
    CardView cardView;
    ImageView imageSearch, imageCancle;


    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            undo.add(IdPage);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar_title.setText("خانه");
                    viewPager.setCurrentItem(0);
                    filter.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    IdPage = R.id.navigation_home;
                    return true;
                case R.id.navigation_menu:
                    toolbar_title.setText("دوره های آموزشی");
                    viewPager.setCurrentItem(1);
                    filter.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    search.setHint("جستجوی دوره");
                    IdPage = R.id.navigation_menu;
                    return true;
                case R.id.navigation_user:
                    toolbar_title.setText("پروفایل");
                    viewPager.setCurrentItem(4);
                    filter.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    IdPage = R.id.navigation_user;
                    return true;
                case R.id.navigation_chat:
                    toolbar_title.setText("بلاگ");
                    viewPager.setCurrentItem(3);
                    filter.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                    search.setHint("جستجوی بلاگ");
                    IdPage = R.id.navigation_chat;
                    return true;
                case R.id.navigation_group:
                    toolbar_title.setText("داروین لند");
                    viewPager.setCurrentItem(2);
                    filter.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    IdPage = R.id.navigation_group;
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Log.i(TAG, "2018/06/11 >> "+pc.MiladiToJalali(2018,6,11));



        init();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search text
            }
        });

        imageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });

        getCategories();

    }


    void init() {

        imageCancle = findViewById(R.id.home_search_cancle);
        imageSearch = findViewById(R.id.home_search_searchicon);
        cardView = findViewById(R.id.home_search_cardview);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        filter = findViewById(R.id.filter);
        toolbar_title = findViewById(R.id.toolbar_title);

        search = findViewById(R.id.home_search);
    }

    private void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new BottomBarAdapter(getSupportFragmentManager());


        HomeFragment homeFragment = new HomeFragment();
        CoursesFragment coursesFragment = new CoursesFragment();
        DarwinLandFragment darwinLandFragment = new DarwinLandFragment();
        BlogFragment blogFragment = new BlogFragment();
        ProfileFragment profileFragment = new ProfileFragment();


        adapter.addFragments(homeFragment);
        adapter.addFragments(coursesFragment);
        adapter.addFragments(darwinLandFragment);
        adapter.addFragments(blogFragment);
        adapter.addFragments(profileFragment);


        viewPager.setAdapter(adapter);

    }


    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
        undo.add(IdPage);

        if (item == 1) {
            navigation.setSelectedItemId(R.id.navigation_menu);
            filter.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            IdPage = R.id.navigation_menu;
        } else if (item == 2) {
            navigation.setSelectedItemId(R.id.navigation_group);
            filter.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            IdPage = R.id.navigation_group;
        } else if (item == 3) {
            navigation.setSelectedItemId(R.id.navigation_chat);
            filter.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
            IdPage = R.id.navigation_chat;
        }
    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }


    public class BottomBarAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        public BottomBarAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Our custom method that populates this Adapter with Fragments
        public void addFragments(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    void filter() {

        final Dialog dialog = new Dialog(HomeActivity.this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_courses);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        ImageView cancle = dialog.findViewById(R.id.dialog_cancle);
        Button ok = dialog.findViewById(R.id.dialog_ok);
        RecyclerView recyclerView = dialog.findViewById(R.id.dialog_recyclerview);

        LinearLayoutManager LLM = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DialogFilterAdapter(categories));

        if (categories.size() == 0) {
            dialog.dismiss();
            return;
        }


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    dialog.dismiss();

                    String GetCategory = "";
                    int position = 0;


                    for (Category c : categories) {

                        if (c.isSelected) {
                            ++position;
                            GetCategory += "/" + c.id;
                            Log.i(TAG, c.name + " -> " + c.id);
                        }

                    }

                    int[] IdCategory = new int[0];
                    if (position > 0) {

                        if (!GetCategory.isEmpty())
                            GetCategory = GetCategory.substring(1, GetCategory.length());
                        Log.i(TAG, GetCategory);


                        String[] gEtcAtegory = GetCategory.split("/");
                        IdCategory = new int[gEtcAtegory.length];

                        for (int i = 0; i < gEtcAtegory.length; i++) {
                            IdCategory[i] = Integer.parseInt(gEtcAtegory[i]);
                            Log.i(TAG, IdCategory[i] + "");
                        }

                    }

                    ((CoursesFragment) adapter.getItem(1)).getDataByCategory(IdCategory, position);

                } catch (Exception e) {
                    Log.i(TAG, "Error -> " + e.toString());
                }
            }
        });


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    void getCategories() {

        Call<List<Category>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getAllCategories();
        client.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d(TAG, "categories received");
                if (response.code() == 200) {
                    categories = response.body();
                    Log.d(TAG, "categories size:" + categories.size());
                } else {
                    internetError();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                internetError();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        undo.clear();
        IdPage = R.id.navigation_home;

    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Size :" + undo.size());
        if (undo.size() > 0) {
            List<Integer> newundo = new ArrayList<Integer>();
            int Selection = undo.get(undo.size() - 1);
            Log.i(TAG, "Undo :" + Selection);
            navigation.setSelectedItemId(Selection);
            for (int i = 0; i < undo.size() - 2; i++) {
                newundo.add(undo.get(i));
            }
            undo.clear();
            undo = newundo;
        } else {
            super.onBackPressed();
        }

    }
}
