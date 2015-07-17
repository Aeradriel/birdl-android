package com.birdl.birdl;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.NumberPicker;

/**
 * Created by Christophe on 17/07/2015.
 */
public class SearchEventActivity extends Activity {
    NumberPicker np;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_event_activity);

        final ViewGroup actionBarLayout = (ViewGroup)
                getLayoutInflater().inflate(R.layout.custom_action_bar_search_event,
                        null);

        ActionBar myActionBar = getActionBar();
        myActionBar.setDisplayShowHomeEnabled(false);
        myActionBar.setDisplayHomeAsUpEnabled(true);
        myActionBar.setDisplayShowTitleEnabled(false);
        myActionBar.setDisplayShowCustomEnabled(true);
        myActionBar.setCustomView(actionBarLayout);

        np = (NumberPicker) findViewById(R.id.number_picker);
        np.setMinValue(0);
        np.setMaxValue(15);
        np.setWrapSelectorWheel(false);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
