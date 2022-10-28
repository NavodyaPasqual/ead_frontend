///Write update delete with alert box
package com.example.ead_frontend.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_frontend.R;
import com.example.ead_frontend.ui.home.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserUpdateDelete extends AppCompatActivity {

    TextView editFullName, editNIC, editEmail, editNumber, editId;
    Button button, button2;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_delete);
        builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        editFullName = findViewById(R.id.editFullName);
        editNIC = findViewById(R.id.editNIC);
        editEmail = findViewById(R.id.editEmail);
        editNumber = findViewById(R.id.editNumber);
        editId = findViewById(R.id.editId);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        SharedPreferences sh = getSharedPreferences("UserData", MODE_PRIVATE);
        String ID = sh.getString("_id", "");
        String FullName = sh.getString("name", "");
        String Email = sh.getString("email", "");
        String Number = sh.getString("number", "");
        String NIC = sh.getString("nic", "");

        editId.setText(ID);
        editFullName.setText(FullName);
        editEmail.setText(Email);
        editNIC.setText(NIC);
        editNumber.setText(Number);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you update your account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ids) {
                                String name = editFullName.getText().toString();
                                String nic = editNIC.getText().toString();
                                String email = editEmail.getText().toString();
                                String number = editNumber.getText().toString();
                                String id = editId.getText().toString();
                                updateUser(ID, name, nic, email, number, id);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ids) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Canceled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Alert");
                alert.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you delete your account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ids) {
                                deleteUser(ID);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ids) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Canceled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Alert");
                alert.show();
            }
        });
    }

    private void updateUser(String ID, String name, String nic, String email, String number, String id) {
        String url = "http://192.168.1.5:8081/api/user/profile/update/" + ID;

        HashMap<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("nic", nic);
        body.put("email", email);
        body.put("number", number);
        body.put("id", id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(body), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        Toast.makeText(UserUpdateDelete.this, "User updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserUpdateDelete.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserUpdateDelete.this, error.toString(), Toast.LENGTH_SHORT).show();
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

    private void deleteUser(String Password) {
        String url = "http://192.168.1.5:8081/api/user/profile/delete/" + Password;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        Toast.makeText(UserUpdateDelete.this, "User Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserUpdateDelete.this, UserLogin.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserUpdateDelete.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


    public void sendToProfile(View view) {
        Intent intent = new Intent(this, UserProfile.class);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

}