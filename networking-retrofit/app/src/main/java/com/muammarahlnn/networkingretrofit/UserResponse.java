package com.muammarahlnn.networkingretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file UserResponse, 27/04/2023 23.46 by Muammar Ahlan Abimanyu
 */
public class UserResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("avatar")
    private String avatarUrl;

    public int getId() {
        return id;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
