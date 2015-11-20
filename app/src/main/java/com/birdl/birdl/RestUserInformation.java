package com.birdl.birdl;

import model.UserResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Christophe on 14/09/2015.
 */

public interface RestUserInformation {
    @GET("/api/me")
    void getInfo(Callback<UserResponse> callback);

    @FormUrlEncoded
    @POST("/api/me")
    void setInfo(@Field("password") String pwd, @Field("user") String query, Callback<UserResponse> callback);
}
