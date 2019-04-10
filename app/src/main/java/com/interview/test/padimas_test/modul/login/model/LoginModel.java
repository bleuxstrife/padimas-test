package com.interview.test.padimas_test.modul.login.model;

import com.google.gson.annotations.SerializedName;
import com.interview.test.padimas_test.modul.profile.model.ProfileModel;

import java.util.List;

public class LoginModel {

    @SerializedName("value")
    private List<ProfileModel> getProfile;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public List<ProfileModel> getGetProfile() {
        return getProfile;
    }
}
