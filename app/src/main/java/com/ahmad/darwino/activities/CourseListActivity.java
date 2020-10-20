package com.ahmad.darwino.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ahmad.darwino.R;
import com.ahmad.darwino.adapters.CourseAdapter;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.Category;
import com.ahmad.darwino.network.mdoels.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseListActivity extends BaseActivity implements OnClick {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<Course> courses;
    private int count = 10;
    private int page = 1;
    private boolean allDataLoaded = false;
    private boolean isAddingTolist = true;
    private boolean isRequestChanged = true;


    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        MY_TAG = "COURSE_LIST_ACTIVITY_TAG";
        account = Account.listAll(Account.class).get(0);

        recyclerView = findViewById(R.id.recyclerView);

        courses = new ArrayList<>();
        adapter= new CourseAdapter(courses, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


//        final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
//                .adapter(adapter)
//                .load(R.layout.adapter_course)
//                .show();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                skeletonScreen.hide();
//            }
//        }, 4000);


        getData();
        getCategories();


    }

    private void getCategories() {

        Call<List<Category>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getAllCategories();
        client.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d(MY_TAG, "categories received");
                if(response.code() == 200){
                    categories = response.body();
                    Log.d(MY_TAG, "categories size:"+categories.size());
                }
                else {
                    internetError();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                internetError();
            }
        });

    }

    private void getData() {

        if(isRequestChanged) {

            int size = 0;

            if(categories != null){
                size = categories.size();
            }

            int[] selectedCategoriesArray = new int[size];

            if(categories != null){

                for(int i=0; i<categories.size(); i++){
                    selectedCategoriesArray[i] = categories.get(i).id;
                }
            }

            Call<List<Course>> client = null;


            if(selectedCategoriesArray.length == 0) {

                client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCourseList(count, page);

            }
            else {

                isAddingTolist = false;



                client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCoursesByCategories(selectedCategoriesArray, count, page);

            }


            client.enqueue(new Callback<List<Course>>() {
                @Override
                public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                    Log.d(MY_TAG, "data received");

                    Log.d(MY_TAG, "Status code:" + response.code());
                    if (response.code() == 200) {

                        List<Course> result = response.body();
                        Log.d(MY_TAG, "length:" + result.size());

                        if (result.size() > 0) {
                            allDataLoaded = true;
                        } else if (isAddingTolist) {
                            int coursesCount = courses.size();
                            courses.addAll(result);
                            adapter.notifyItemRangeInserted(coursesCount, result.size());
                        } else {
                            courses = result;
                            adapter.notifyDataSetChanged();
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<Course>> call, Throwable t) {
                    Log.d(MY_TAG, t.getMessage());
                    internetError();
                }
            });

        }

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

    @Override
    public void Clicked(int position) {
        shortToast(String.valueOf(position));
    }

    @Override
    public void Clicked(int position, View sharedElement, View secondElement) {

    }

}
