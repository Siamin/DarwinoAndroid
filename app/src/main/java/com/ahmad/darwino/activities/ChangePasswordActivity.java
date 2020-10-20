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
import com.ahmad.darwino.network.mdoels.InputForgotChangePassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    private MyEditText passwordInput,confirmPasswordInput;
    private String code,phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        MY_TAG = "CHANGE_PASSWORD_ACTIVITY_TAG";
        account = Account.listAll(Account.class).get(0);
        shouldBackOnLogin = getIntent().getBooleanExtra("back", false);
        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);

        passwordInput = findViewById(R.id.password);
        confirmPasswordInput = findViewById(R.id.second_password);

        code = getIntent().getStringExtra("Code");
        phoneNumber = getIntent().getStringExtra("Phone");

        if (code == null || phoneNumber == null) {
            super.onBackPressed();
        }

        if(code.isEmpty() || phoneNumber.isEmpty()){
            super.onBackPressed();
        }

        findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

    }

    private void changePassword() {
        final String password = passwordInput.getText().toString();
        String confirmPassword = confirmPasswordInput.getText().toString();

        if(password.isEmpty()){
            inputFieldToast("کلمه عبور");
            return;
        }

        if(confirmPassword.isEmpty()){
            inputFieldToast("تکرار کلمه عبور");
            return;
        }

        if(!password.equals(confirmPassword)){
            shortToast("کلمه عبور و تکرار آن یکسان نیستند");
            return;
        }

        InputForgotChangePassword model = new InputForgotChangePassword();
        model.Code = code;
        model.Password = password;
        model.ConfirmPassword = confirmPassword;
        model.PhoneNumber = phoneNumber;

        Call<AccountModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).forgotPasswordChangePassword(model);

        client.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {

                unFade();

                AccountModel result = response.body();

                if(result.code == 0){
                    account.Password = password;
                    account.PhoneNumber = phoneNumber;
                    account.LoginToken = result.loginToken;
                    account.save();
                    shortToast("کلمه عبور با موفقیت تغییر کرد");
                    Intent i = new Intent(ChangePasswordActivity.this, HomeActivity.class);
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
