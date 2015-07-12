package com.birdl.birdl;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Serkan on 12/07/2015.
 */

//Classe de Gestion d'Erreur logiquement fonctionnel

public abstract class RestCallback<T> implements Callback<T> {
    public abstract void failure(RestError restError);

    @Override
    public void failure(RetrofitError error){
        RestError restError = (RestError) error.getBodyAs(RestError.class);
        if (restError != null)
            failure(restError);
        else {
            failure(new RestError(error.getMessage()));
        }
    }
}
