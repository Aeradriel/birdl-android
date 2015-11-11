package com.birdl.birdl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.util.Set;

import activity.MainActivity;
import model.UserInformationStatic;
import model.UserResponse;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class SetProfilActivity extends Activity {
    private ImageView profilPic;
    private Button pickImage;
    RestUserInformation start;
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

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            public void intercept(RequestFacade request) {
                request.addHeader("ACCESS-TOKEN", SessionInformation.AccessToken);
            }
        };

         RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://birdl.xyz:3000/")
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("log retrofit"))
                .build();

        final RestUserInformation getUserInfo = adapter.create(RestUserInformation.class);
        Button ButtonSubmit = (Button) findViewById(R.id.buttonSubmit);

            ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String query = "{\"email\":\"" + EmailField.getText().toString() + "\"" + ",\"first_name\":\"" + FirstNameField.getText().toString() + "\"" + ",\"last_name\":\"" +
                        LastNameField.getText().toString() + "\"" + ",\"birthdate\":\"" +
                        BirthdateField.getText().toString().replace('-', '/') + "\"" + "}";
                getUserInfo.setInfo(pass, query, new Callback<UserResponse>() {
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

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}

