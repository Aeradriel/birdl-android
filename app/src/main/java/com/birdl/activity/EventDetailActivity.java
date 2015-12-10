package com.birdl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import interfaceRetrofit.RestEventInterface;
import configBirdl.AllEventResponseStatic;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

/**
 * Created by Christophe on 05/10/2015.
 */
public class EventDetailActivity extends AppCompatActivity {
    Toolbar customtoolbar;
    public static int position;
    private EditText EventNameField = null;
    private EditText EventTypeField = null;
    private EditText LanguageField = null;
    private EditText LocationField = null;
    private EditText MaxSlotsField = null;
    private EditText DescField = null;
    private RestAdapter restAdapterHeader;
    private RequestInterceptor requestInterceptor;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        EventNameField = (EditText) findViewById(R.id.EventNameField);
        EventNameField.setText(AllEventResponseStatic.events.get(position).getName());

        EventTypeField = (EditText) findViewById(R.id.EventTypeField);
        EventTypeField.setText(AllEventResponseStatic.events.get(position).getType());

        LanguageField = (EditText) findViewById(R.id.EventLanguageField);
        LanguageField.setText(null);

        LocationField = (EditText) findViewById(R.id.LocationField);
        LocationField.setText(AllEventResponseStatic.events.get(position).getLocation());

        MaxSlotsField = (EditText) findViewById(R.id.MaxSlotsField);
        MaxSlotsField.setText(String.valueOf(AllEventResponseStatic.events.get(position).getMax_slots()));

        DescField = (EditText) findViewById(R.id.DescField);
        MaxSlotsField.setText(AllEventResponseStatic.events.get(position).getDesc());

        customtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(customtoolbar);

        customtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.birdl.birdl.SlidingEventLayout");
                startActivity(intent);
            }
        });


        requestInterceptor = new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
            }
        };

        restAdapterHeader = new RestAdapter.Builder()
                .setEndpoint("http://163.5.84.208:3000/")
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();

        final RestEventInterface eventInformation = restAdapterHeader.create(RestEventInterface.class);

        final String query = String.valueOf(AllEventResponseStatic.events.get(position).getId());
        Log.i("query", query);

        Button ButtonSubmit = (Button) findViewById(R.id.buttonSubscribe);
        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eventInformation.registerUser(query, new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        Toast.makeText(EventDetailActivity.this, "good", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(EventDetailActivity.this, "bad", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent2 = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent2);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
