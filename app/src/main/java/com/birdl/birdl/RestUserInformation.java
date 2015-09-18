package com.birdl.birdl;

import model.UserInformation;
import model.UserResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Christophe on 14/09/2015.
 */

public interface RestUserInformation {
    @GET("/api/me")
    void getInfo(Callback<UserResponse> callback);

    @POST("/api/me")
    void setInfo(Callback<UserResponse> callback, @Field("password")String pwd, @Field("user") String query);
}
