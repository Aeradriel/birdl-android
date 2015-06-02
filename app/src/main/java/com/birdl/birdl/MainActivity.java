package com.birdl.birdl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.birdl.birdl.R;


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

        ImageButton actionBarSent = (ImageButton) findViewById(R.id.action_bar_user_picture);

        ImageButton actionBarStaff = (ImageButton) findViewById(R.id.action_bar_message);
        actionBarStaff.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent a = new Intent("com.bridl.homepagebirdl.MailActivity");
                startActivity(a);
            }
        });

        ImageButton actionBarNottif = (ImageButton) findViewById(R.id.action_bar_nottif);

        final ImageButton actionBarSettings = (ImageButton) findViewById(R.id.action_bar_settings);
        actionBarSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, actionBarSettings);

                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.other_option, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String sText = item.getTitle().toString();
                        if(sText.equals("Settings")) {
                            Intent a = new Intent("com.bridl.homepagebirdl.SettingsActivity");
                            startActivity(a);
                        }
                        else if(sText.equals("A propos")) {
                            Intent a = new Intent("com.bridl.homepagebirdl.AboutActivity");
                            startActivity(a);
                        }

//                        Toast.makeText(
//                                MainActivity.this,
//                                "You Clicked : " + item.getTitle(),
//                                Toast.LENGTH_SHORT
//                        ).show();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}
