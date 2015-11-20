package activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.birdl.birdl.HomeActivity;
import com.birdl.birdl.InboxActivity;
import com.birdl.birdl.Login;
import com.birdl.birdl.NewMessageActivity;
import com.birdl.birdl.R;
import com.birdl.birdl.SetProfilActivity;

import model.AllEventResponseStatic;
import model.UserInformationStatic;

import com.birdl.birdl.SettingsActivity;
import com.birdl.birdl.SlidingEventLayout;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    public static ImageView setProfil;
    public static Bitmap defaultImage = null;
    public static TextView FirstName;
    public static String FirstNameModif = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        //Firstname
        FirstName = (TextView) findViewById(R.id.usernameProfil);
        if (FirstNameModif != null) {
            FirstName.setText(FirstNameModif);
            SetProfilActivity.FirstNameModif = FirstNameModif;
        }
        else {
            FirstName.setText(UserInformationStatic.getFirst_name());
            SetProfilActivity.FirstNameModif = UserInformationStatic.getFirst_name();
        }

        //lastName
        SetProfilActivity.LastNameModif = UserInformationStatic.getLast_name();

        //Email
        SetProfilActivity.BirthdateModif = UserInformationStatic.getBirthdate();

        //Email
        SetProfilActivity.EmailModif = UserInformationStatic.getEmail();

        //profil picture
        setProfil = (ImageView) findViewById(R.id.imageProfil);
        if (defaultImage != null) {
            setProfil.setImageBitmap(defaultImage);
        }

        setProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.birdl.birdl.SetProfilActivity");
                startActivity(intent);
            }
        });

        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (SettingsActivity.application_language_selection.equals("English")) {
            menu.findItem(R.id.action_about).setTitle(R.string.action_about_birdl);
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings);
        }
        else if (SettingsActivity.application_language_selection.equals("French")) {
            menu.findItem(R.id.action_about).setTitle(R.string.action_about_birdl_fr);
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_fr);
        }
        else if (SettingsActivity.application_language_selection.equals("Spanish")) {
            menu.findItem(R.id.action_about).setTitle(R.string.action_about_birdl_sp);
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_sp);
        }
        else if (SettingsActivity.application_language_selection.equals("Italian")) {
            menu.findItem(R.id.action_about).setTitle(R.string.action_about_birdl_it);
            menu.findItem(R.id.action_settings).setTitle(R.string.action_settings_it);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent("com.birdl.birdl.AboutActivity");
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent("com.birdl.birdl.SettingsActivity");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeActivity();
                if (SettingsActivity.application_language_selection.equals("English"))
                    title = getString(R.string.title_home);
                else if (SettingsActivity.application_language_selection.equals("French"))
                    title = getString(R.string.title_home_fr);
                else if (SettingsActivity.application_language_selection.equals("Spanish"))
                    title = getString(R.string.title_home_sp);
                else if (SettingsActivity.application_language_selection.equals("Italian"))
                    title = getString(R.string.title_home_it);
                break;
            case 1:
                fragment = new NewMessageActivity();
                if (SettingsActivity.application_language_selection.equals("English"))
                    title = getString(R.string.new_message);
                else if (SettingsActivity.application_language_selection.equals("French"))
                    title = getString(R.string.new_message_fr);
                else if (SettingsActivity.application_language_selection.equals("Spanish"))
                    title = getString(R.string.new_message_sp);
                else if (SettingsActivity.application_language_selection.equals("Italian"))
                    title = getString(R.string.new_message_it);
                break;
            case 2:
                fragment = new InboxActivity();
                if (SettingsActivity.application_language_selection.equals("English"))
                    title = getString(R.string.inbox);
                else if (SettingsActivity.application_language_selection.equals("French"))
                    title = getString(R.string.inbox_fr);
                else if (SettingsActivity.application_language_selection.equals("Spanish"))
                    title = getString(R.string.inbox_sp);
                else if (SettingsActivity.application_language_selection.equals("Italian"))
                    title = getString(R.string.inbox_it);
                break;
            case 3:
                Intent intent = new Intent("com.birdl.birdl.SlidingEventLayout");
                startActivity(intent);
                break;
            case 4:
                Intent intent2 = new Intent(this, Login.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            getSupportActionBar().setTitle(title);
        }
    }
}
