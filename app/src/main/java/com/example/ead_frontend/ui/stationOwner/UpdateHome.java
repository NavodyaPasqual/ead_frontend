package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;

public class UpdateHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_home);
    }

    //Navigate to previous ui
    public void sendToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    //Send to petrol status update form
    public void sendToShedRegistration(View view) {
        Intent intent = new Intent(this,  ShedRegistration.class);
        RelativeLayout image = (RelativeLayout) findViewById(R.id.shed_registration);
        startActivity(intent);
    }

    public void sendToListUpdate(View view) {
        Intent intent = new Intent(this,  ListUpdate.class);
        RelativeLayout image = (RelativeLayout) findViewById(R.id.update_petrol);
        startActivity(intent);
    }

    public void sendToListDelete(View view) {
        Intent intent = new Intent(this,  ListUpdate.class);
        RelativeLayout image = (RelativeLayout) findViewById(R.id.delete_diesel);
        startActivity(intent);
    }

}
