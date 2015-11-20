package com.birdl.birdl;

import model.AllEventResponse;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Christophe on 17/09/2015.
 */
public interface RestAllEventInformation {
    @GET("/api/events")
    void getInfo(Callback<AllEventResponse> callback);



    //@FormUrlEncoded
    @POST("/api/events/register/{id}")
    void registerUser(@Path("id") String query, Callback<Response> callback);

    @FormUrlEncoded
    @POST("/api/events/create")
    void createEvent(@Field("name") String name, @Field("desc") String desc, @Field("type") String type, @Field("min_slots") String min_slots, @Field("max_slots") String max_slots, @Field("date") String date, @Field("end") String end_date, @Field("language") String language, Callback<Response> callback);
}
