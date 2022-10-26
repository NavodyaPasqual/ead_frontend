package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;

public class UserRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }
    public void sendToLogin(View view) {
        Intent intent = new Intent(this,  UserLogin.class);
        TextView button = (TextView) findViewById(R.id.txtSignIn);
        startActivity(intent);
    }
    public void sendToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        Button button = (Button) findViewById(R.id.button);
        startActivity(intent);
    }
}