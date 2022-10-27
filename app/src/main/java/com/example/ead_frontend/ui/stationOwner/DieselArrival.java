package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.R;

public class DieselArrival extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diesel_arrival);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this,  UpdateHome.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
}