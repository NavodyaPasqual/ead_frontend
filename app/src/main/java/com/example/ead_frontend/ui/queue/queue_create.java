package com.example.ead_frontend.ui.queue;

//import static com.example.ead_frontend.ui.EndPoints.EndPoints.QUEUE_CREATE_URL;


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

    //String url = QUEUE_CREATE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_create);

    }
}