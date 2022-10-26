package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.ui.home.MainActivity;
import com.example.ead_frontend.R;

public class FuelType extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_type);

    }

    //Navigate to previous ui
    public void backToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        ImageButton image = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    //Send to petrol status update form
    public void sendToPetrolArrival(View view) {
        Intent intent = new Intent(this,  PetrolArrival.class);
        RelativeLayout image = (RelativeLayout) findViewById(R.id.send_petrol);
        startActivity(intent);
    }

    //Send to diesel status update form
    public void sendToDieselArrival(View view) {
        Intent intent = new Intent(this,  DieselArrival.class);
        RelativeLayout image = (RelativeLayout) findViewById(R.id.send_diesel);
        startActivity(intent);
    }

}
