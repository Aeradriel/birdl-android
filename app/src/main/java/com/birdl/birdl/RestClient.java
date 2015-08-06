package com.birdl.birdl;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Serkan on 12/07/2015.
 */

public class RestClient {
    private static RestLogin REST_CLIENT;

    static {
        setupRestClient();
    }

    public static RestLogin get() {
        return REST_CLIENT;
    }

    private static void setupRestClient(){
        String ROOT = "http://163.5.84.208:3000/";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new SessionRequestInterceptor())
                .build();

        REST_CLIENT = restAdapter.create(RestLogin.class);
    }
}
