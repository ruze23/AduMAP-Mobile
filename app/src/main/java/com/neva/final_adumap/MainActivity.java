package com.neva.final_adumap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.neva.final_adumap.activities.MAP_SV;
import com.neva.final_adumap.activities.SignInActivity;

public class MainActivity extends AppCompatActivity {

    MaterialButton map, forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = (MaterialButton) findViewById(R.id.map_btn);
        forum = (MaterialButton) findViewById(R.id.forum_btn);

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signin);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(MainActivity.this, MAP_SV.class);
                startActivity(map);
            }
        });
    }
}