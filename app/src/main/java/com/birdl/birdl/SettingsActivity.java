package com.birdl.birdl;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birdl.birdl.R;

import activity.MainActivity;

public class SettingsActivity extends Activity {

    Toolbar customtoolbar;
    public static String application_language_selection_init = null;
    public static String application_language_selection = "English";
    private Spinner spinner_language;
    private static Button ButtonSubmit;
    private static TextView get_notification;
    private static TextView application_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        application_language_selection_init = application_language_selection;

        customtoolbar = (Toolbar) findViewById(R.id.toolbar);

        customtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application_language_selection = application_language_selection_init;
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });

        ButtonSubmit = (Button) findViewById(R.id.submit_setting);
        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });

        spinner_language = (Spinner) findViewById(R.id.spinner_language);
        get_notification = (TextView) findViewById(R.id.get_notification);
        application_language = (TextView) findViewById(R.id.application_language);

        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                application_language_selection =  spinner_language.getSelectedItem().toString();
                if (application_language_selection.equals("English"))
                {
                    ((TextView)findViewById(R.id.get_notification)).setText(R.string.get_notifier);
                    ((TextView)findViewById(R.id.application_language)).setText(R.string.application_language);
                    ((Button)findViewById(R.id.submit_setting)).setText(R.string.submit_settings);
                }
                else if (application_language_selection.equals("French"))
                {
                    ((TextView)findViewById(R.id.get_notification)).setText(R.string.get_notifier_fr);
                    ((TextView)findViewById(R.id.application_language)).setText(R.string.application_language_fr);
                    ((Button)findViewById(R.id.submit_setting)).setText(R.string.submit_settings_fr);
                }
                else if (application_language_selection.equals("Spanish"))
                {
                    ((TextView)findViewById(R.id.get_notification)).setText(R.string.get_notifier_sp);
                    ((TextView)findViewById(R.id.application_language)).setText(R.string.application_language_sp);
                    ((Button)findViewById(R.id.submit_setting)).setText(R.string.submit_settings_sp);
                }
                else if (application_language_selection.equals("Italian"))
                {
                    ((TextView)findViewById(R.id.get_notification)).setText(R.string.get_notifier_it);
                    ((TextView)findViewById(R.id.application_language)).setText(R.string.application_language_it);
                    ((Button)findViewById(R.id.submit_setting)).setText(R.string.submit_settings_it);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}