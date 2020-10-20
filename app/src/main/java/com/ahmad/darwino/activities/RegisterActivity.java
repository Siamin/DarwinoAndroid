package com.ahmad.darwino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;

import com.ahmad.darwino.CusrtomViews.MyButton;
import com.ahmad.darwino.CusrtomViews.MyEditText;
import com.ahmad.darwino.CusrtomViews.MyTextView;
import com.ahmad.darwino.R;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.AccountModel;
import com.ahmad.darwino.network.mdoels.RegisterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {


    private MyEditText phoneNumber,email,password,confirm;
    private MyButton register;
    private RegisterModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        MY_TAG = "REG?ISTER_ACTIVITY_TAG";
        account = Account.listAll(Account.class).get(0);
        shouldBackOnLogin = false;
        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);

        phoneNumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.password_confirm);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        MyTextView login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!email.getText().toString().isEmpty()){
                    email.setGravity(Gravity.LEFT);
                }
                else {
                    email.setGravity(Gravity.RIGHT);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!phoneNumber.getText().toString().isEmpty()){
                    phoneNumber.setGravity(Gravity.LEFT);
                }
                else {
                    phoneNumber.setGravity(Gravity.RIGHT);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!password.getText().toString().isEmpty()){
                    password.setGravity(Gravity.LEFT);
                }
                else {
                    password.setGravity(Gravity.RIGHT);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void register() {

        if(phoneNumber.getText().toString().isEmpty()){
            inputFieldToast("شماره تلفن");
            return;
        }

        if(email.getText().toString().isEmpty()){
            inputFieldToast("ایمیل");
            return;
        }

        if(password.getText().toString().isEmpty()){
            inputFieldToast("کلمه عبور");
            return;
        }

        fade();

        model = new RegisterModel();
        model.Email = email.getText().toString();
        model.Password = password.getText().toString();
        model.PhoneNumber = phoneNumber.getText().toString();
        model.ConfirmPassword = password.getText().toString();

        Call<AccountModel> request = RequestBuilderClass.retrofit.create(RequestBuilder.class).register(model);

        request.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {

                if(response.isSuccessful()) {


                    AccountModel result = response.body();

                    shortToast(String.valueOf(result.code));

                    if (result.code != 0) {
                        shortToast(result.message);
                    } else {
                        Account account = Account.listAll(Account.class).get(0);
                        account.FirstName = model.FirstName;
                        account.LastName = model.LastName;
                        account.Email = model.Email;
                        account.Password = model.Password;
                        account.PhoneNumber = model.PhoneNumber;
                        account.LoginToken = result.loginToken;
                        account.save();
                        shortToast("saved");

                        Intent i = new Intent(RegisterActivity.this, SmsActivity.class);
                        startActivity(i);
                        finish();

                    }

                }
                else {
                    internetError();
                }

                unFade();

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                unFade();
                shortToast("خطا در اینترنت");
            }
        });

    }


    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }
}
