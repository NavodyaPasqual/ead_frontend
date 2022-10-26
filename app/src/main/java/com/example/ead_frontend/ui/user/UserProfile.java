package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
    public void sendToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
    public void sendToEditProfile(View view) {
        Intent intent = new Intent(this,  UserUpdateDelete.class);
        TextView button = (TextView) findViewById(R.id.edit);
        startActivity(intent);
    }
    public void sendToDeleteProfile(View view) {
        Intent intent = new Intent(this,  UserUpdateDelete.class);
        TextView button = (TextView) findViewById(R.id.delete);
        startActivity(intent);
    }
}
