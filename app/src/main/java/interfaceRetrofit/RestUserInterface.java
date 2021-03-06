package interfaceRetrofit;

import configBirdl.LoginResponse;

import configBirdl.RelationsResponse;
import configBirdl.UserResponse;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Christophe on 14/09/2015.
 */

public interface RestUserInterface {

    // Login user
    @FormUrlEncoded
    @POST("/api/login")
    void postLogin(@Field("email") String username, @Field("password") String password, Callback<LoginResponse> callback);

    // Register user
    @FormUrlEncoded
    @POST("/api/register")
    void postRegister(@Field("user") String user, Callback<LoginResponse> callback);

    // Get user information
    @GET("/api/me")
    void getInfo(Callback<UserResponse> callback);

    // Set user information
    @FormUrlEncoded
    @POST("/api/me")
    void setInfo(@Field("password") String pwd, @Field("user") String query, Callback<UserResponse> callback);

    // Get user relations
    @GET("/api/user/relations")
    void getRelations(Callback<RelationsResponse> callback);
}
