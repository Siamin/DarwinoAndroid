package com.ahmad.darwino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ahmad.darwino.CusrtomViews.MyButton;
import com.ahmad.darwino.CusrtomViews.MyEditText;
import com.ahmad.darwino.R;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.AccountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPhoneNumberActivity extends BaseActivity {

    MyEditText phoneNumber;
    MyButton sendSms;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_phone_number);


        MY_TAG = "CHANGE_PASSWORD_PHONE_NUMBER_ACTIVITY_TAG";
        account = Account.listAll(Account.class).get(0);

        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);

        phoneNumber = findViewById(R.id.phonenumber);
        sendSms = findViewById(R.id.send_sms);
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPasswordPhoneNumberActivity.super.onBackPressed();
            }
        });

    }

    private void sendSms() {

        phone = phoneNumber.getText().toString();

        if(phone.isEmpty()){
            inputFieldToast("شماره تلفن");
            return;
        }

        shortToast(phone);

        if(phone.length()<10){
            shortToast("لطفا شماره تلفن را به صورت صحیح وارد کنید.");
            return;
        }

        fade();
        Call<AccountModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).forgotPasswordSms(phone);
        client.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                unFade();

                AccountModel result = response.body();

                if(result.code == 0){

                    Intent i = new Intent(ForgotPasswordPhoneNumberActivity.this, SmsActivity.class);
                    i.putExtra("ChangePassword", true);
                    i.putExtra("Phone", phone);
                    startActivity(i);
                    finish();
                }
                else {
                    shortToast(result.message);
                }

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                unFade();
                internetError();
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
