package com.ahmad.darwino.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ahmad.darwino.R;

public class Payment_status_activity extends  BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);
    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }
}
