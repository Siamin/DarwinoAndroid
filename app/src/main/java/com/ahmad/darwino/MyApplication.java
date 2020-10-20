package com.ahmad.darwino;

import android.app.Application;

import com.ahmad.darwino.helpers.TypefaceUtil;
import com.orm.SugarApp;

public class MyApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "serif", "fonts/irsans.ttf");

    }

}
