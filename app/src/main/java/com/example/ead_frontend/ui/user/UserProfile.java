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

public class UserProfile extends AppCompatActivity {

    TextView name, nic, nic2, email, number, password;
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
        password = findViewById(R.id.password);
        delete = findViewById(R.id.delete);

        SharedPreferences sh = getSharedPreferences("UserData", MODE_PRIVATE);
        String Name = sh.getString("name", "");
        String NIC = sh.getString("nic", "");
        String NIC2 = sh.getString("nic", "");
        String Email = sh.getString("email", "");
        String Number = sh.getString("number", "");
        String Password = sh.getString("_id", "");
        String Token = sh.getString("token", "");

        name.setText(Name);
        nic.setText(NIC);
        nic2.setText(NIC2);
        email.setText(Email);
        number.setText(Number);
        password.setText(Password);

//        delete.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  deleteUser(Password);
//              }
//          }
//        );
    }

//    private void deleteUser(String Password) {
//        String url = "http://192.168.1.5:8081/api/user/profile/delete/" + Password;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    if(response.getBoolean("success")) {
//                        Toast.makeText(UserProfile.this, "User Deleted", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(UserProfile.this, UserLogin.class);
//                        startActivity(intent);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//        );
//
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjectRequest);
//    }

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
