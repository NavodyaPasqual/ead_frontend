package com.example.ead_frontend.ui.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.stationOwner.ListUpdate;
import com.example.ead_frontend.ui.stationOwner.UpdateHome;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;

public class PetrolArrival extends AppCompatActivity {

    //boolean availabilityCheck = false;
    Intent intent;
    ImageView signOutIcon;
    AlertDialog dialog;
    EditText editPetrolArrival, editPetrolTime, editPetrolFinish, editDieselArrival, editDieselTime, editDieselFinish;
    Button update_fuel, delete_fuel;

    String sPetrolArrival, sPetrolTime, sPetrolFinish, sDieselArrival, sDieselTime, sDieselFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_update);

        String Id = getIntent().getExtras().getString("id","defaultKey");

        Toast.makeText(PetrolArrival.this, "Id retrieved success "+Id, Toast.LENGTH_SHORT).show();

        editPetrolArrival = findViewById(R.id.editPetrolArrival);
        editPetrolTime = findViewById(R.id.editPetrolTime);
        editPetrolFinish = findViewById(R.id.editPetrolFinish);
        editDieselArrival = findViewById(R.id.editDieselArrival);
        editDieselTime = findViewById(R.id.editDieselTime);
        editDieselFinish =  findViewById(R.id.editDieselFinish);
        RequestQueue requestQueue = Volley.newRequestQueue(PetrolArrival.this);

        getFuelStationByID();

        update_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    sPetrolArrival = editPetrolArrival.getText().toString();
                    sPetrolTime = editPetrolTime.getText().toString();
                    sPetrolFinish = editPetrolFinish.getText().toString();
                    sDieselArrival = editDieselArrival.getText().toString();
                    sDieselTime = editDieselTime.getText().toString();
                    sDieselFinish = editDieselFinish.getText().toString();

                    String url = "http://192.168.43.136:8081/api/shed";

                if (!sPetrolArrival.isEmpty() && !sPetrolTime.isEmpty() && !sPetrolFinish.isEmpty() && !sDieselArrival.isEmpty() && !sDieselTime.isEmpty() && !sDieselFinish.isEmpty()) {
                    getFuelStationByID();
                    Toast.makeText(PetrolArrival.this, "New fuel station added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PetrolArrival.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getFuelStationByID(){

        String url = "http://192.168.43.136:8081/api/shed/:id";

        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                (Response.Listener<JSONObject>) response -> {

                    try {
                        editPetrolTime.setText((String)response.get("location"));
                        editDieselTime.setText((String)response.get("stationName"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PetrolArrival.this,"Station fetching failed - "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void createFuelStation(String url,String station,String location,HashMap<String,Boolean> availabilities,String arrivaleTime,RequestQueue requestQueue,String email){
        String tag_json_obj = "json_obj_req";

        JSONObject requestBody = new JSONObject();
        JSONObject requestBody1 = new JSONObject();

        try {
            for (String i:availabilities.keySet()) {
                requestBody1.put(i,availabilities.get(i));
            }

            requestBody.put("location", location);
            requestBody.put("stationName", station);
            requestBody.put("fuelAvailability", requestBody1);
            requestBody.put("fuelArrivalTime", arrivaleTime);
            requestBody.put("fuelFinishTime", arrivaleTime);
            requestBody.put("email",email);
//            requestBody.put("arrival", Calendar.getInstance().getTime().toString());
        } catch (JSONException e) {
            Toast.makeText(this,"Input value error",Toast.LENGTH_SHORT).show();
        }

        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                requestBody,
                (Response.Listener<JSONObject>) response -> {

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PetrolArrival.this,"Failed to create",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public void sendToHome(View view) {
        Intent intent = new Intent(this,  ListUpdate.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

}