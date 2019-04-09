package com.interview.test.padimas_test.modul.profile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileModel {

    @SerializedName("UserId")
    private String userId;

    @SerializedName("DisplayName")
    private String displayName;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Email")
    private String email;

    @SerializedName("roles")
    private List<String> roles;

    @SerializedName("ExtraProfile")
    private ExtraProfileModel getExtraProfile;

    @SerializedName("AffiliateId")
    private String affilatedId;



    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getAffilatedId() {
        return affilatedId;
    }

    public ExtraProfileModel getGetExtraProfile() {
        return getExtraProfile;
    }
}
