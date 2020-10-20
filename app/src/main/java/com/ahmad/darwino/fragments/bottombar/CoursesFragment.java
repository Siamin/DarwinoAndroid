package com.ahmad.darwino.fragments.bottombar;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.darwino.R;
import com.ahmad.darwino.activities.CourseActivity;
import com.ahmad.darwino.adapters.CourseAdapter;
import com.ahmad.darwino.adapters.EmptyAdapter;
import com.ahmad.darwino.adapters.FastAccessAdapter;
import com.ahmad.darwino.fragments.BaseFragment;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.FastAccessModel;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.Category;
import com.ahmad.darwino.network.mdoels.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class CoursesFragment extends BaseFragment implements OnClick {

    OnClick fastAccessClick;
    RecyclerView courseRecyclerView;
    CourseAdapter courseAdapter;
    List<Course> courses = new ArrayList<>();
    List<Course> courses_filter = new ArrayList<>();
    String TAG = "TAG_CoursesFragment";

    public CoursesFragment() {
        // Required empty public constructor
    }


    public int position = -1,page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("fragment:",String.valueOf(position));


        RecyclerView fastAccessRecyclerView = view.findViewById(R.id.fast_access_recyclerView);

        List<FastAccessModel> fastAccessModels = new ArrayList<>();

        courseRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager courseLayoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(courseLayoutManager);

        courseAdapter = new CourseAdapter(courses, this);
        courseRecyclerView.setAdapter(courseAdapter);

        getData();

    }

    void getData() {

        fade();


        Call<List<Course>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCourseList(200,page);

        client.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                unFade();

                for(Course c : response.body()){
                    courses.add(c);
                    courseAdapter.notifyItemInserted(courses.size()-1);
                }

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                unFade();
                internetError();
                shortToast(t.getMessage());
            }
        });

    }

    public void getDataByCategory(int[] array,int position) {

        courses_filter.clear();

        if (position > 0){
            for (int i = 0; i < courses.size() ;i++){
                for (int j = 0; j < array.length ;j++){
                    if (courses.get(i).categoryId == array[j]) {
                        courses_filter.add(courses.get(i));
                        break;
                    }
                }
            }


            if (courses_filter.size()>0){
                courseAdapter = new CourseAdapter(courses_filter, this);
                courseRecyclerView.setAdapter(courseAdapter);
            }else{
                courseRecyclerView.setAdapter(new EmptyAdapter("دوره ای یافت نشد"));
            }


//            courseAdapter = new CourseAdapter(courses_filter, this);
        }else {
            courseAdapter = new CourseAdapter(courses, this);
            courseRecyclerView.setAdapter(courseAdapter);
        }



        courseRecyclerView.notifyAll();

//        fade();
//
//        Call<List<Course>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCoursesByCategories(array,200,page);
//
//        client.enqueue(new Callback<List<Course>>() {
//            @Override
//            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
//                unFade();
//
//                for(Course c : response.body()){
//                    Log.i(TAG,c.name);
//                    /*courses.add(c);
//
//                    courseAdapter.notifyItemInserted(courses.size()-1);*/
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Course>> call, Throwable t) {
//                unFade();
//                internetError();
//                shortToast(t.getMessage());
//            }
//        });


    }

    @Override
    public void Clicked(int position) {

    }

    @Override
    public void Clicked(int position, View sharedElement, View secondElement) {

        Intent i = new Intent(getContext(), CourseActivity.class);
        i.putExtra("id", courses.get(position).id);

        Pair<View, String> p1 = Pair.create(sharedElement, sharedElement.toString());
        Pair<View, String> p2 = Pair.create(secondElement, secondElement.toString());

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this.getActivity(), p1);
        startActivity(i, options.toBundle());

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

}
