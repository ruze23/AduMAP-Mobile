package com.neva.final_adumap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.neva.final_adumap.MainActivity;
import com.neva.final_adumap.R;
import com.neva.final_adumap.classes.Adapter;
import com.neva.final_adumap.classes.VolleySingleton;
import com.neva.final_adumap.classes.posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class forum_HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recycler;
    MaterialButton post;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar ToolBar;

    private List<posts> PostsList;

    private  RequestQueue requestQueue;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_home_page);
        recycler = (RecyclerView) findViewById(R.id.recyclerView);

        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.nav_view);
        ToolBar = findViewById(R.id.toolbar_id);

        post = (MaterialButton) findViewById(R.id.post_btn);


        Menu menu  = navigationView.getMenu();
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(false);

        navigationView.bringToFront();
        setSupportActionBar(ToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,ToolBar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_forum);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        PostsList = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        fetchPosts();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forum_HomePage.this,forum_PostPage.class);
                startActivity(intent);
            }
        });
    }

    private void fetchPosts() {
        String url = "https://adumap.online/fetchPosts.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("JSON Response", response.toString());
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String user = jsonObject.getString("name");
                        String content = jsonObject.getString("comment");
                        String date = jsonObject.getString("date");

                        posts post = new posts(user,content,date);
                        PostsList.add(post);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Adapter adapter = new Adapter(forum_HomePage.this,PostsList);
                    recycler.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
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
            Intent intent = new Intent(forum_HomePage.this,MainActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_map){
            Intent intent = new Intent(forum_HomePage.this, MAP_SV.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_forum){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (clicked == R.id.nav_login){
            Intent intent = new Intent(forum_HomePage.this, SignInActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_logout){
            Intent intent = new Intent(forum_HomePage.this, SignInActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid!",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}