package com.birdl.birdl;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import java.util.Scanner;

/**
 * Created by Serkan on 12/07/2015.
 */

public interface RestApi {
    @FormUrlEncoded
    @POST("/api/login")
    void postLogin(@Field("email") String username, @Field("password") String password, Callback<LoginResponse> callback);
}