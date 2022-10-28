package com.example.ead_frontend.ui.queue;

import static com.example.ead_frontend.ui.EndPoints.EndPoints.QUEUE_CREATE_URL;


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
import com.example.ead_frontend.ui.queue.queue_join;
import com.example.ead_frontend.ui.user.UserLogin;
import com.example.ead_frontend.ui.user.UserRegistration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class queue_create extends AppCompatActivity {

    EditText idQC, nicQC, shedNameQC;

    Button btnCreateQC;

    String sID, sNIC, sShedName;

    String url = QUEUE_CREATE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_create);

        idQC = findViewById(R.id.idQC);
        nicQC = findViewById(R.id.nicQC);
        shedNameQC = findViewById(R.id.shedNameQC);
        btnCreateQC = findViewById(R.id.btnCreateQC);

        btnCreateQC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sID = idQC.getText().toString();
                sNIC = nicQC.getText().toString();
                sShedName = shedNameQC.getText().toString();


                if (!sID.isEmpty() && !sNIC.isEmpty() && !sShedName.isEmpty()) {
                    createQueue();
                    Toast.makeText(queue_create.this, "Added to the queue", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(queue_create.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createQueue(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", sID);
            jsonBody.put("nic", sNIC);
            jsonBody.put("fuelStation", sShedName);


            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    idQC.getText().clear();
                    nicQC.getText().clear();
                    shedNameQC.getText().clear();


                    Intent intent = new Intent(queue_create.this, queue_join.class);
                    startActivity(intent);
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

    public void sendToQueueJoin(View view) {
        Intent intent = new Intent(this,  queue_join.class);
        Button button = (Button) findViewById(R.id.btnCreateQC);
        startActivity(intent);
    }
}