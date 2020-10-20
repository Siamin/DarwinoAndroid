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
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.darwino.R;
import com.ahmad.darwino.activities.CourseActivity;
import com.ahmad.darwino.activities.HomeActivity;
import com.ahmad.darwino.adapters.CourseAdapter;
import com.ahmad.darwino.adapters.FastAccessAdapter;
import com.ahmad.darwino.fragments.BaseFragment;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.models.FastAccessModel;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.Course;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements OnClick {


    OnClick fastAccessClick;
    RecyclerView courseRecyclerView;
    CourseAdapter courseAdapter;
    List<Course> courses = new ArrayList<>();
    int counterTest=0;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MY_TAG = "TAG_HOME_FRAGMENT";
        //account = Account.listAll(Account.class).get(0);


        progressBar = view.findViewById(R.id.progressBar);
        container = view.findViewById(R.id.container);

        RecyclerView fastAccessRecyclerView = view.findViewById(R.id.fast_access_recyclerView);

        List<FastAccessModel> fastAccessModels = new ArrayList<>();

        fastAccessClick = new OnClick() {
            @Override
            public void Clicked(int position) {
                if (position != 3){
                    ((HomeActivity) getActivity()).setCurrentItem(position+1, true);
                }else {
                    Toast.makeText(getContext(), "این گزینه به زودی فعال خواهد شد!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void Clicked(int position, View sharedElement, View secondElement) {

            }
        };

        FastAccessAdapter fastAccessAdapter = new FastAccessAdapter(fastAccessModels, fastAccessClick);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        fastAccessRecyclerView.setLayoutManager(layoutManager);

        fastAccessRecyclerView.setAdapter(fastAccessAdapter);


        FastAccessModel model = new FastAccessModel();
        model.text = "دوره های جدید";
        model.image = R.drawable.ic_laptop;
        fastAccessModels.add(model);


        model = new FastAccessModel();
        model.text = "عضویت باشگاه";
        model.image = R.drawable.ic_hierarchical_structure;
        fastAccessModels.add(model);


        model = new FastAccessModel();
        model.text = "مقالات برتر";
        model.image = R.drawable.ic_chat;
        fastAccessModels.add(model);


        model = new FastAccessModel();
        model.text = "دانشجویان برتر";
        model.image = R.drawable.ic_people;
        fastAccessModels.add(model);


        fastAccessAdapter.notifyItemRangeInserted(0, fastAccessModels.size());


        courseRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager courseLayoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(courseLayoutManager);

        courseAdapter = new CourseAdapter(courses, this);
        courseRecyclerView.setAdapter(courseAdapter);

        getData();


    }

    private void getData() {

        fade();


        Call<List<Course>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).topCourse();

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

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

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

}
