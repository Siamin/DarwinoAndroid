package com.ahmad.darwino.fragments.bottombar;


import android.content.Intent;
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
import com.ahmad.darwino.activities.DarwinoLandActivity;
import com.ahmad.darwino.adapters.DarwinoLandAdapter;
import com.ahmad.darwino.fragments.BaseFragment;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.FastAccessModel;
import com.ahmad.darwino.network.mdoels.DarwinoLandModel;
import java.util.ArrayList;
import java.util.List;


public class DarwinLandFragment  extends BaseFragment implements OnClick {

    OnClick fastAccessClick;
    RecyclerView courseRecyclerView;
    DarwinoLandAdapter darwinoLandAdapter;
    List<DarwinoLandModel> darwinoLandModels = new ArrayList<DarwinoLandModel>();

    public DarwinLandFragment() {
        // Required empty public constructor
    }


    public int position = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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


        darwinoLandModels.clear();
        DarwinoLandModel dlm = new DarwinoLandModel();

        dlm.id = 1;
        dlm.price = 90000;
        dlm.url_image = "http://darwino.ir/images/main/Bronze.svg";
        dlm.image_id = R.drawable.ic_darwinobronze;
        dlm.name = "عضویت برنزی";
        dlm.description = "این طرح عضویت در بازه کوتاه یکماهه، با هزینه ای ناچیز برای مخاطبانی طراحی شده است که دارای محدودیت زمانی در کسب آموزش بوده و نیاز به برنامه های جانبی گسترده ندارند.";
        dlm.shortDescription = "برای مخاطبین که دارای محدودیت زمانی در کسب آموزش هستند.";
        dlm.possibilities = "روپوش ویژه داروینو|بن تخفیف خرید کتاب|برنز سرتیفیکیت|برنامه های علمی فرهنگی (اختیاری)|خدمات مشاوره (اختیاری)";
        darwinoLandModels.add(dlm);
        //******************************************************************************************
        dlm = new DarwinoLandModel();
        dlm.id = 2;
        dlm.price = 180000;
        dlm.url_image = "http://darwino.ir/images/main/Silver.svg";
        dlm.image_id = R.drawable.ic_darwinosilver;
        dlm.name = "عضویت نقره ای";
        dlm.description = "طرح نقره ای برای دانشجویانی که تنها در بازه یک فصل امکان حضور در برنامه های آموزشی را دارند طراحی شده و علاوه بر امکانات حداقلی مجموعه ای از کتب آموزشی ضروری را دریافت خواهند نمود";
        dlm.shortDescription = "دانشجویانی که تنها در بازه یک فصل امکان حضور در برنامه های آموزشی را دارند.";
        dlm.possibilities = "کلیه خدمات عضویت برنزی|هدیه کتب ضروری|سیلور سرتیفیکیت|برنامه های علمی و فرهنگی(اختیاری)|خدمات مشاوره ای(اختیاری)";
        darwinoLandModels.add(dlm);
        //******************************************************************************************
        dlm = new DarwinoLandModel();
        dlm.id = 3;
        dlm.price = 900000;
        dlm.url_image = "http://darwino.ir/images/main/Gold.svg";
        dlm.image_id = R.drawable.ic_darwinogold;
        dlm.name = "عضویت طلایی";
        dlm.description = "طرح عضویت طلایی برای دانشجویان دوره کارشناسی و کارشناسی ارشد طراحی شده است. این گروه از دانشجویان با عضویت در این طرح به مدت شش ماه امکان استفاده از خدمات ویژه داروین\u200Cلند را خواهند داشت.";
        dlm.shortDescription = "برای دانشجویان سال اول و دوم دوره ای کارشناسی پیشنهاد می شود.";
        dlm.possibilities = "کلیه خدمات عضویت نقره ای|50% تخفیف برنامه های علمی و فرهنگی|گلدن سرتیفیکیت|خدمات مشاوره ای(اختیاری)|پکیج آموزشی ویژه(اختیاری)";
        darwinoLandModels.add(dlm);
        //******************************************************************************************
        dlm = new DarwinoLandModel();
        dlm.id = 4;
        dlm.price = 8000000;
        dlm.url_image = "http://darwino.ir/images/main/Platinum.svg";
        dlm.image_id = R.drawable.ic_darwinoplatinum;
        dlm.name = "عضویت پلاتینیوم";
        dlm.description = "این طرح برای دانشجویان و فارغ التحصیلان طراحی شده است. مخاطبین این طرح میتوانند از تمامی خدمات داروین\u200Cلند بهره مند شوند، همچنین در اولویت استخدام و راه اندازی شرکتهای دانش بنیان قرار خواهند گرفت.";
        dlm.shortDescription = "برای دانشجویان سال اخر و فارغ التحصیلان گرایش های مختلف رشته های علوم زیستی پیشنهاد می شود.";
        dlm.possibilities = "کلیه خدمات عضویت طلایی|برنامه های علمی و فرهنگی|سرتیفیکیت ویژه داروینو|ست لوازم آزمایشگاهی|پکیج آموزشی ویژه";
        darwinoLandModels.add(dlm);


        darwinoLandAdapter = new DarwinoLandAdapter(darwinoLandModels, this);
        courseRecyclerView.setAdapter(darwinoLandAdapter);

        getData();

    }

    void getData() {

//        fade();
//
//
//        Call<List<Course>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).topCourse();
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
    public void Clicked(int position) {

    }

    @Override
    public void Clicked(int position, View sharedElement, View secondElement) {

        Intent i = new Intent(getContext(), DarwinoLandActivity.class);
        i.putExtra("id", String.valueOf(position));

//        Pair<View, String> p1 = Pair.create(sharedElement, sharedElement.toString());
//        Pair<View, String> p2 = Pair.create(secondElement, secondElement.toString());
//
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this.getActivity(), p1);
        startActivity(i);

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }
}