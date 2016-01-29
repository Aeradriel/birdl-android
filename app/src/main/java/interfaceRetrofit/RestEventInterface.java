package interfaceRetrofit;

import configBirdl.AllEventResponse;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Christophe on 17/09/2015.
 */
public interface RestEventInterface {
    @GET("/api/events")
    void getAllEvent(Callback<AllEventResponse> callback);

    @GET("/api/events/check")
    void isUserRegistred(@Query("event_id") String event_id, @Query("user_id") String user_id, Callback<Response> callback);

    @GET("/api/events/future")
    void getFutureEvent(Callback<AllEventResponse> callback);

    @GET("/api/events/past")
    void getPastEvent(Callback<AllEventResponse> callback);

    @POST("/api/events/register/{id}")
    void registerUser(@Path("id") String query, Callback<Response> callback);

    @POST("/api/events/unregister/{id}")
    void unregisterUser(@Path("id") String query, Callback<Response> callback);

    @FormUrlEncoded
    @POST("/api/events/create")
    void createEvent(@Field("name") String name, @Field("desc") String desc, @Field("type") String type, @Field("min_slots") String min_slots, @Field("max_slots") String max_slots, @Field("date") String date, @Field("end") String end_date, @Field("location") String location, @Field("language") String language, Callback<Response> callback);

    @GET("/api/events/presence")
    void getPresence(Callback<Response> callback);

    @FormUrlEncoded
    @POST("/api/events/presence/")
    void confirmPresence(@Field("user_id") String user_id, @Field("event_id") String event_id, @Field("was_there") String was_there, Callback<Response> callback);
}
