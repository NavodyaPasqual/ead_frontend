package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;
import com.example.ead_frontend.ui.stationOwner.ListUpdate;

public class ListUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_update);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    public void sendToUpdateFuel(View view) {
        Intent intent = new Intent(this,  PetrolArrival.class);
        TextView button = (TextView) findViewById(R.id.update_fuel);
        startActivity(intent);
    }

    public void sendToDeleteFuel(View view) {
        Intent intent = new Intent(this,  PetrolArrival.class);
        TextView button = (TextView) findViewById(R.id.delete_fuel);
        startActivity(intent);
    }
}