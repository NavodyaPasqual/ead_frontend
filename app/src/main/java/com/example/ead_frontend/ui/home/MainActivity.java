package com.example.ead_frontend.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.queue.queue_create;
import com.example.ead_frontend.ui.stationOwner.AvailabilityList;
import com.example.ead_frontend.ui.stationOwner.UpdateHome;
import com.example.ead_frontend.ui.user.UserProfile;
import com.example.ead_frontend.ui.user.UserUpdateDelete;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.appBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void sendToJoinQueue(View view) {
        Intent intent = new Intent(this,  queue_create.class);
        CardView button = (CardView) findViewById(R.id.card1);
        startActivity(intent);
    }

    public void sendToAvailableShed(View view) {
        Intent intent = new Intent(this,  AvailabilityList.class);
        CardView button = (CardView) findViewById(R.id.card2);
        startActivity(intent);
    }
    public void sendToMyProfile(View view) {
        Intent intent = new Intent(this,  UserProfile.class);
        CardView button = (CardView) findViewById(R.id.card3);
        startActivity(intent);
    }
    public void sendToShedOwner(View view) {
        Intent intent = new Intent(this,  UpdateHome.class);
        CardView button = (CardView) findViewById(R.id.card4);
        startActivity(intent);
    }
}