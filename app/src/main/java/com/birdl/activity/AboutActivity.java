package com.birdl.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends Activity{

    Toolbar customtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        customtoolbar = (Toolbar) findViewById(R.id.toolbar);

        customtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });

        Button ButtonSubmit = (Button) findViewById(R.id.ok_about);
        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}