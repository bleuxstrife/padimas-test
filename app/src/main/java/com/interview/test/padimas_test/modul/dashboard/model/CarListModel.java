package com.interview.test.padimas_test.modul.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class CarListModel {
    @SerializedName("CarName")
    private String carName;

    @SerializedName("CarImage")
    private String carImage;

    @SerializedName("StartFromPrice")
    private int carPrice;

    public String getCarName() {
        return carName;
    }

    public String getCarImage() {
        return carImage;
    }

    public int getCarPrice() {
        return carPrice;
    }
}
