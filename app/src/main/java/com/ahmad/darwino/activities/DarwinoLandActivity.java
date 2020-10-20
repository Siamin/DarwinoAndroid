package com.ahmad.darwino.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmad.darwino.R;
import com.ahmad.darwino.network.mdoels.DarwinoLandModel;

import java.util.ArrayList;
import java.util.List;

public class DarwinoLandActivity extends BaseActivity {


    List<DarwinoLandModel> darwinoLandModels = new ArrayList<DarwinoLandModel>();
    String TAG = "TAG_DarwinoLandActivity";
    Button register;
    TextView title, subject, price, discription, possibilities;
    ImageView image_subject, back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darwinoland);


        register = findViewById(R.id.activity_darwino_register);
        back = findViewById(R.id.activity_darwino_back);
        title = findViewById(R.id.activity_darwino_title);
        price = findViewById(R.id.activity_darwino_price);
        subject = findViewById(R.id.activity_darwino_subject);
        discription = findViewById(R.id.activity_darwino_discription);
        possibilities = findViewById(R.id.activity_darwino_possibilities);
        image_subject = findViewById(R.id.activity_darwino_image);


        Bundle bundle = getIntent().getExtras();

        getData(Integer.parseInt(bundle.getString("id")));


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DarwinoLandActivity.this,HomeActivity.class));
                finish();
            }
        });


    }


    void getData(int position) {

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

        //******************************************************************************************
        //************************************ Set Data to Objects *********************************
        //******************************************************************************************

        title.setText(darwinoLandModels.get(position).name);
        subject.setText(darwinoLandModels.get(position).name);
        price.setText("" + Number_to_letters(darwinoLandModels.get(position).price,""));
        discription.setText(darwinoLandModels.get(position).description);
        image_subject.setImageDrawable(getResources().getDrawable(darwinoLandModels.get(position).image_id));

        possibilities.setText((darwinoLandModels.get(position).possibilities).replace("|","\n\n"));


    }

    String Number_to_letters(int price, String unit) {

        int newprice = price / 1000;

        if (newprice > 1){
            if (unit.equals("")){
                unit = " هزار ";
            }else if (unit.equals(" هزار ")){
                unit = " میلیون ";
            }else if (unit.equals(" میلیون ")){
                unit = " میلیارد ";
            }
            return Number_to_letters(newprice,unit);
        }else{
            return String.valueOf(price)+unit+"تومان";
        }




    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

}
