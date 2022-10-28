package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.Handler.HttpHandler;
import com.example.ead_frontend.ui.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity {

    TextView name, nic, nic2, email, number, id;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.name);
        nic = findViewById(R.id.nic);
        nic2 = findViewById(R.id.nic2);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        id = findViewById(R.id.id);
        delete = findViewById(R.id.delete);

        SharedPreferences sh = getSharedPreferences("UserData", MODE_PRIVATE);
        String Name = sh.getString("name", "");
        String NIC = sh.getString("nic", "");
        String NIC2 = sh.getString("nic", "");
        String Email = sh.getString("email", "");
        String Number = sh.getString("number", "");
        String Id = sh.getString("_id", "");
        String Token = sh.getString("token", "");

        name.setText(Name);
        nic.setText(NIC);
        nic2.setText(NIC2);
        email.setText(Email);
        number.setText(Number);
        id.setText(Id);

    }

    private void getByID () {
        SharedPreferences sh = getSharedPreferences("UserData", MODE_PRIVATE);
        String Id = sh.getString("_id", "");

        id.setText(Id);

        String url = "http://192.168.1.5:8081/api/user/profile/" + Id;
        HttpHandler handler = new HttpHandler();
        String jsonStr = handler.makeServiceCall(url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("USER", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(stringRequest);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    public void actionLogout(View view) {
        Intent intent = new Intent(this, UserLogin.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    public void sendToEditProfile(View view) {
        Intent intent = new Intent(this, UserUpdateDelete.class);
        startActivity(intent);
    }

    public void sendToDeleteProfile(View view) {
        Intent intent = new Intent(this, UserUpdateDelete.class);
        Button button = (Button) findViewById(R.id.delete);
        startActivity(intent);
    }
}
