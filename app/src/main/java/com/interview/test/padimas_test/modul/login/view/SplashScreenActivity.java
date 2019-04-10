package com.interview.test.padimas_test.modul.login.view;

import android.os.Bundle;
import android.util.Log;

import com.interview.test.padimas_test.R;
import com.interview.test.padimas_test.modul.login.controller.LoginController;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

public class SplashScreenActivity extends BaseActivity {

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        new LoginController(getContext()).checkLogin();
        finish();
    }

    @Subscribe
    public void onEvent(BaseObject object) {
    }
}
