package com.example.ead_frontend.ui.stationOwner;

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
import com.example.ead_frontend.ui.home.MainActivity;
import com.example.ead_frontend.ui.stationOwner.ListUpdate;
import com.example.ead_frontend.ui.stationOwner.ListUpdate;
import com.example.ead_frontend.ui.user.UserProfile;

public class AvailabilityList extends AppCompatActivity {

    TextView shedname, shedaddress, davailable, pavailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_list);

        shedname = findViewById(R.id.shedname);
        shedaddress = findViewById(R.id.shedaddress);
        davailable = findViewById(R.id.davailable);
        pavailable = findViewById(R.id.pavailable);

        SharedPreferences sh = getSharedPreferences("ShedData", MODE_PRIVATE);
        String Shedname = sh.getString("name", "");
        String Shedaddress = sh.getString("address", "");
        String Davailable = sh.getString("dieselAvailable", "");
        String Pavailable = sh.getString("petrolAvailable", "");

        shedname.setText(Shedname);
        shedaddress.setText(Shedaddress);
        davailable.setText(Davailable);
        pavailable.setText(Pavailable);
    }

//    private void deleteShed(String Shedaddress) {
//        String url = "http://192.168.43.136:8081/api/shed/delete/" + Shedaddress;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    if(response.getBoolean("success")) {
//                        Toast.makeText(AvailabilityList.this, "User Deleted", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(AvailabilityList.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(AvailabilityList.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//        );
//
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjectRequest);
//    }
    public void sendToHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
}