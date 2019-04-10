package com.interview.test.padimas_test.modul.login.controller;

import android.content.Context;
import android.content.Intent;

import com.interview.test.padimas_test.api.BaseConnection;
import com.interview.test.padimas_test.api.request.LoginRequest;
import com.interview.test.padimas_test.modul.dashboard.view.DashboardActivity;
import com.interview.test.padimas_test.modul.login.model.LoginModel;
import com.interview.test.padimas_test.modul.login.view.LoginActivity;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.Constant;
import com.interview.test.padimas_test.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;


public class LoginController implements BaseConnection.OnRequestResultListener {
    private Context context;
    private LoginRequest loginRequest;
    PreferenceUtils preferenceUtils;

    public LoginController(Context context) {
        this.context = context;
        loginRequest = new LoginRequest(context);
        loginRequest.setOnRequestResultListener(this);
        preferenceUtils = PreferenceUtils.getInstance(context);
    }

    public void checkLogin() {
        if (!preferenceUtils.getUserID("").isEmpty()) {
            context.startActivity(new Intent(context, DashboardActivity.class));
        } else {
            context.startActivity(new Intent(context, LoginActivity.class));
        }

    }

    public void login(String email, String password) {
        loginRequest.login(email, password);
    }

    @Override
    public void onRequestResultSuccessWithType(Object res, int type) {
        if (type == Constant.LOGIN) {
            LoginModel loginModel = (LoginModel) res;
            preferenceUtils.setProfile(loginModel.getGetProfile().get(0));
            preferenceUtils.setExtraProfile(loginModel.getGetProfile().get(0).getGetExtraProfile());
            preferenceUtils.setUserID(loginModel.getGetProfile().get(0).getUserId());
            EventBus.getDefault().post(new BaseObject(res, type));
        } else if(type==400){
            EventBus.getDefault().post(new BaseObject(res, type));
        }

    }

    @Override
    public void onRequestResultError(Throwable throwable) {
        EventBus.getDefault().post(throwable);
    }


    @Override
    public void onRequestResultSuccessWithId(Object res, String id, int type) {

    }
}
