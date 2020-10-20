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
import com.ahmad.darwino.activities.BlogActivity;
import com.ahmad.darwino.adapters.BlogsAdapter;
import com.ahmad.darwino.fragments.BaseFragment;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.FastAccessModel;
import com.ahmad.darwino.network.mdoels.BlogModel;
import com.ahmad.darwino.network.mdoels.Course;
import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends BaseFragment implements OnClick {

    OnClick fastAccessClick;
    RecyclerView courseRecyclerView;
    BlogsAdapter Adapter;
    List<BlogModel> blogModels = new ArrayList<>();

    String TAG = "TAG_BlogFragment";

    public BlogFragment() {
        // Required empty public constructor
    }


    public int position = -1,page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView fastAccessRecyclerView = view.findViewById(R.id.fast_access_recyclerView);

        List<FastAccessModel> fastAccessModels = new ArrayList<>();

        courseRecyclerView = view.findViewById(R.id.fragment_blog_recyclerView);


        getData();

        LinearLayoutManager courseLayoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(courseLayoutManager);
        Adapter = new BlogsAdapter(blogModels, this);
        courseRecyclerView.setAdapter(Adapter);



    }

    void getData() {

        blogModels.clear();

        BlogModel bm = new BlogModel();

        bm.id = 0;
        bm.Title = "روز جهانی هموفیلی";
        bm.shortDescription = "روز جهانی هموفیلی در سال ۱۹۸۹ و توسط فدراسیون جهانی هموفیلی WFH نامگذاری شد. و به احترام فرانک اشنابل (Frank Schnabel) موسس فدراسیون جهانی هموفیلی ۱۷ آوریل  روز تولد وی به عنوان روزجهانی هموفیلی در نظر گرفته شد.";
        bm.description = " روز جهانی هموفیلی در سال ۱۹۸۹ و توسط فدراسیون جهانی هموفیلی WFH نامگذاری شد. و به احترام فرانک اشنابل (Frank Schnabel) موسس فدراسیون جهانی هموفیلی ۱۷ آوریل  روز تولد وی به عنوان روزجهانی هموفیلی در نظر گرفته شد.\n" +
                "\n" +
                "حالا بهتره که بدونیم هموفیلی چه نوع بیماری هست؟\n" +
                "\n" +
                "هموفیلی یا خون تراوی یک اختلال نادر هست و جز بیماری های ارثی. خون افراد مبتلا بعد از جراحت و پاره شدن رگ بطور طبیعی لخته نمیشه و انعقاد خون مختل میشه. هموفیلی انواع مختلف داره، نوع A و هموفیلی نوع B که این دو نوع، اختلالات انعقادی وابسته به کروموزوم  جنسی و هموفیلی نوع C یک نوع اختلال ژنتیکی آتوزومال هستن.\n" +
                "\n" +
                "مراسم بزرگداشت روز جهانی هموفیلی  در بعد از ظهر روز ۲۹ فروردین ماه در هتل المپیک برگزار می شود. لازم به ذکر است همه ساله ۱۷ اوریل مصادف با ۲۸ فروردین ماه روز جهانی هموفیلی با شعارهای محوری فدراسیون جهانی هموفیلی برگزار و به مناسبت این روز بناهای تاریخی و نماد های شهری در کشور های مختلف جهان به رنگ قرمز در می آید.";


        bm.url_iamge = "http://darwino.ir/image/ById/1202?size=140x140";
        blogModels.add(bm);
        //******************************************************************************************

        bm = new BlogModel();

        bm.id = 0;
        bm.Title = "دستکاری DNA تخلیص شده 2";
        bm.shortDescription ="کلونینگ دو هدف عمده دارد، اول اینکه امکان تولید مقدار زیادی مولکول های DNA نوترکیب از مقدار محدودی ماده اولیه را فراهم می کند و دومین هدف ، تخلیص است.";
        bm.description = "در مطلب هفته ی گذشته درباره ی نحوه دستکاری DNA و آنزیم های مورد استفاده حرف زدیم ، در این هفته به توضیح کلونینگ که وارد کردن این مولکول ها به سلول های زنده که معمولا باکتری هستند می پردازیم.کلونینگ دو هدف عمده دارد، اول اینکه امکان تولید مقدار زیادی مولکول های DNA نوترکیب از مقدار محدودی ماده اولیه را فراهم می کند و دومین هدف ، تخلیص است.\n" +
                "\n" +
                "هر باکتری که یک پلاسمید برداشته است متعاقبا بارها تقسیم می شود و یک کلونی تشکیل می دهد که هر سلول آن حاوی چندین کپی از مولکول DNA نوترکیب است.\n" +
                "\n" +
                "باکتری ها به طور طبیعی به مقدار محدودی توانایی برداشت DNA را دارند ، برای همین نیاز است تحت عوامل فیزیکی و شیمیایی ، این توانایی در آن ها افزایش یابد . بدین منظور می توان با شوک حرارتی میزان نفوذپذیری غشا نسبت به DNA را بالا برد .\n" +
                "\n" +
                "برای انتخاب سلول های ترانسفورم شده می توان از قابلیت حساسیت Ecoli به آنتی بیوتیک هایی مانند آمپی سیلین یا تتراسایکلین استفاده کرد ، زیرا سلول هایی که حامل پلاسمید مورد نظر هستند می توانند در محیط کشت آغشته با آنتی بیوتیک مقاوم باشند . در نتیجه سلول هایی که پلاسمید را دریافت نکردند در این محیط کلونی تشکیل نمی دهند. در اینجا این نکته هائز اهمیت است که سلول ها بلافاصله بعد از شوک حرارتی در میط کشت قرار نگیرند، زیرا ژن ها های مقاوم به آنتی بیوتیک باید زمان کافی را برای بیان شدن داشته باشند.\n" +
                "\n" +
                "کشت روی محیط کشت انتخابی امکان شناسایی سلول های ترانسفورم شده از سلول های ترانسفورم نشده را فراهم می کند . هم اکنون مشکل دیگر تمایز بین کلونی های تراسفورم شده با وکتور های نوترکیب و سلول های دارای وکتور که بدون ورود DNA خارجی دوباره به هم متصل شده اند، خواهد بود.\n" +
                "\n" +
                "در بیشتر وکتور های کلونینگ ورود قطعه DNA به پلاسمید باعث از بین رفتن پیوستگی یکی از ژن ها موجود در پلاسمید و غیر فعال شدن آن ژن خواهد شد . بنابراین ریکامبینت ها ( سلول های حاوی DNA نوترکیب) قابل شناسایی هستند زیرا خصوصیتی که به وسیله ی ژن غیر فعال شده ایجاد میشده دیگر به وسیله سلول های میزبان نشان داده نمی شود .\n" +
                "\n" +
                "برای مثال پلاسمید PBR322 چندین محل شناسایی منحصر به فرد برای آنزیم های محدودالاثر دارد که با استفاده آن ها می توان وکتور را قبل از ورود قطعه جدید DNA باز کرد . برای مثال BamHI پلاسمید PBR322 را فقط در یک محل برش می دهد که در گروه ژنی کد کننده مقاومت به تتراسایکلین قرار دارد .\n" +
                "\n" +
                "مولکول PBR322 نوترکیب که یک قطعه اضافی DNA را در محل BamHI حمل میکند ، دیگر نمیتواند مقاومت به تتراسایکلین در میزبان خود ایجاد کند ، چون با ورود DNA اضافی یکی از ژن های ضروری تخریب شده است . سلول های دادرای این مولکول PBR322 نوترکیب هنوز نسبت به آمپی سیلین مقاوم هستند اما نسبت به تتراسایکلین حساس اند .\n" +
                "\n" +
                "غربالگری برای پلاسمید های PBR322 نوترکیب به صورت زیر انجام می شود .\n" +
                "\n" +
                "بعد از ترنسفورماسیون DNA ، سلول ها را روی محیط حاوی آمپی سیلین کشت داده تا کلونی ها ظاهر شوند . همه ی این کلونی ها دارای سلول های ترانسفورم شده هستند و بنابراین در برابر آمپی سیلین مقاومت دارند .اما فقط تعداد کمی از کلونی ها دارای مولکول  PBR322 نوترکیب بوده و بیشتر آن ها دارای پلاسمید های طبیعی هستند که بدون قطعه DNA دوباره به هم متصل شده اند .\n" +
                "\n" +
                "بعد از انکوباسیون تعدادی از کلونی های اولیه دوباره رشد کرده یک سری دیگر رشد نمی کنند . آنهایی که دوباره رشد کردند یک PBR322 طبیعی بدون قطعه DNA دخولی را حمل می کنند و بنابراین مجموعه ژنی مقاومت به تتراسایکلین فعال دارند. کلونی هایی که روی آگار حاوی تتراسایکلین رشد نکرده اند ،دارای DNA نوترکیب می باشند . در هفته بعد به ادامه ی این موضوع می پردازیم.";
        bm.url_iamge = "http://darwino.ir/image/ById/1204?size=140x140";
        blogModels.add(bm);


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
    public void Clicked(int position) {

    }

    @Override
    public void Clicked(int position, View sharedElement, View secondElement) {

        Intent i = new Intent(getContext(), BlogActivity.class);
        startActivity(i);
//        i.putExtra("id", courses.get(position).id);
//
//        Pair<View, String> p1 = Pair.create(sharedElement, sharedElement.toString());
//        Pair<View, String> p2 = Pair.create(secondElement, secondElement.toString());
//
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this.getActivity(), p1);
//        startActivity(i, options.toBundle());

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }
}