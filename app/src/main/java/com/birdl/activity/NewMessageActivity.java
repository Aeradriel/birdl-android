package com.birdl.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import configBirdl.BirdlConfigNetwork;
import configBirdl.RestInterface;
import configBirdl.UserInformation;
import configBirdl.UserInformationStatic;
import interfaceRetrofit.RestMessageInterface;
import interfaceRetrofit.RestUserInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NewMessageActivity extends Fragment {
    private BirdlConfigNetwork msgNetwork;
    private RestInterface restCaller;
    private EditText receiver_id;
    private String sender_id;
    private EditText content_msg;
    private Button send;
    private UserInformationStatic userinfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgNetwork = new BirdlConfigNetwork();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_new_message, container, false);
        send = (Button) rootView.findViewById(R.id.send_but);
        receiver_id = (EditText) rootView.findViewById(R.id.receiver_id);
        content_msg = (EditText) rootView.findViewById(R.id.content_msg);
        sender_id = String.valueOf(UserInformationStatic.getId());
        sendMethod();
        return rootView;

    }

    public void sendMethod() {
        send.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread start = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                restCaller = new RestInterface(msgNetwork, "message");
                                restCaller.getMessageInterface().createMessage(sender_id, receiver_id.getText().toString(), content_msg.getText().toString(), new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        Toast.makeText(getActivity(), "Message envoyé", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(getActivity(), "Erreur", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        start.start();
                    }
                });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}