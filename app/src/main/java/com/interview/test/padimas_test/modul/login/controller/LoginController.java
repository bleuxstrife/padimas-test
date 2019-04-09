package com.interview.test.padimas_test.modul.login.controller;

import android.content.Context;

import com.interview.test.padimas_test.api.BaseConnection;
import com.interview.test.padimas_test.api.request.LoginRequest;
import com.interview.test.padimas_test.modul.login.model.LoginModel;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.Constant;
import com.interview.test.padimas_test.utils.PreferenceUtils;

import de.greenrobot.event.EventBus;

public class LoginController implements BaseConnection.OnRequestResultListener {

    private LoginRequest loginRequest;
    PreferenceUtils preferenceUtils;

    public LoginController(Context context){
        loginRequest = new LoginRequest(context);
        loginRequest.setOnRequestResultListener(this);
        preferenceUtils = PreferenceUtils.getInstance(context);
    }

    public void login(String email, String password){
        loginRequest.login(email, password);
    }

    @Override
    public void onRequestResultSuccessWithType(Object res, int type) {
        if(type== Constant.LOGIN) {
            LoginModel loginModel = (LoginModel) res;
            preferenceUtils.setProfile(loginModel.getGetProfile());
            preferenceUtils.setExtraProfile(loginModel.getGetProfile().getGetExtraProfile());
            preferenceUtils.setUserID(loginModel.getGetProfile().getUserId());
        }
        EventBus.getDefault().post(new BaseObject(res, type));
    }

    @Override
    public void onRequestResultError(Throwable throwable) {
        EventBus.getDefault().post(throwable);
    }

    @Override
    public void onRequestResultSuccessWithId(Object res, String id, int type) {

    }
}
