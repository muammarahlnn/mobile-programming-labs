package com.muammarahlnn.networkingretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ApiService, 28/04/2023 10.45 by Muammar Ahlan Abimanyu
 */
public interface ApiService {

    @GET("api/users/{id}")
    Call<DataResponse> getUser(@Path("id") String id);
}
