package com.muammarahlnn.networkingretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ApiConfig, 28/04/2023 10.49 by Muammar Ahlan Abimanyu
 */
public class ApiConfig {
    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
