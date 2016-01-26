package com.birdl.activity;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Activity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterSpinner;
import adapter.CustomListAdapter;
import adapter.CustomListAdapterMsg;
import configBirdl.AllEventInformationStatic;
import configBirdl.AllEventResponseStatic;
import configBirdl.AllInboxInformationStatic;
import configBirdl.AllInboxResponseStatic;
import configBirdl.BirdlConfigNetwork;
import configBirdl.RelationsInformation;
import configBirdl.RelationsResponse;
import configBirdl.RestInterface;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class InboxActivity extends Fragment {
    private static BirdlConfigNetwork msgNetwork;
    private static RestInterface restCaller;
    private static RestInterface restRelations;
    private View rootView;
    private Spinner receiver_spinner;
    private ListView msgs;
    private String receiver_id;
    private String[] allmessages = null;
    private ArrayList<String> adapter = null;

    public InboxActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgNetwork = new BirdlConfigNetwork();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_inbox, container, false);
        restRelations = new RestInterface(msgNetwork, "user");
        restRelations.getUserInterface().getRelations(new Callback<RelationsResponse>() {
            @Override
            public void success(final RelationsResponse relationsResponse, Response response) {

                receiver_spinner = (Spinner) rootView.findViewById(R.id.rel_spinner);
                receiver_spinner.setAdapter(new AdapterSpinner(getActivity(), relationsResponse.relations));
                receiver_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        AdapterSpinner adapter = (AdapterSpinner) parent.getAdapter();
                        RelationsInformation lang = (RelationsInformation) adapter.getItem(position);
                        receiver_id = String.valueOf(lang.getId());
                        retrieveMsgs(receiver_id);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "bad user relations request", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void retrieveMsgs(String neededId)
    {
        restCaller = new RestInterface(msgNetwork, "message");
        restCaller.getMessageInterface().getMessage(neededId, new Callback<AllInboxResponseStatic>() {
            @Override
            public void success(AllInboxResponseStatic allInboxResponseStatic, Response response2) {
                allInboxResponseStatic.messages.get(0).getContent();
                msgs = (ListView) rootView.findViewById(R.id.inbox_msg);
                msgs.setAdapter(new CustomListAdapterMsg(getActivity(), allInboxResponseStatic.messages));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Erreur :( !", Toast.LENGTH_LONG).show();
            }
        });
    }
}