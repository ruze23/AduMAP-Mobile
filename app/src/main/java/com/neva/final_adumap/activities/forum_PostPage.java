package com.neva.final_adumap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.neva.final_adumap.MainActivity;
import com.neva.final_adumap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class forum_PostPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar ToolBar;
    MaterialButton post;
    EditText user,content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post_page);

        drawerLayout = findViewById(R.id.layoutDrawer);
        navigationView = findViewById(R.id.nav_view1);
        ToolBar = findViewById(R.id.toolbar_ID);

        post = (MaterialButton) findViewById(R.id.post_btn);

        user = (EditText) findViewById(R.id.titletxt);
        content = (EditText) findViewById(R.id.contenttxt);

        EditText edit[] = {user,content};

        navigationView.bringToFront();
        setSupportActionBar(ToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,ToolBar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_forum);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(EditText editText : edit){
                    if(editText.getText().toString().trim().isEmpty()){
                        editText.setError("Field must not be empty!");
                        editText.requestFocus();
                        return;
                    }
                }
                String username = user.getText().toString().trim();
                String body = content.getText().toString().trim();

                submitToDB(username,body);
                Toast.makeText(getApplicationContext(),"Post Created Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(forum_PostPage.this,forum_HomePage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int clicked = item.getItemId();

        if(clicked == R.id.nav_home){
            Intent intent = new Intent(forum_PostPage.this, MainActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_map){
            Intent intent = new Intent(forum_PostPage.this, MAP_SV.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_forum){
            Intent intent = new Intent(forum_PostPage.this, forum_HomePage.class);
            startActivity(intent);
        } else if (clicked == R.id.nav_login){
            Intent intent = new Intent(forum_PostPage.this, SignInActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_logout){
            Intent intent = new Intent(forum_PostPage.this, SignInActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid!",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void submitToDB(final String user, final String content){

        String url = "https://adumap.online/forumMobile.php";

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
        }) {
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<String, String>();

                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


                params.put("name", user);
                params.put("comment", content);
                params.put("date", currentDate);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}