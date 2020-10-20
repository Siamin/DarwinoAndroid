package com.ahmad.darwino.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmad.darwino.interfaces.Login;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;
import com.ahmad.darwino.network.mdoels.AccountModel;
import com.ahmad.darwino.network.mdoels.InputLoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity implements Login {


    public String MY_TAG = "TAG_BASE_ACTIVITY";
    protected Account account = null;
    public boolean shouldBackOnLogin = true;
    protected ConstraintLayout container;
    protected ProgressBar progressBar;
    private float fadedAlpha = 0.35f;

    protected void shortToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void longToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void inputFieldToast(String fieledName){
        String message = "لطفا " + fieledName + " را وارد کنید";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void internetError(){
        Toast.makeText(this, "لطفا اینترنت خود را وصل کنید.", Toast.LENGTH_LONG).show();
    }

    protected void gotoRegister(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    protected void gotoLogin(){
        Intent i = new Intent(this, LoginActivity.class);
        if(shouldBackOnLogin){
            i.putExtra("back", true);
            startActivity(i);
        }
        else {
            i.putExtra("back", false);
            startActivity(i);
            finish();
        }

    }

    protected void gotoSmsCode(){
        Intent i = new Intent(this, SmsActivity.class);
        if(shouldBackOnLogin){
            i.putExtra("back", true);
            startActivity(i);
        }
        else {
            i.putExtra("back", false);
            startActivity(i);
            finish();
        }

    }

    protected void login(){
        login(true);
    }

    protected void login(final boolean startLoginActivity) {

        fade();

        InputLoginModel model = new InputLoginModel();
        model.Password = account.Password;
        model.PhoneNumber = account.PhoneNumber;

        Call<AccountModel> request = RequestBuilderClass.retrofit.create(RequestBuilder.class).login(model);

        request.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {

                Log.i(MY_TAG, "login response:");

                AccountModel result = response.body();

                Log.i(MY_TAG, "login code:"+result.code);

                if(result.code == 0){
                    account.LoginToken = result.loginToken;
                    account.save();
                    loggedIn();
                    Log.d(MY_TAG, "logged in");
                }
                else if (result.code == 1){
                    account.LoginToken = result.loginToken;
                    account.save();
                    gotoSmsCode();
                    shortToast("required authentication");
                    Log.d(MY_TAG, "required authentication");
                }
                else {
                    Log.d(MY_TAG, result.message);
                    if(startLoginActivity){
                        gotoLogin();
                    }
                    else {
                        loginFailed(result.message);
                    }
                }

                unFade();

            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                unFade();
                internetError();
                System.out.println("networkerror:"+t.getMessage());
                Log.d(MY_TAG, t.getMessage());
            }
        });

    }

    protected void fade(){
        if(container != null && progressBar != null){

            container.setEnabled(false);
            for ( int i = 0; i < container.getChildCount();  i++ ){
                View view = container.getChildAt(i);
                view.setEnabled(false); // Or whatever you want to do with the view.
            }


            container.setAlpha(fadedAlpha);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setEnabled(true);
        }
    }

    protected void unFade(){
        if(container != null && progressBar != null){

            container.setEnabled(false);
            for ( int i = 0; i < container.getChildCount();  i++ ){
                View view = container.getChildAt(i);
                view.setEnabled(true); // Or whatever you want to do with the view.
            }

            container.setAlpha(1);
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setEnabled(false);
        }
    }

}
