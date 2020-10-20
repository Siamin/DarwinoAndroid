package com.ahmad.darwino.activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahmad.darwino.PersianCalendar;
import com.ahmad.darwino.R;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.Course;
import com.ahmad.darwino.network.mdoels.SimpleResponse;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends BaseActivity {


    int courseId;
    Course course;
    Toolbar toolbar;
    PersianCalendar persianCalendar = new PersianCalendar();

    enum State {
        COURSE_DATA,
        REGISTER
    }

    String TAG = "TAG_CourseActivity";
    State state;
    View content_container;
    ImageView share, ImageCourse;
    RelativeLayout register;
    TextView course_name, course_description, course_date, course_capacity, course_price, course_duration, teacher_name;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        init();
        getData();

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, course.name + "\n\n"
                        + course.description.replace("<br/>", "\n")
                        + course.LinkePage);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseActivity.this, PaymentActivity.class));
            }
        });

//        findViewById(R.id.toolbar_layout).seton


    }

    void init() {

        MY_TAG = "COURSE_ACTIVITY_TAG";
        //account = Account.listAll(Account.class).get(0);


        toolbar = (Toolbar) findViewById(R.id.toolbar_course);
//        toolbar.inflateMenu(R.menu.menu_scrolling);


        course_name = findViewById(R.id.course_name);
        course_description = findViewById(R.id.course_description);
        course_date = findViewById(R.id.course_date);
        course_capacity = findViewById(R.id.course_capacity);
        course_price = findViewById(R.id.course_price);
        course_duration = findViewById(R.id.course_duration);
        teacher_name = findViewById(R.id.teacher_name);
        register = findViewById(R.id.course_activity_register);
        ImageCourse = findViewById(R.id.course_image);

        share = findViewById(R.id.course_share);

//        progressBar = findViewById(R.id.progressBar);
//        container = findViewById(R.id.container);


        courseId = getIntent().getIntExtra("id", -1);

        if (courseId == -1) {
            onBackPressed();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        content_container = findViewById(R.id.content_container);

        Animation animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        content_container.startAnimation(animationDown);

    }

    void getData() {

        state = State.COURSE_DATA;

        //fade();

        Call<Course> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).getCourse(courseId);

        client.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {

                //unFade();
                if (response.code() == 200) {
                    course = response.body();
                    String DatePertion = "";

                    try {
                        String[] DateMiladi = course.startingTime.split("T")[0].split("-");
                        DatePertion = persianCalendar.MiladiToJalaliByMonthNames(Integer.parseInt(DateMiladi[0]), Integer.parseInt(DateMiladi[1]), Integer.parseInt(DateMiladi[2]));
                    } catch (Exception e) {
                        Log.i(TAG, e.toString());
                    }
                    course_name.setText(course.name);
                    course_description.setText(course.description.replace("<br/>", ""));
                    course_date.setText("تاریخ شروع: " + DatePertion);
                    //course_capacity.setText("ظرفیت: "+course.);
                    teacher_name.setText("مدرس: " + course.getTeachers());
                    course_price.setText(String.valueOf(course.price));
                    //course_duration.setText(""+course.);
                    toolbar.setTitle(course.name.replace("دوره", ""));
                    Picasso.with(getApplicationContext()).load("http://darwino.ir/Image/ById/76").into(ImageCourse);

                    updateView();
                } else {
                    internetError();
                }

            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                //unFade();
                internetError();
            }
        });

    }

    void updateView() {

    }

    void register() {

        state = State.REGISTER;

        fade();

        Call<SimpleResponse> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).courseRegister(courseId);

        client.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                unFade();

                if (response.code() == 200) {
                    SimpleResponse result = response.body();

                    if (result.code == -2) {
                        login();
                    } else if (result.code == -1) {
                        shortToast(result.message);
                    } else if (result.code == 1) {
                        shortToast(result.message);
                    } else if (result.code == 2) {
                        shortToast(result.message);
                        // goto payment
                    } else if (result.code == 0) {
                        shortToast(result.message);
                        // goto payment
                    }

                } else {
                    internetError();
                }

            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                unFade();
            }
        });

    }

    @Override
    public void loggedIn() {
        if (state == State.COURSE_DATA) {
            getData();
        } else if (state == State.REGISTER) {
            register();
        }

    }

    @Override
    public void loginFailed(String message) {

    }


}
