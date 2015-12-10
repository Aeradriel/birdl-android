package com.birdl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import configBirdl.RestInterface;
import interfaceRetrofit.RestUserInterface;
import configBirdl.UserResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SetProfilActivity extends Activity {

    Toolbar customtoolbar;
    private ImageView profilPic;
    private Button pickImage;
    RestUserInterface start;
    private final int SELECT_PHOTO = 1;
    public static String FirstNameModif;
    public static String LastNameModif;
    public static String BirthdateModif;
    public static String EmailModif;
    private EditText FirstNameField = null;
    private EditText LastNameField = null;
    private EditText BirthdateField = null;
    private EditText EmailField = null;
    private String pass;


    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_profil_activity);
        BindViews();

        customtoolbar = (Toolbar) findViewById(R.id.toolbar);

        customtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });

        FirstNameField = (EditText) findViewById(R.id.FirstNameField);
        FirstNameField.setText(FirstNameModif);

        LastNameField = (EditText) findViewById(R.id.LastNameField);
        LastNameField.setText(LastNameModif);

        BirthdateField = (EditText) findViewById(R.id.BirthdateField);
        BirthdateField.setText(BirthdateModif);

        EmailField = (EditText) findViewById(R.id.EmailField);
        EmailField.setText(EmailModif);

        profilPic = (ImageView) findViewById(R.id.setImageProfil);
        pickImage = (Button) findViewById(R.id.buttonBrowsePic);
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        pass = Login.getPasswordToString();

        Button ButtonSubmit = (Button) findViewById(R.id.buttonSubmit);
            ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String query = "{\"email\":\"" + EmailField.getText().toString() + "\"" + ",\"first_name\":\"" + FirstNameField.getText().toString() + "\"" + ",\"last_name\":\"" +
                        LastNameField.getText().toString() + "\"" + ",\"birthdate\":\"" +
                        BirthdateField.getText().toString().replace('-', '/') + "\"" + "}";
                Log.i("query", query);
                RestInterface.getUserInterface().setInfo(pass, query, new Callback<UserResponse>() {
                    @Override
                    public void success(UserResponse userResponse, Response response) {
                        Toast.makeText(SetProfilActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(SetProfilActivity.this, "Update Failure", Toast.LENGTH_SHORT).show();
                    }
                });
                //MainActivity.FirstNameModif = FirstNameField.getText().toString();
                Intent intent = new Intent("com.birdl.birdl.action.menu");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        MainActivity.defaultImage = selectedImage;
                        profilPic.setImageBitmap(selectedImage);
                        //MainActivity.SetProfil.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    protected void BindViews()
    {
        if (SettingsActivity.application_language_selection.equals("English")) {
            ((TextView) findViewById(R.id.FirstName)).setText(R.string.first_name_profile);
            ((TextView) findViewById(R.id.LastName)).setText(R.string.last_name_profile);
            ((TextView) findViewById(R.id.Birhtdate)).setText(R.string.birthdate_profile);
            ((TextView) findViewById(R.id.Email)).setText(R.string.email_profile);
            ((TextView) findViewById(R.id.Picture)).setText(R.string.picture_profile);
            ((Button) findViewById(R.id.buttonBrowsePic)).setText(R.string.browse_picture);
            ((Button) findViewById(R.id.buttonSubmit)).setText(R.string.submit_profile);
        }
        else if (SettingsActivity.application_language_selection.equals("French")) {
            ((TextView) findViewById(R.id.FirstName)).setText(R.string.first_name_profile_fr);
            ((TextView) findViewById(R.id.LastName)).setText(R.string.last_name_profile_fr);
            ((TextView) findViewById(R.id.Birhtdate)).setText(R.string.birthdate_profile_fr);
            ((TextView) findViewById(R.id.Email)).setText(R.string.email_profile_fr);
            ((TextView) findViewById(R.id.Picture)).setText(R.string.picture_profile_fr);
            ((Button) findViewById(R.id.buttonBrowsePic)).setText(R.string.browse_picture_fr);
            ((Button) findViewById(R.id.buttonSubmit)).setText(R.string.submit_profile_fr);
        }
        else if (SettingsActivity.application_language_selection.equals("Spanish")) {
            ((TextView) findViewById(R.id.FirstName)).setText(R.string.first_name_profile_sp);
            ((TextView) findViewById(R.id.LastName)).setText(R.string.last_name_profile_sp);
            ((TextView) findViewById(R.id.Birhtdate)).setText(R.string.birthdate_profile_sp);
            ((TextView) findViewById(R.id.Email)).setText(R.string.email_profile_sp);
            ((TextView) findViewById(R.id.Picture)).setText(R.string.picture_profile_sp);
            ((Button) findViewById(R.id.buttonBrowsePic)).setText(R.string.browse_picture_sp);
            ((Button) findViewById(R.id.buttonSubmit)).setText(R.string.submit_profile_sp);
        }
        else if (SettingsActivity.application_language_selection.equals("Italian")) {
            ((TextView) findViewById(R.id.FirstName)).setText(R.string.first_name_profile_it);
            ((TextView) findViewById(R.id.LastName)).setText(R.string.last_name_profile_it);
            ((TextView) findViewById(R.id.Birhtdate)).setText(R.string.birthdate_profile_it);
            ((TextView) findViewById(R.id.Email)).setText(R.string.email_profile_it);
            ((TextView) findViewById(R.id.Picture)).setText(R.string.picture_profile_it);
            ((Button) findViewById(R.id.buttonBrowsePic)).setText(R.string.browse_picture_it);
            ((Button) findViewById(R.id.buttonSubmit)).setText(R.string.submit_profile_it);
        }
        else if (SettingsActivity.application_language_selection.equals("German")) {
            ((TextView) findViewById(R.id.FirstName)).setText(R.string.first_name_profile_ge);
            ((TextView) findViewById(R.id.LastName)).setText(R.string.last_name_profile_ge);
            ((TextView) findViewById(R.id.Birhtdate)).setText(R.string.birthdate_profile_ge);
            ((TextView) findViewById(R.id.Email)).setText(R.string.email_profile_ge);
            ((TextView) findViewById(R.id.Picture)).setText(R.string.picture_profile_ge);
            ((Button) findViewById(R.id.buttonBrowsePic)).setText(R.string.browse_picture_ge);
            ((Button) findViewById(R.id.buttonSubmit)).setText(R.string.submit_profile_ge);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}

