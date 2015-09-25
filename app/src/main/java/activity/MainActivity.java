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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        //test
        String test = AllEventResponseStatic.events.get(0).name;
        String desc = AllEventResponseStatic.events.get(0).desc;

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
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new NewMessageActivity();
                title = getString(R.string.new_message);
                break;
            case 2:
                fragment = new InboxActivity();
                title = getString(R.string.inbox);
                break;
            case 3:
                Intent intent2 = new Intent(this, SlidingEventLayout.class);
                startActivity(intent2);
                break;
            case 4:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
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
