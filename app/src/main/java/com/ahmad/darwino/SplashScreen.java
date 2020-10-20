package com.ahmad.darwino;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.ahmad.darwino.activities.BaseActivity;
import com.ahmad.darwino.activities.CourseListActivity;
import com.ahmad.darwino.activities.HomeActivity;
import com.ahmad.darwino.models.Account;
import com.ahmad.darwino.network.RequestBuilder;
import com.ahmad.darwino.network.RequestBuilderClass;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends BaseActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2200;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission
            .WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
    String TAG = "TAG_SplashScreen";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MY_TAG = "SPLASH_ACTIVITY_TAG";
        shouldBackOnLogin = false;

        float d = getResources().getDisplayMetrics().density;
        shortToast("display:"+d);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // Permission is not granted
            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.INTERNET)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.INTERNET},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
        } else {
            shortToast("permission is already granted");
            checkDatabase();
            //gotoLogin();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkDatabase();
                } else {
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void checkDatabase() {

        List<Account> accounts = Account.listAll(Account.class);

        Log.i(TAG,accounts.size()+"");
        //shortToast(String.valueOf(accounts.size()));

        if(accounts == null || accounts.size() < 1){

            account = new Account();
            account.save();

            Log.d(TAG, "going to register");


            gotoRegister();

        }

        else {
            account = accounts.get(0);
            if(account.LoginToken.isEmpty()){

                if(!account.PhoneNumber.isEmpty() && !account.Password.isEmpty()){
                    //shortToast("going to login method");
                    Log.d(MY_TAG, "going to login method");
                    login();
                }
                else {
                    //shortToast("going to register");
                    Log.d(MY_TAG, "going to register");
                    gotoRegister();
                }

            }
            else {
                shortToast("going to checkLogin method");
                Log.d(MY_TAG, "going to checkLogin method");
                checkLogin();
            }
        }
    }

    protected void checkLogin() {
        Call<String> request = RequestBuilderClass.retrofit.create(RequestBuilder.class).IsTokenValid("Bearer " + account.LoginToken);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 401){
                    shortToast("needs login:"+401);
                    gotoLogin();
                }
                else if (response.code() == 200){
                    Log.d(MY_TAG, response.body());
                    Log.d(MY_TAG, "code:"+response.code());
                    if(response.body().equals("true")){
                        Log.d(MY_TAG, "going to main activity");
                        gotoMainActivity();
                    }
                    else {
                        Log.d(MY_TAG, "going to sms activity");
                        gotoSmsCode();
                    }

                }
                else {
                    internetError();
                    Log.d(MY_TAG, "network error");
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(MY_TAG, "error catched:"+t.getMessage());
                internetError();
                System.out.println("networkerror:"+t.getMessage());

            }
        });
    }

    @Override
    public void loggedIn() {
        Log.d(MY_TAG, "logged in");
        gotoMainActivity();
    }

    @Override
    public void loginFailed(String meesage) {

    }

    protected void gotoMainActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void internetError() {
        super.internetError();
        gotoLogin();
    }



}
