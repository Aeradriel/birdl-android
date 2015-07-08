package com.birdl.birdl;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;

public class ServiceGenerator {

    private ServiceGenerator() {}

public static <S> S createService(Class<S> serviceClass, String baseApi)
{
    RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(baseApi)
            .setClient(new OkClient(new OkHttpClient()));
    RestAdapter adapter = builder.build();

    return adapter.create(serviceClass);
}
}
