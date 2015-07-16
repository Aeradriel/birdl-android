package com.birdl.birdl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import retrofit.RestAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public class Login extends Activity {
    private static EditText username;
    private static EditText password;
    private static Button login;
    private static Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton() {

        username = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.user_pwd);
        login = (Button) findViewById(R.id.connect_button);
        sign_up = (Button) findViewById(R.id.sign_up);
        final RetrofitError error;

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Thread fetch = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                RestAdapter restAdapter = new RestAdapter.Builder()
                                        .setEndpoint("http://163.5.84.208:3000/")
                                        .build();

                                RestApi api = restAdapter.create(RestApi.class);
                                api.postLogin(username.getText().toString(), password.getText().toString(), new Callback<LoginResponse>() {
                                    @Override
                                    public void success(LoginResponse loginResponse, Response response) {
                                        Toast.makeText(Login.this, "Logged in", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent("com.birdl.birdl.action.menu");
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        fetch.start();
                    }
                });


        sign_up.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View t) {
                    Intent intent = new Intent("com.birdl.birdl.signupactivity");
                    startActivity(intent);
                }
            }
        );

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}