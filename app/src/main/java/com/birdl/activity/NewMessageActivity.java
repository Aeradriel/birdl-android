package com.birdl.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import adapter.AdapterSpinner;
import configBirdl.BirdlConfigNetwork;
import configBirdl.RelationsInformation;
import configBirdl.RelationsResponse;
import configBirdl.RestInterface;
import configBirdl.UserInformationStatic;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NewMessageActivity extends Fragment {
    private static BirdlConfigNetwork msgNetwork;
    private static RestInterface restCaller;
    private static RestInterface restRelations;
    private View rootView;
    private Spinner receiver_spinner;
    private String sender_id;
    private String receiver_id;
    private EditText content_msg;
    private Button send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgNetwork = new BirdlConfigNetwork();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_new_message, container, false);

        restRelations = new RestInterface(msgNetwork, "user");
        restRelations.getUserInterface().getRelations(new Callback<RelationsResponse>() {
            @Override
            public void success(final RelationsResponse relationsResponse, Response response) {

                send = (Button) rootView.findViewById(R.id.send_but);
                receiver_spinner = (Spinner) rootView.findViewById(R.id.receiver_spinner);
                receiver_spinner.setAdapter(new AdapterSpinner(getActivity(), relationsResponse.relations));
                receiver_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        AdapterSpinner adapter = (AdapterSpinner) parent.getAdapter();
                        RelationsInformation lang = (RelationsInformation) adapter.getItem(position);
                        receiver_id = String.valueOf(lang.getId());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                content_msg = (EditText) rootView.findViewById(R.id.content_msg);
                sender_id = String.valueOf(UserInformationStatic.getId());
                sendMethod();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "bad user relations request", Toast.LENGTH_LONG).show();
            }
        });
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
                                restCaller.getMessageInterface().createMessage(sender_id, receiver_id, content_msg.getText().toString(), new Callback<Response>() {
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