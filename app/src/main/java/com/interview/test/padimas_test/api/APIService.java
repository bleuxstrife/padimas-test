package com.interview.test.padimas_test.api;

import com.interview.test.padimas_test.modul.login.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("authentification/login")
    Call<LoginModel> login(@Header("Authorization")String token,
                           @Field("email")String email,
                           @Field("password")String password);
}
