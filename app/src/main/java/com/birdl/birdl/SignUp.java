package com.birdl.birdl;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.*;
import com.google.gson.*;
import java.io.BufferedReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;


public class SignUp extends Activity {

    private static EditText email;
    private static EditText pwd;
    private static EditText first_name;
    private static EditText last_name;
    private static EditText birthdate;
    private Button country;
    private Switch gender;
    private static Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUp();
    }

    public void SignUp(){
         email = (EditText) findViewById(R.id.sign_up_email);
         pwd = (EditText) findViewById(R.id.sign_up_pwd);
         first_name = (EditText) findViewById(R.id.sign_up_first_name);
         last_name = (EditText) findViewById(R.id.sign_up_last_name);
         birthdate = (EditText) findViewById(R.id.sign_up_date);
         gender = (Switch) findViewById(R.id.switch1);
         create = (Button) findViewById(R.id.sign_up_confirm);
         country = (Button) findViewById(R.id.button_land);

        gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SignUp.this, "Female selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUp.this, "Male selected", Toast.LENGTH_SHORT).show();
                }
            }
        });



        create.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Thread i = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                RestAdapter restAdapter = new RestAdapter.Builder()
                                        .setEndpoint("http://163.5.84.208:3000/")
                                        .build();

                                RestRegister restRegister = restAdapter.create(RestRegister.class);

                                String res = "";
                                if (gender.isChecked()){
                                    res = "0";
                                } else res = "1";

                                String query = "{\"email\":\"" + email.getText().toString() + "\"" + ",\"first_name\":\"" + first_name.getText().toString() + "\"" + ",\"last_name\":\"" +
                                        last_name.getText().toString() + "\"" + ",\"password\":\"" + pwd.getText().toString() + "\"" + ",\"gender\":\"" + res + "\"" + ",\"birthdate\":\"" +
                                        birthdate.getText().toString() + "\"" + "}";

                                restRegister.postRegister(query, new Callback<LoginResponse>() {
                                    @Override
                                    public void success(LoginResponse loginResponse, Response response) {
                                        Toast.makeText(SignUp.this, "Created !", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent("com.birdl.birdl.signupconfirmation");
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(SignUp.this, "Failed !", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        i.start();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
