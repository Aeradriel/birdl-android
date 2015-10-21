package com.birdl.birdl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.AllEventInformation;
import model.AllEventInformationStatic;
import model.AllEventResponse;
import model.AllEventResponseStatic;
import model.UserInformationStatic;
import model.UserResponse;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.HEAD;

public class Login extends Activity {
    private static EditText username;
    private static EditText password;
    private static Button login;
    private static Button sign_up;
    private static Button others;
    static int i = 0;
    static UserInformationStatic InformationStatic;
    static AllEventResponseStatic allEventResponseStatic;
    static AllEventInformationStatic allEventInformationStatic;
    public RestAdapter restAdapterHeader;
    public RequestInterceptor requestInterceptor;
    public static String getPasswordToString(){
        return password.getText().toString();
    }

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
        others = (Button) findViewById(R.id.othersign);
        final RetrofitError error;

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Thread fetch = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final RestAdapter restAdapter = new RestAdapter.Builder()
                                        .setEndpoint("http://birdl.xyz:3000/")
                                        .build();

                                RestLogin api = restAdapter.create(RestLogin.class);
                                api.postLogin(username.getText().toString(), password.getText().toString(), new Callback<LoginResponse>() {
                                    @Override
                                    public void success(LoginResponse loginResponse, Response response) {
                                        List<Header> test = response.getHeaders();
                                        SessionInformation.ChangeAccessToken(test.get(2).toString());

                                        requestInterceptor = new RequestInterceptor() {
                                            public void intercept(RequestFacade request) {
                                                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
                                            }
                                        };

                                        restAdapterHeader = new RestAdapter.Builder().setEndpoint("http://birdl.xyz:3000/")
                                                .setRequestInterceptor(requestInterceptor)
                                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                                .setLog(new AndroidLog("log retrofit"))
                                                .build();

                                        // fill user information class
                                        RestUserInformation getUserInfo = restAdapterHeader.create(RestUserInformation.class);
                                        getUserInfo.getInfo(new Callback<UserResponse>() {
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

                                                //fill event information class
                                                RestAdapter eventInformation = new RestAdapter.Builder().setEndpoint("http://birdl.xyz:3000/")
                                                       .setRequestInterceptor(requestInterceptor)
                                                       .setLogLevel(RestAdapter.LogLevel.FULL)
                                                       .setLog(new AndroidLog("log retrofit"))
                                                       .build();
                                               RestAllEventInformation getEventInfo = eventInformation.create(RestAllEventInformation.class);
                                               getEventInfo.getInfo(new Callback<AllEventResponse>() {
                                                   @Override
                                                   public void success(AllEventResponse allEventResponse, Response response3) {

                                                       AllEventResponseStatic.events = new ArrayList<AllEventInformationStatic>();
                                                       for (int i = 0; i < allEventResponse.events.size(); i++)
                                                       {
                                                           AllEventResponseStatic.events.add(new AllEventInformationStatic(allEventResponse.events.get(i).id,
                                                                   allEventResponse.events.get(i).getName(),
                                                                   allEventResponse.events.get(i).getType(),
                                                                   allEventResponse.events.get(i).getMin_slots(),
                                                                   allEventResponse.events.get(i).getMax_slots(),
                                                                   allEventResponse.events.get(i).getDate(),
                                                                   allEventResponse.events.get(i).getDesc(),
                                                                   allEventResponse.events.get(i).getOwner_id(),
                                                                   allEventResponse.events.get(i).getAddress_id(),
                                                                   allEventResponse.events.get(i).getLocation()));
                                                       }
                                                       Toast.makeText(Login.this, "Logged in", Toast.LENGTH_LONG).show();
                                                       Intent intent = new Intent("com.birdl.birdl.action.menu");
                                                       startActivity(intent);
                                                   }

                                                   @Override
                                                   public void failure(RetrofitError error) {
                                                   }
                                               });

                                                Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent("com.birdl.birdl.action.menu");
                                                startActivity(intent);
                                            }

                                                @Override
                                                public void failure (RetrofitError error){
                                                    Toast.makeText(Login.this, "bad user information request", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        );
                                    }

                                        @Override
                                        public void failure (RetrofitError error){
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