package com.birdl.birdl;

import model.UserInformation;
import model.UserResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Christophe on 14/09/2015.
 */
public interface RestUserInformation {
    @GET("/api/me")
    void getInfo(Callback<UserResponse> callback);
}
