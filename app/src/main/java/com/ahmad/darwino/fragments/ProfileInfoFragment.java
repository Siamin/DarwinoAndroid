package com.ahmad.darwino.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ahmad.darwino.R;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoFragment extends BaseFragment {


    EditText firstname, lastname, email, phone, password, new_password;
    Button changeInfo, changePassword;
    Account account = null;


    public ProfileInfoFragment() {
        // Required empty public constructor
    }


    public int position = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("fragment:",String.valueOf(position));

        account = Account.listAll(Account.class).get(0);


        firstname = view.findViewById(R.id.profile_first_name);
        lastname = view.findViewById(R.id.profile_last_name);
        email = view.findViewById(R.id.profile_email);
        phone = view.findViewById(R.id.profile_phone);
        password = view.findViewById(R.id.profile_password);
        new_password = view.findViewById(R.id.profile_new_password);

        changeInfo = view.findViewById(R.id.profile_change_user_info);
        changePassword = view.findViewById(R.id.profile_change_password);


        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        getData();

    }

    void getData() {

        firstname.setText(account.FirstName);
        lastname.setText(account.LastName);
        email.setText(account.Email);
        phone.setText(account.PhoneNumber);

//        Call<String> request = RequestBuilderClass.retrofit.create(RequestBuilder.class).IsTokenValid("Bearer " + account.LoginToken);
//
//        request.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i(MY_TAG, "response :"+response.body());
//
//            }
//
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i(MY_TAG, "error catched:"+t.getMessage());
//                internetError();
//                System.out.println("networkerror:"+t.getMessage());
//
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
