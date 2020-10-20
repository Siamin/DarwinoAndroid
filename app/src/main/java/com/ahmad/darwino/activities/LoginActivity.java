package com.ahmad.darwino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.ahmad.darwino.CusrtomViews.MyButton;
import com.ahmad.darwino.CusrtomViews.MyEditText;
import com.ahmad.darwino.CusrtomViews.MyTextView;
import com.ahmad.darwino.R;
import com.ahmad.darwino.models.Account;

public class LoginActivity extends BaseActivity {


    private MyEditText phoneNumber, password;
    private  boolean isPasswordShown = false;
    String TAG = "TAG_LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MY_TAG = "LOGIN_ACTIVITY_TAG";
        account = Account.listAll(Account.class).get(0);
        shouldBackOnLogin = getIntent().getBooleanExtra("back", false);
        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);


        phoneNumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);


        MyButton login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToAccount();
            }
        });

        MyTextView register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister();
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


        findViewById(R.id.fotogt_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordPhoneNumberActivity.class);
                startActivity(i);
            }
        });

    }



    private void loginToAccount() {

        Log.i(TAG," loginToAccount 1");
        if(phoneNumber.getText().length()!= 11){
            inputFieldToast("شماره تلفن");
            return;
        }

        if(password.getText().toString().isEmpty()){
            inputFieldToast("کلمه عبور");
            return;
        }
        Log.i(TAG," loginToAccount 2");
        account.PhoneNumber = phoneNumber.getText().toString();
        account.Password = password.getText().toString();
        account.save();

        Log.i(TAG," loginToAccount 3");
        login(false);
        Log.i(TAG," loginToAccount 4");

    }

    @Override
    public void loggedIn() {

        if(shouldBackOnLogin){
            finish();
        }
        else {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }


    }

    @Override
    public void loginFailed(String message) {
        shortToast(message);
    }

    @Override
    protected void gotoSmsCode() {

        Intent i = new Intent(this, SmsActivity.class);
        if(shouldBackOnLogin){
            i.putExtra("back", true);
        }
        else {
            i.putExtra("back", false);
        }

        startActivity(i);
        finish();

    }
}
