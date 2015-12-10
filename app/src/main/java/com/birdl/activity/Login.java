package com.birdl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import configBirdl.BirdlConfigNetwork;
import configBirdl.LoginResponse;
import configBirdl.RestInterface;
import interfaceRetrofit.RestUserInterface;
import configBirdl.UserInformationStatic;
import configBirdl.UserResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class Login extends Activity {
    private static EditText username;
    private static EditText password;
    private static Button login;
    private static Button sign_up;
    private static Button others;
    private RestUserInterface restUserInterface;
    private RestInterface restInterface;
    private BirdlConfigNetwork userNetwork;
    static UserInformationStatic InformationStatic;
    public static String getPasswordToString(){
        return password.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNetwork = new BirdlConfigNetwork();
        LoginButton();
    }

    public void LoginButton() {
        username = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.user_pwd);
        login = (Button) findViewById(R.id.connect_button);
        sign_up = (Button) findViewById(R.id.sign_up);
        others = (Button) findViewById(R.id.othersign);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread fetch = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                restUserInterface = userNetwork.getRestAdapter().create(RestUserInterface.class);
                                restUserInterface.postLogin(username.getText().toString(), password.getText().toString(), new Callback<LoginResponse>() {

                                            @Override
                                            public void success(LoginResponse loginResponse, Response response) {
                                                List<Header> test = response.getHeaders();
                                                SessionInformation.ChangeAccessToken(test.get(2).toString());

                                                // fill user information class
                                                restInterface = new RestInterface(userNetwork, "user");
                                                restInterface.getUserInterface().getInfo(new Callback<UserResponse>() {
                                                                              @Override
                                                                              public void success(UserResponse userResponse, Response response2) {
                                                                                  InformationStatic = new UserInformationStatic(
                                                                                          userResponse.user.getId(),
                                                                                          userResponse.user.getEmail(),
                                                                                          userResponse.user.getFirst_name(),
                                                                                          userResponse.user.getLast_name(),
                                                                                          userResponse.user.getBirthdate(),
                                                                                          userResponse.user.getGender(),
                                                                                          SessionInformation.AccessToken,
                                                                                          password.getText().toString());

                                                                                  Toast.makeText(Login.this, "Logged in", Toast.LENGTH_LONG).show();
                                                                                  Intent intent = new Intent("com.birdl.birdl.action.menu");
                                                                                  startActivity(intent);
                                                                              }

                                                                              @Override
                                                                              public void failure(RetrofitError error) {
                                                                                  Toast.makeText(Login.this, "bad user information request", Toast.LENGTH_LONG).show();
                                                                              }
                                                                          }
                                                );
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                );
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

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, GoogleSignIn.class);
                startActivity(intent);
            }
        });
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