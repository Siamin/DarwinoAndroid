package com.ahmad.darwino.fragments.bottombar;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ahmad.darwino.CusrtomViews.NonSwipeableViewPager;
import com.ahmad.darwino.R;
import com.ahmad.darwino.fragments.BaseFragment;
import com.ahmad.darwino.fragments.ProfileHistoryFragment;
import com.ahmad.darwino.fragments.ProfileInfoFragment;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.Account;
import com.amulyakhare.textdrawable.TextDrawable;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends BaseFragment implements OnClick {

    ImageView image;
    TextView fullname;
    RelativeLayout info, course;
    boolean status_info = true, status_course = false;
    NonSwipeableViewPager viewPager;
    TabAdapter adapter ;
    Account account = null;
    public int position = -1;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Thread
        account = Account.listAll(Account.class).get(0);

        info = view.findViewById(R.id.profile_info);
        image = view.findViewById(R.id.profile_image);
        course = view.findViewById(R.id.profile_course);
        viewPager = view.findViewById(R.id.profile_view);
        fullname = view.findViewById(R.id.profile_fullname);


//        fullname.setText(account.FirstName+" "+account.LastName);

        setupViewPager(viewPager);


        SetChekedButton(true,false,R.drawable.round_button_profile_right_selected,R.drawable.backgrund_left_btn1);
        TextDrawable drawable = TextDrawable.builder().buildRoundRect("A", getResources().getColor(R.color.colorAccent), 100);
        image.setImageDrawable(drawable);



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!status_info){
                    SetChekedButton(true,false,R.drawable.round_button_profile_right_selected,R.drawable.backgrund_left_btn1);
                    viewPager.setCurrentItem(0);
                }

            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!status_course){
                    SetChekedButton(false,true,R.drawable.backgrund_righ_btn1,R.drawable.round_button_profile_left_selected);
                    viewPager.setCurrentItem(1);
                }

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        if (status_info){
            SetChekedButton(true,false,R.drawable.round_button_profile_right_selected,R.drawable.backgrund_left_btn1);
            viewPager.setCurrentItem(0);
        }else{
            SetChekedButton(false,true,R.drawable.backgrund_righ_btn1,R.drawable.round_button_profile_left_selected);
            viewPager.setCurrentItem(1);
        }

    }


    void SetChekedButton(boolean _info, boolean _course,int _binfo, int _bcourse){
        status_course = _course;
        status_info = _info;
        info.setBackground(getResources().getDrawable(_binfo));
        course.setBackground(getResources().getDrawable(_bcourse));

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new TabAdapter(getChildFragmentManager());

        ProfileInfoFragment profileInfoFragment = new ProfileInfoFragment();
        ProfileHistoryFragment profileHistoryFragment = new ProfileHistoryFragment();

        adapter.addFragments(profileInfoFragment);
        adapter.addFragments(profileHistoryFragment);

        viewPager.setAdapter(adapter);


    }

    public class TabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        public TabAdapter(FragmentManager fragmentManager) {
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

//        Intent i = new Intent(getContext(), BlogActivity.class);
//        startActivity(i);
////        i.putExtra("id", courses.get(position).id);
////
////        Pair<View, String> p1 = Pair.create(sharedElement, sharedElement.toString());
////        Pair<View, String> p2 = Pair.create(secondElement, secondElement.toString());
////
////        ActivityOptionsCompat options = ActivityOptionsCompat.
////                makeSceneTransitionAnimation(this.getActivity(), p1);
////        startActivity(i, options.toBundle());

    }

}