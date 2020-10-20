package com.ahmad.darwino.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmad.darwino.R;
import com.ahmad.darwino.adapters.CourseAdapter;
import com.ahmad.darwino.adapters.ProfileHistoryAdapter;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.FastAccessModel;
import com.ahmad.darwino.network.mdoels.HistoryCourses;

import java.util.ArrayList;
import java.util.List;


public class ProfileHistoryFragment extends BaseFragment {

    String TAG = "TAG_CoursesFragment";
    OnClick fastAccessClick;
    RecyclerView courseRecyclerView;
    ProfileHistoryAdapter Adapter;
    List<HistoryCourses> historyCoursesList = new ArrayList<>();


    public ProfileHistoryFragment() {
        // Required empty public constructor
    }


    public int position = -1, page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_hostory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("fragment:", String.valueOf(position));


        RecyclerView fastAccessRecyclerView = view.findViewById(R.id.fast_access_recyclerView);

        List<FastAccessModel> fastAccessModels = new ArrayList<>();

        courseRecyclerView = view.findViewById(R.id.profile_history_recyclerView);

        getData();

        LinearLayoutManager courseLayoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(courseLayoutManager);
        Adapter = new ProfileHistoryAdapter(historyCoursesList);
        courseRecyclerView.setAdapter(Adapter);



    }


    void getData() {

        for (int i = 0; i < 10; i++) {
            HistoryCourses hc = new HistoryCourses();
            hc.id = i + 1;
            hc.name = "دوره آموزشی " + (i + 1);
            hc.teacher = "استاد " + (i + 1);
            hc.period = (i + 1) + " هفته";
            hc.date = "1398/01/" + (i + 1);
            hc.price = (i+10) * 10000;
            historyCoursesList.add(hc);
        }


//        fade();
//
//
//        Call<List<Course>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCourseList(200,page);
//
//        client.enqueue(new Callback<List<Course>>() {
//            @Override
//            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
//                unFade();
//
//                for(Course c : response.body()){
//                    courses.add(c);
//                    courseAdapter.notifyItemInserted(courses.size()-1);
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
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }
}
