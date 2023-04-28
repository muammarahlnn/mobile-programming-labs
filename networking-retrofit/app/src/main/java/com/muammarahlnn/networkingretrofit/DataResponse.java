package com.muammarahlnn.networkingretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DataResponse, 28/04/2023 11.24 by Muammar Ahlan Abimanyu
 */
public class DataResponse {

    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }
}
