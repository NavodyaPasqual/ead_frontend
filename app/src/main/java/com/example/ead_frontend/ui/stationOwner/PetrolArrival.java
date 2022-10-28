package com.example.ead_frontend.ui.stationOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.user.UserLogin;
import com.example.ead_frontend.ui.user.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PetrolArrival extends AppCompatActivity {

    TextView editPetrolArrival, editPetrolTime, editPetrolFinish, editDieselArrival, editDieselTime, editDieselFinish, editId;
    Button button, button2;
    String base = "635bb1e7c9b7692150351327";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petrol_arrival);

        editPetrolArrival = findViewById(R.id.editPetrolArrival);
        editPetrolTime = findViewById(R.id.editPetrolTime);
        editPetrolFinish = findViewById(R.id.editPetrolFinish);
        editDieselArrival = findViewById(R.id.editDieselArrival);
        editDieselTime = findViewById(R.id.editDieselTime);
        editDieselFinish = findViewById(R.id.editDieselFinish);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petrolArrivalTime = editPetrolArrival.getText().toString();
                String petrolAvailable = editPetrolTime.getText().toString();
                String petrolFinishTime = editPetrolFinish.getText().toString();
                String dieselArrivalTime = editDieselArrival.getText().toString();
                String dieselAvailable = editDieselTime.getText().toString();
                String dieselFinishTime = editDieselFinish.getText().toString();
                updateShed(petrolArrivalTime, petrolAvailable, petrolFinishTime, dieselArrivalTime, dieselAvailable, dieselFinishTime);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }

    private void updateShed(String petrolArrivalTime, String petrolAvailable, String petrolFinishTime, String dieselArrivalTime, String dieselAvailable, String dieselFinishTime) {
        String url = "http://192.168.43.136:8081/api/shed/update/" + base;

        HashMap<String, String> body = new HashMap<>();
        body.put("petrolArrivalTime", petrolArrivalTime);
        body.put("petrolAvailable", petrolAvailable);
        body.put("petrolFinishTime", petrolFinishTime);
        body.put("dieselArrivalTime", dieselArrivalTime);
        body.put("dieselAvailable", dieselAvailable);
        body.put("dieselFinishTime", dieselFinishTime);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(body), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        Toast.makeText(PetrolArrival.this, "Arrival time updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PetrolArrival.this, AvailabilityList.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(com.example.ead_frontend.ui.stationOwner.PetrolArrival.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void deleteUser() {
        String url = "http://192.168.43.136:8081/api/shed/delete/" + base;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        Toast.makeText(PetrolArrival.this, "Arrival time Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PetrolArrival.this, UpdateHome.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(com.example.ead_frontend.ui.stationOwner.PetrolArrival.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this,  UpdateHome.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

}