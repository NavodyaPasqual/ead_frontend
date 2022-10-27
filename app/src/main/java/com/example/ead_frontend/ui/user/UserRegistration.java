package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserRegistration extends AppCompatActivity {

    EditText editSignUpFullName, editNIC, editSignUpEmail, editSignUpMobile, editSignUpPassword, editSignUpConfirmPassword;

    Button button;

    String sFullName, sNIC, sEmail, sMobile, sPassword;

    String url = "http://192.168.43.136:8081/api/user/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        editSignUpFullName = findViewById(R.id.editSignUpFullName);
        editNIC = findViewById(R.id.editNIC);
        editSignUpEmail = findViewById(R.id.editSignUpEmail);
        editSignUpMobile = findViewById(R.id.editSignUpMobile);
        editSignUpPassword = findViewById(R.id.editSignUpPassword);
        editSignUpConfirmPassword = findViewById(R.id.editSignUpConfirmPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sFullName = editSignUpFullName.getText().toString();
                sNIC = editNIC.getText().toString();
                sEmail = editSignUpEmail.getText().toString();
                sMobile = editSignUpMobile.getText().toString();
                sPassword = editSignUpPassword.getText().toString();

                if (!sFullName.isEmpty() && !sEmail.isEmpty() && !sPassword.isEmpty() && !sMobile.isEmpty() && !sNIC.isEmpty()) {
                    registerUser();
                    Toast.makeText(UserRegistration.this, "User registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserRegistration.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", sFullName);
            jsonBody.put("email", sEmail);
            jsonBody.put("nic", sNIC);
            jsonBody.put("number", sMobile);
            jsonBody.put("password", sPassword);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    editSignUpFullName.getText().clear();
                    editNIC.getText().clear();
                    editSignUpMobile.getText().clear();
                    editSignUpEmail.getText().clear();
                    editSignUpPassword.getText().clear();
                    editSignUpConfirmPassword.getText().clear();
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