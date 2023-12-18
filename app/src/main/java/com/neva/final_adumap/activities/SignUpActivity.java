package com.neva.final_adumap.activities;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.neva.final_adumap.R;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password, Email;
    MaterialButton Create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.newUsername);
        password = (EditText) findViewById(R.id.newPassword);
        Email = (EditText) findViewById(R.id.txtemail);

        Create = (MaterialButton) findViewById(R.id.createAcc_btn);

        EditText[] arr = {username,password, Email};

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(EditText text : arr){
                    if(text.getText().toString().trim().isEmpty()){
                        text.setError("Field must not be empty!");
                        text.requestFocus();
                        return;
                    }
                }

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String email = Email.getText().toString().trim();

                if(isValidEmail(email)){
                    submitToDB(user,pass,email);

                    Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                    startActivity(intent);
                }else{
                    Email.setError("Use Adamson Email!");
                }
            }
        });
    }

    private void submitToDB(final String user, final String pass, final String email ){

        String url = "https://adumap.online/signup.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "error", error);
            }
        }){
            protected HashMap<String,String> getParams() throws AuthFailureError{
                HashMap<String,String> map = new HashMap<>();

                map.put("user", user);
                map.put("password", pass);
                map.put("email", email);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean isValidEmail(String email){
        String domain = "@adamson.edu.ph";

        int atIndex = email.lastIndexOf("@");

        if(atIndex != -1 && email.substring(atIndex).equals(domain)){
            Log.d("Valid", "valid");
            return true;
        }else{
            Log.d("Invalid", "invalid");
            return false;
        }
    }
}