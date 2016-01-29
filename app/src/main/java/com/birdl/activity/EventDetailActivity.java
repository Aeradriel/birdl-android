package com.birdl.activity;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import configBirdl.BirdlConfigNetwork;
import configBirdl.RestInterface;
import configBirdl.UserInformationStatic;
import interfaceRetrofit.RestEventInterface;
import configBirdl.AllEventResponseStatic;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by Christophe on 05/10/2015.
 */
public class EventDetailActivity extends AppCompatActivity {
    Toolbar customtoolbar;
    private BirdlConfigNetwork eventNetwork;
    private RestInterface restEvent;
    public static int position;
    private EditText EventNameField = null;
    private EditText EventTypeField = null;
    private EditText LanguageField = null;
    private EditText LocationField = null;
    private EditText MaxSlotsField = null;
    private EditText DescField = null;
    private Button ValidatePresence;
    private Button ButtonSubmit;
    private RestAdapter restAdapterHeader;
    private RequestInterceptor requestInterceptor;
    private static String isRegistred ;
    private String currentDate;
    private String eventDate;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        eventNetwork = new BirdlConfigNetwork();
        restEvent = new RestInterface(eventNetwork, "event");

        restEvent.getEventInterface().isUserRegistred(String.valueOf(AllEventResponseStatic.events.get(position).getId()),
                String.valueOf(UserInformationStatic.getId()), new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        isRegistred = new String(((TypedByteArray) response.getBody()).getBytes());

                        bindViews();

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

                        final String query = String.valueOf(AllEventResponseStatic.events.get(position).getId());

                        ValidatePresence.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try{
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                Date current = formatter.parse(currentDate);

                                Date event = formatter.parse(eventDate);
                                if (isRegistred.equals("true"))
                                {
                                    if (current.compareTo(event)>0 || current.compareTo(event)==0)
                                    {
                                            restEvent.getEventInterface().confirmPresence(query, String.valueOf(UserInformationStatic.getId()), "yes", new Callback<Response>() {
                                                @Override
                                                public void success(Response response, Response response2) {
                                                    Toast.makeText(EventDetailActivity.this, "Presence confirmed", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void failure(RetrofitError error) {
                                                    Toast.makeText(EventDetailActivity.this, "You can't confirm your presence", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    }
                                    else
                                    Toast.makeText(EventDetailActivity.this, "The event must be currently on to confirm your presence", Toast.LENGTH_SHORT).show();
                                }
                                    else
                                        Toast.makeText(EventDetailActivity.this, "You're not registered to this event", Toast.LENGTH_SHORT).show();

                                }catch (ParseException e1){
                                    e1.printStackTrace();
                                }
                            }
                        });

                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (isRegistred.equals("true")) {
                                    restEvent.getEventInterface().unregisterUser(query, new Callback<Response>() {
                                        @Override
                                        public void success(Response response, Response response2) {
                                            Toast.makeText(EventDetailActivity.this, "Unregister", Toast.LENGTH_SHORT).show();
                                            isRegistred = "false";
                                            ButtonSubmit.setText("Subscribe");
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(EventDetailActivity.this, "You can't unregister", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    restEvent.getEventInterface().registerUser(query, new Callback<Response>() {
                                        @Override
                                        public void success(Response response, Response response2) {
                                            Toast.makeText(EventDetailActivity.this, "Register", Toast.LENGTH_SHORT).show();

                                            if (SettingsActivity.getnotif.equals("check")) {
                                                //send notif to android wear
                                                Notification notification = new NotificationCompat.Builder(getApplication())
                                                        .setSmallIcon(R.drawable.logo_birdl)
                                                        .setContentTitle("You register to an event")
                                                        .setContentText(AllEventResponseStatic.events.get(position).getName())
                                                        .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                                                        .build();
                                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
                                                int notificationId = 1;
                                                notificationManager.notify(notificationId, notification);
                                            }
                                            isRegistred = "true";
                                            ButtonSubmit.setText("Unsubscribe");
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(EventDetailActivity.this, "You can't register", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(EventDetailActivity.this, "not registred", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("com.birdl.birdl.SlidingEventLayout");
                        startActivity(intent);
                    }
                });
    }

    private void bindViews()
    {
        EventNameField = (EditText) findViewById(R.id.EventNameField);
        EventNameField.setText(AllEventResponseStatic.events.get(position).getName());

        EventTypeField = (EditText) findViewById(R.id.EventTypeField);
        EventTypeField.setText(AllEventResponseStatic.events.get(position).getType());

        LanguageField = (EditText) findViewById(R.id.EventLanguageField);
        LanguageField.setText(AllEventResponseStatic.events.get(position).getLanguage());

        LocationField = (EditText) findViewById(R.id.LocationField);
        LocationField.setText(AllEventResponseStatic.events.get(position).getLocation());

        MaxSlotsField = (EditText) findViewById(R.id.MaxSlotsField);
        MaxSlotsField.setText(String.valueOf(AllEventResponseStatic.events.get(position).getMax_slots()));

        DescField = (EditText) findViewById(R.id.DescField);
        DescField.setText(AllEventResponseStatic.events.get(position).getDesc());

        ValidatePresence = (Button) findViewById(R.id.buttonPresence);

        Date cDate = new Date();
        currentDate = new SimpleDateFormat("dd/MM/yyyy").format(cDate);
        eventDate = parseDateTime(AllEventResponseStatic.events.get(position).getDate());

        ButtonSubmit = (Button) findViewById(R.id.buttonSubscribe);
        if (isRegistred.equals("true")) {
            ButtonSubmit.setText("Unsubscribe");
            }
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date current = formatter.parse(currentDate);

            Date event = formatter.parse(eventDate);

            if (current.compareTo(event)>0 || current.compareTo(event)==0) {
                ButtonSubmit.setAlpha(0.5F);
                ButtonSubmit.setBackgroundColor(Color.parseColor("#BDBDBD"));
                ButtonSubmit.setFocusable(false);

                ValidatePresence.setBackgroundColor(Color.parseColor("#EF6C00"));
                ValidatePresence.setAlpha(1F);
                ValidatePresence.setFocusable(true);
            }
        }catch (ParseException e1){
            e1.printStackTrace();
        }

        customtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(customtoolbar);
    }

    private String parseDateTime(String time)
    {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        String outputPattern = "dd/MM/yyyy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try
        {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return str;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
