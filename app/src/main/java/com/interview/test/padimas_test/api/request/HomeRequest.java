package com.interview.test.padimas_test.api.request;

import android.content.Context;

import com.interview.test.padimas_test.api.APIService;
import com.interview.test.padimas_test.api.BaseConnection;
import com.interview.test.padimas_test.modul.dashboard.model.CarListModel;
import com.interview.test.padimas_test.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRequest extends BaseConnection {
    APIService apiService;

    public HomeRequest(Context context) {
        super(context);
        apiService = getAPI().create(APIService.class);
    }

    public void getCar(){
        apiService.getListCar(Constant.BASIC_AUTH).enqueue(new Callback<List<CarListModel>>() {
            @Override
            public void onResponse(Call<List<CarListModel>> call, Response<List<CarListModel>> response) {
                if(response.code()==200){
                    sendResponse(response.body(), Constant.GET_SPECIAL_CAR);
                } else {
                    onFailure(call, new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<CarListModel>> call, Throwable t) {
                sendResponse(t);
            }
        });
    }
}
