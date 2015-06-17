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


public class Login extends Activity {
    private static EditText username;
    private static EditText password;
    private static Button login;
    private static String token = "";
    private String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton() {
        username = (EditText)findViewById(R.id.user_email);
        password = (EditText)findViewById(R.id.user_pwd);
        login = (Button)findViewById(R.id.connect_button);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!username.getText().toString().isEmpty() &&
                                !password.getText().toString().isEmpty())
                        {
                            try {
                                Connection link = DriverManager.getConnection("http://163.5.84.208/auth");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(Login.this, "Successfully logged in :)!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.birdl.birdl.action.menu");
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Wrong Username/Password :(",
                                    Toast.LENGTH_SHORT).show();
                        }
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
