package com.birdl.birdl;

import java.util.List;

import model.AllEventResponse;
import model.UserResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Christophe on 17/09/2015.
 */
public interface RestAllEventInformation {
    @GET("/api/events")
    void getInfo(Callback<AllEventResponse> callback);
}
