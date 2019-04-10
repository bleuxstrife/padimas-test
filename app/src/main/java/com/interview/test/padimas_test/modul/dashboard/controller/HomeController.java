package com.interview.test.padimas_test.modul.dashboard.controller;

import android.content.Context;

import com.interview.test.padimas_test.api.BaseConnection;
import com.interview.test.padimas_test.api.request.HomeRequest;
import com.interview.test.padimas_test.modul.profile.model.ProfileModel;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

public class HomeController implements BaseConnection.OnRequestResultListener {

    HomeRequest homeRequest;
    PreferenceUtils preferenceUtils;

    public HomeController(Context context){
        homeRequest = new HomeRequest(context);
        homeRequest.setOnRequestResultListener(this);
        preferenceUtils = PreferenceUtils.getInstance(context);
    }

    public ProfileModel getProfile(){
        ProfileModel model;

        model = preferenceUtils.getProfile("");
        if (model.equals("")) {
            return new ProfileModel();
        }

        return model;
    }

    public void logout(){
        preferenceUtils.setExtraProfile(null);
        preferenceUtils.setProfile(null);
        preferenceUtils.setUserID("");
    }

    public void getCar(){
        homeRequest.getCar();
    }

    @Override
    public void onRequestResultSuccessWithType(Object res, int type) {
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
