package interfaceRetrofit;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Christophe on 03/12/2015.
 */
public interface RestMessageInterface {
    @GET("/api/messages")
    void getMessage(Callback<Response> callback);

    @GET("/api/messages/sent")
    void getMessageSent(Callback<Response> callback);

    @GET("/api/messages/received")
    void getMessageReceived(Callback<Response> callback);

    @GET("/api/messages/relations")
    void getMessageRelations(Callback<Response> callback);

    @FormUrlEncoded
    @POST("/api/events/create")
    void createMessage(@Field("sender_id") String senderId, @Field("receiver_id") String receiverId, @Field("content") String content, Callback<Response> callback);
}
