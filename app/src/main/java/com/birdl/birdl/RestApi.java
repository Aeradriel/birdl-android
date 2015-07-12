package com.birdl.birdl;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Serkan on 12/07/2015.
 */

public interface RestApi {
    @POST("/api/login")
    void login(@Body UserCurrent user,
               RestCallback<LoginResponse> callback);
}
