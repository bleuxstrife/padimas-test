package com.interview.test.padimas_test.api.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.interview.test.padimas_test.api.APIService;
import com.interview.test.padimas_test.api.BaseConnection;
import com.interview.test.padimas_test.modul.login.model.LoginModel;
import com.interview.test.padimas_test.utils.Constant;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRequest extends BaseConnection {
    APIService apiService;

    public LoginRequest(Context context) {
        super(context);
        apiService = getAPI().create(APIService.class);
    }

    public void login(String email, String password){
        apiService.login(Constant.BASIC_AUTH, email, password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.code()==200){
                    sendResponse(response.body(), Constant.LOGIN);
                } else if (response.code() == 400 ) {
                    LoginModel model = new LoginModel();
                    Gson gson = new Gson();
                    TypeAdapter<LoginModel> adapter = gson.getAdapter(LoginModel.class);
                    try {
                        if (response.errorBody() != null)
                            model = adapter.fromJson(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sendResponse(model, response.code());
                } else {
                    onFailure(call, new Throwable(response.message()));
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                sendResponse(t);
            }
        });
    }
}
