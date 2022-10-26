package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ead_frontend.R;

public class UserUpdateDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_delete);
    }

    public void sendToProfile(View view) {
        Intent intent = new Intent(this,  UserProfile.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
}