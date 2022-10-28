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
import com.example.ead_frontend.ui.stationOwner.ShedRegistration;

public class ListUpdate extends AppCompatActivity {

    TextView shedname, shedaddress, davailable, pavailable;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_update);

        shedname = findViewById(R.id.shedname);
        shedaddress = findViewById(R.id.shedaddress);
        davailable = findViewById(R.id.davailable);
        pavailable = findViewById(R.id.pavailable);
        delete = findViewById(R.id.delete);

//        SharedPreferences sh = getSharedPreferences("ShedData", MODE_PRIVATE);
//        String RegNo = sh.getString("regNo", "");
//        String ShedName = sh.getString("name", "");
//        String Address = sh.getString("address", "");
//        String Number = sh.getString("_id", "");
//
//        shedname.setText(RegNo);
//        shedaddress.setText(ShedName);
//        davailable.setText(Address);
//        pavailable.setText(Number);

        delete.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          deleteShed();
                                      }
                                  }
        );
    }

    private void deleteShed() {
        String url = "http://192.168.43.136:8081/api/shed/delete/" + "6358183d044f1044446e96b5";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        Toast.makeText(ListUpdate.this, "Shed Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListUpdate.this, ShedRegistration.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListUpdate.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    public void sendToUpdateFuel(View view) {
        Intent intent = new Intent(this,  PetrolArrival.class);
        TextView button = (TextView) findViewById(R.id.updatefuel);
        startActivity(intent);
    }

    public void sendToDeleteFuel(View view) {
        Intent intent = new Intent(this,  PetrolArrival.class);
        TextView button = (TextView) findViewById(R.id.delete);
        startActivity(intent);
    }
}