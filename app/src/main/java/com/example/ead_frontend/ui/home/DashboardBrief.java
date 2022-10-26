package com.example.ead_frontend.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.user.UserProfile;

public class DashboardBrief extends AppCompatActivity {

    ImageButton ibtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_brief);

        ibtn1 = (ImageButton) findViewById(R.id.imageButton13);

        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void sendToMyProfile(View view) {
        Intent intent = new Intent(this, UserProfile.class);
        CardView button = (CardView) findViewById(R.id.cardView12);
        startActivity(intent);
    }
}