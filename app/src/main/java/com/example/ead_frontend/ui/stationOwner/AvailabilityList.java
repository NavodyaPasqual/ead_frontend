package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.R;

public class AvailabilityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_list);
    }
    public void sendToHome(View view) {
        Intent intent = new Intent(this,  FuelType.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
}