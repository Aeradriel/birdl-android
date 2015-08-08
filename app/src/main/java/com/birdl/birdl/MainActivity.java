package com.birdl.birdl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup actionBarLayout = (ViewGroup)
                getLayoutInflater().inflate(R.layout.custom_action_bar,
                        null);

        ActionBar myActionBar = getActionBar();
        myActionBar.setDisplayShowHomeEnabled(false);
        myActionBar.setDisplayShowTitleEnabled(false);
        myActionBar.setDisplayShowCustomEnabled(true);
        myActionBar.setCustomView(actionBarLayout);

        final ImageButton actionBarPicture = (ImageButton) findViewById(R.id.action_bar_user_picture);
        actionBarPicture.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

            Intent a = new Intent("com.birdl.birdl.SetProfilActivity");
            startActivity(a);
        }});

            final ImageButton actionBarMessage = (ImageButton) findViewById(R.id.action_bar_message);
        actionBarMessage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                PopupMenu popupMessage = new PopupMenu(MainActivity.this, actionBarMessage);

                MenuInflater inflaterEvent = popupMessage.getMenuInflater();
                inflaterEvent.inflate(R.menu.menu_message, popupMessage.getMenu());

                popupMessage.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String sText = item.getTitle().toString();
                        if(sText.equals("Inbox")) {
                            Intent a = new Intent("com.birdl.birdl.InboxActivity");
                            startActivity(a);
                        }
                        else if(sText.equals("New message")) {
                            Intent a = new Intent("com.birdl.birdl.NewMessageActivity");
                            startActivity(a);
                        }
                        return true;
                    }
                });
                popupMessage.show();
            }
        });

        final ImageButton actionBarEvent = (ImageButton) findViewById(R.id.action_bar_event);
        actionBarEvent.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                PopupMenu popupEvent = new PopupMenu(MainActivity.this, actionBarEvent);

                MenuInflater inflaterEvent = popupEvent.getMenuInflater();
                inflaterEvent.inflate(R.menu.menu_event, popupEvent.getMenu());

                popupEvent.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String sText = item.getTitle().toString();
                        if(sText.equals("Create event")) {
                            Intent a = new Intent("com.birdl.birdl.CreateEventActivity");
                            startActivity(a);
                        }
                        else if(sText.equals("Search event")) {
                            Intent a = new Intent("com.birdl.birdl.SearchEventActivity");
                            startActivity(a);
                        }
                        return true;
                    }
                });
                popupEvent.show();
            }
        });

        final ImageButton actionBarSettings = (ImageButton) findViewById(R.id.action_bar_settings);
        actionBarSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupOption = new PopupMenu(MainActivity.this, actionBarSettings);

                MenuInflater inflaterOption = popupOption.getMenuInflater();
                inflaterOption.inflate(R.menu.other_option, popupOption.getMenu());

                popupOption.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String sText = item.getTitle().toString();
                        if(sText.equals("Settings")) {
                            Intent a = new Intent("com.birdl.birdl.SettingsActivity");
                            startActivity(a);
                        }
                        else if(sText.equals("A propos")) {
                            Intent a = new Intent("com.birdl.birdl.AboutActivity");
                            startActivity(a);
                        }
                        return true;
                    }
                });
                popupOption.show();
            }
        });
    }
}
