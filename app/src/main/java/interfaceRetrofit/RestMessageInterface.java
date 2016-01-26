package interfaceRetrofit;

import configBirdl.AllInboxResponseStatic;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Christophe on 03/12/2015.
 */
public interface RestMessageInterface {
    // Get user messages
    @GET("/api/messages")
    void getMessage(@Query("relation") String receiverId, Callback<AllInboxResponseStatic> callback);

    @GET("/api/messages/relations")
    void getMessageRelations(Callback<Response> callback);

    @FormUrlEncoded
    @POST("/api/messages/new")
    void createMessage(@Field("sender_id") String senderId, @Field("receiver_id") String receiverId, @Field("content") String content, Callback<Response> callback);
}
