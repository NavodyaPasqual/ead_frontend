package com.example.ead_frontend.ui.stationOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;
import com.example.ead_frontend.ui.stationOwner.ShedRegistration;
import com.example.ead_frontend.ui.user.UserLogin;
import com.example.ead_frontend.ui.user.UserRegistration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ShedRegistration extends AppCompatActivity{

    EditText editRegistrationNumber, editShedName, editShedAddress, editContactNumber;

    CheckBox checkBox, checkBox1;

    Button button1, button2;

    String sRegistrationNumber, sShedName, sShedAddress, sShedContactNumber;

    String url = "http://192.168.43.136:8081/api/shed/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shed_registration);

        editRegistrationNumber = findViewById(R.id.editRegistrationNumber);
        editShedName = findViewById(R.id.editShedName);
        editShedAddress = findViewById(R.id.editShedAddress);
        editContactNumber = findViewById(R.id.editContactNumber);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sRegistrationNumber = editRegistrationNumber.getText().toString();
                sShedName = editShedName.getText().toString();
                sShedAddress = editShedAddress.getText().toString();
                sShedContactNumber = editContactNumber.getText().toString();
                sShedContactNumber = editContactNumber.getText().toString();

                if (!sRegistrationNumber.isEmpty() && !sShedName.isEmpty() && !sShedAddress.isEmpty() && !sShedContactNumber.isEmpty()) {
                    registerShed();
                    Toast.makeText(ShedRegistration.this, "New fuel station added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShedRegistration.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerShed(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("regNo", sRegistrationNumber);
            jsonBody.put("name", sShedName);
            jsonBody.put("address", sShedAddress);
            jsonBody.put("shedContactNo", sShedContactNumber);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    editRegistrationNumber.getText().clear();
                    editShedName.getText().clear();
                    editShedAddress.getText().clear();
                    editContactNumber.getText().clear();
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

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendToBack(View view) {
        Intent intent = new Intent(this,  UpdateHome.class);
        TextView button = (TextView) findViewById(R.id.button2);
        startActivity(intent);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this,  UpdateHome.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }
}


