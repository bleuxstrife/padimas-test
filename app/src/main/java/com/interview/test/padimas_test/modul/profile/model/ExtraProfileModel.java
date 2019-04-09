package com.interview.test.padimas_test.modul.profile.model;

import com.google.gson.annotations.SerializedName;

public class ExtraProfileModel{
    @SerializedName("Address")
    private String address;

    @SerializedName("State")
    private String state;

    @SerializedName("City")
    private String city;

    @SerializedName("ZipCode")
    private String zipCode;

    @SerializedName("Works")
    private String works;

    @SerializedName("JoinDate")
    private String joinDate;

    @SerializedName("RefCode")
    private String refCode;

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getWorks() {
        return works;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getRefCode() {
        return refCode;
    }
}
