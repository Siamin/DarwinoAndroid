package com.ahmad.darwino.activities;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.ahmad.darwino.CusrtomViews.MyButton;
import com.ahmad.darwino.CusrtomViews.MyEditText;
import com.ahmad.darwino.R;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.AccountModel;
import com.ahmad.darwino.network.mdoels.InputSmsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsActivity extends BaseActivity {

    private MyEditText smsInput;
    private boolean isChangePassword;
    private String phone;
    private MyButton resendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        MY_TAG = "SMS_CODE_ACTIVITY_TAG";

        shouldBackOnLogin = getIntent().getBooleanExtra("back", false);
        account = Account.listAll(Account.class).get(0);
        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);

        phone = getIntent().getStringExtra("Phone");
        if(phone == null || phone.isEmpty()){
            phone = account.PhoneNumber;
        }

        isChangePassword = getIntent().getBooleanExtra("ChangePassword", false);

        smsInput = findViewById(R.id.smscode);
        MyButton verify = findViewById(R.id.verify_sms);
        resendSms = findViewById(R.id.resend_code);
        resendSms.setClickable(false);

        resendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendSms.isClickable()){
                    resendCode();
                }
            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChangePassword){
                    sendForgotPasswordSms();
                }
                else {
                    verify();
                }
            }
        });

        startTimer();

    }

    private void resendCode() {

        fade();
        Call<AccountModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).forgotPasswordSms(phone);
        client.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {

                unFade();
                startTimer();
                AccountModel result = response.body();
                if(result.code == 0){
                    shortToast("کد به دستگاه شما ارسال شد");
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

    private void startTimer(){

        resendSms.setClickable(false);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            resendSms.setBackgroundDrawable(ContextCompat.getDrawable(SmsActivity.this, R.drawable.round_gray_color_button) );
        } else {
            resendSms.setBackground(ContextCompat.getDrawable(SmsActivity.this, R.drawable.round_gray_color_button));
        }


        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                resendSms.setText("ارسال مجدد(" + millisUntilFinished / 1000 + ")");
            }

            public void onFinish() {

                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    resendSms.setBackgroundDrawable(ContextCompat.getDrawable(SmsActivity.this, R.drawable.round_primary_color_button) );
                } else {
                    resendSms.setBackground(ContextCompat.getDrawable(SmsActivity.this, R.drawable.round_primary_color_button));
                }

                resendSms.setClickable(true);
                resendSms.setText("ارسال مجدد");

            }
        }.start();


    }

    private void sendForgotPasswordSms() {

        final String code = smsInput.getText().toString();

        if(code.isEmpty()){
            inputFieldToast("کد");
            return;
        }

        InputSmsModel model = new InputSmsModel();
        model.Code = code;
        model.PhoneNumber = phone;

        Call<AccountModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).forgotPasswordCheckSms(model);

        client.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                unFade();

                AccountModel result = response.body();

                if(result.code == 0){
                    Intent i  = new Intent(SmsActivity.this, ChangePasswordActivity.class);
                    i.putExtra("Phone", phone);
                    i.putExtra("Code", code);
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

    private void verify() {

        if(smsInput.getText().toString().isEmpty()){
            inputFieldToast("کد");
            return;
        }

        fade();

        Call<AccountModel> request = RequestBuilderClass.retrofit.create(RequestBuilder.class).verifySms("Bearer " + account.LoginToken, smsInput.getText().toString());

        request.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {

                if(response.code() == 200){
                    AccountModel model = response.body();
                    Log.d(MY_TAG, "code:"+model.code);

                    if(model.code == 0){
                        account.LoginToken = model.loginToken;
                        account.save();
                        if(shouldBackOnLogin){
                            finish();
                        }
                        else {
                            gotoMainActivity();
                        }
                    }
                    else if(model.code == -2) {
                        Log.d(MY_TAG, "user not found");
                        gotoLogin();
                    }
                    else if(model.code == -1){
                        Log.d(MY_TAG, "code not found");
                        //gotoLogin();
                    }
                    else
                    {
                        Log.d(MY_TAG, model.message);
                        shortToast(model.message);
                    }

                }
                else {
                    Log.d(MY_TAG, "error code:"+response.code());
                    gotoLogin();
                }

                unFade();

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                unFade();
                Log.d(MY_TAG, "error:"+t.getMessage());
                internetError();
            }
        });

    }

    protected void gotoMainActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

}
