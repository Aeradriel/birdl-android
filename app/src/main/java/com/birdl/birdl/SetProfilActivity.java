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

import java.io.FileNotFoundException;
import java.io.InputStream;

import activity.MainActivity;

/**
 * Created by Christophe on 08/08/2015.
 */
public class SetProfilActivity extends Activity {
    private ImageView profilPic;
    private Button pickImage;
    private final int SELECT_PHOTO = 1;
    public static TextView usernameModif;
    private EditText usernameField = null;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_profil_activity);

        usernameField = (EditText) findViewById(R.id.profilNameField);
        usernameField.setText(usernameModif.getText().toString());

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
        Button ButtonSubmit = (Button) findViewById(R.id.buttonSubmit);
        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.usernameModif = usernameField.getText().toString();
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
