package com.neva.final_adumap.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.security.identity.NoAuthenticationKeyAvailableException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.neva.final_adumap.MainActivity;
import com.neva.final_adumap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    MaterialButton logIn, signUp;
    TextView NoAccount;
    EditText lbl, Email,password;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        logIn = (MaterialButton) findViewById(R.id.logIn_btn);
        signUp = (MaterialButton) findViewById(R.id.signUp_btn);
        progressDialog = new ProgressDialog(this);
        lbl = (EditText)findViewById(R.id.lblKlasmeyt);
        lbl.setEnabled(false);

        NoAccount = (TextView)  findViewById(R.id.noAcc_tv);

        Email = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);

        EditText[] arr = {Email, password};

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(EditText editText : arr){
                    if(editText.getText().toString().trim().isEmpty()){
                        editText.setError("Field must not be empty!");
                        editText.requestFocus();

                        Log.e("Login", "Login Success");
                        return;
                    }
                }
                String email = Email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                fetchFromDb(email,pass);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUp);
            }
        });

        NoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noAcc = new Intent(SignInActivity.this, forum_noAcc.class);
                startActivity(noAcc);
            }
        });
    }

    private void fetchFromDb(final String email, final String pass){

        String url = "https://adumap.online/loginMobile.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        progressDialog.setMessage("Verifying User");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("User Found")){
                    progressDialog.hide();
                    Intent intent = new Intent(SignInActivity.this,forum_HomePage.class);
                    startActivity(intent);
                }else if(response.equals("Invalid Credentials!")){
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(),"Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(),"Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Log.e("VolleyError", "Error during sign-in", error);
                Toast.makeText(getApplicationContext(), "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected HashMap<String,String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", pass);
                return map;
            }
        };
        Thread timer = new Thread(){
            @Override
            public  void run(){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    requestQueue.add(stringRequest);
                }
            }
        };
        timer.start();
    }
}