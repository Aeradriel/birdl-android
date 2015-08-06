package com.birdl.birdl;

import android.util.Log;

import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedString;

/**
 * Created by Serkan on 18/07/2015.
 */

interface RestRegister{
    @FormUrlEncoded
    @POST("/api/register")
    void postRegister(@Field("user") String user, Callback<LoginResponse> callback);
}