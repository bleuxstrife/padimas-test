package com.interview.test.padimas_test.modul.login.model;

import com.google.gson.annotations.SerializedName;
import com.interview.test.padimas_test.modul.profile.model.ProfileModel;

public class LoginModel {

    @SerializedName("value")
    private ProfileModel getProfile;

    public ProfileModel getGetProfile() {
        return getProfile;
    }
}
