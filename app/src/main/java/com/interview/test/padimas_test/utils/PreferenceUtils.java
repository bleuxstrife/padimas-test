package com.interview.test.padimas_test.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.interview.test.padimas_test.modul.profile.model.ExtraProfileModel;
import com.interview.test.padimas_test.modul.profile.model.ProfileModel;

import java.util.Set;

public class PreferenceUtils {
    private static Context context;

    private static PreferenceUtils mInstance = null;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private static final String PADIMAS_SHARED_PREF = "padimas_pref";
    private static final String KEY_PROFILE = "profile";
    private static final String KEY_EXTRA_PROFILE = "extra_profile";
    private static final String KEY_USER_ID = "user_id";


    public static synchronized PreferenceUtils getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new PreferenceUtils();
        }
        context = ctx;
        mSharedPreferences = context.getSharedPreferences(PADIMAS_SHARED_PREF, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.apply();
        return mInstance;
    }

    public void setProfile(ProfileModel model){
        Gson gson = new Gson();
        String json = gson.toJson(model);
        mEditor.putString(KEY_PROFILE, json);
        mEditor.commit();
    }

    public ProfileModel getProfile(String defaults){
        ProfileModel model = new ProfileModel();
        if(mSharedPreferences.contains(KEY_PROFILE)){
            Gson gson = new Gson();
            String json = mSharedPreferences.getString(KEY_PROFILE, defaults);
            model = gson.fromJson(json, ProfileModel.class);
        }
        return model;
    }

    public void setExtraProfile(ExtraProfileModel model){
        Gson gson = new Gson();
        String json = gson.toJson(model);
        mEditor.putString(KEY_EXTRA_PROFILE, json);
        mEditor.commit();
    }

    public ExtraProfileModel getExtraProfile(String defaults){
        ExtraProfileModel model = new ExtraProfileModel();
        if(mSharedPreferences.contains(KEY_EXTRA_PROFILE)){
            Gson gson = new Gson();
            String json = mSharedPreferences.getString(KEY_EXTRA_PROFILE, defaults);
            model = gson.fromJson(json, ExtraProfileModel.class);
        }
        return model;
    }

    public void setUserID(String userID) {
        mEditor.putString(KEY_USER_ID, userID);
        mEditor.commit();
    }

    public String getUserID(String defaults) {
        if (mSharedPreferences.contains(KEY_USER_ID)) {
            return mSharedPreferences.getString(KEY_USER_ID, defaults);
        }
        return defaults;
    }

}
