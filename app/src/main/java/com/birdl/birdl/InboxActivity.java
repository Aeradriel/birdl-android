package com.birdl.birdl;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.birdl.birdl.R;

import java.util.ArrayList;

import activity.MainActivity;
import adapter.CustomListAdapter;
import model.AllEventResponseStatic;
import model.AllInboxInformationStatic;
import model.AllInboxResponseStatic;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

public class InboxActivity extends Fragment {
    public InboxActivity() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
            }
        };

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://birdl.xyz:3000/")
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_inbox, container, false);
        ArrayList inbox_list = AllInboxResponseStatic.inbox;
        ListView inbox = (ListView) rootView.findViewById(R.id.inbox_msg);
        inbox.setAdapter(new CustomListAdapter(getActivity(), AllInboxResponseStatic.inbox));
        return rootView;
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