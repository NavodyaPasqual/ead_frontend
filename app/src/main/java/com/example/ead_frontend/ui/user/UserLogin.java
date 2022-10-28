package com.example.ead_frontend.ui.user;

import static com.example.ead_frontend.ui.EndPoints.EndPoints.USER_LOGIN_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.Handler.HttpHandler;
import com.example.ead_frontend.ui.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserLogin extends AppCompatActivity {

    Button button;
    String lMail, lPassword;
    EditText editSignInEmail, editSignInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        editSignInEmail = findViewById(R.id.editSignInEmail);
        editSignInPassword = findViewById(R.id.editSignInPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lMail = editSignInEmail.getText().toString();
                lPassword = editSignInPassword.getText().toString();
                if (!lMail.isEmpty() && !lPassword.isEmpty()) {
                    loginUser();
                    Toast.makeText(UserLogin.this, "Welcome to CEYPETCO", Toast.LENGTH_SHORT).show();
                } else if(lMail.isEmpty()){
                    Toast.makeText(UserLogin.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else if (lPassword.isEmpty()) {
                    Toast.makeText(UserLogin.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserLogin.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Calling the login User endpoint
    private void loginUser() {
        try {
            String url = USER_LOGIN_URL;
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", lMail);
            jsonBody.put("password", lPassword);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor User = sharedPreferences.edit();
                        String Name = jsonObj.getString("name");
                        String Email = jsonObj.getString("email");
                        String NIC = jsonObj.getString("nic");
                        String Number = jsonObj.getString("number");
                        String Id = jsonObj.getString("_id");
                        String Token = jsonObj.getString("token");

                        User.putString("name", Name);
                        User.putString("email", Email);
                        User.putString("nic", NIC);
                        User.putString("number", Number);
                        User.putString("_id", Id);
                        User.putString("token", Token);
                        User.commit();

                        Log.i("USER", Name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent homeIntent = new Intent(UserLogin.this, MainActivity.class);
                    startActivity(homeIntent);
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
            };
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendToRegistration(View view) {
        Intent intent = new Intent(this, UserRegistration.class);
        TextView button = (TextView) findViewById(R.id.txtSignIn);
        startActivity(intent);
    }

    public void sendToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Button button = (Button) findViewById(R.id.button);
        startActivity(intent);
    }
}