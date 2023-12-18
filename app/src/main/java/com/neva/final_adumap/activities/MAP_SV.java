package com.neva.final_adumap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.neva.final_adumap.MainActivity;
import com.neva.final_adumap.R;

public class MAP_SV extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Spinner floor, room;
    ImageView selected;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar ToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sv);

        floor  = (Spinner) findViewById(R.id.spinnerFloor);
        room = (Spinner) findViewById(R.id.spinnerRoom);
        selected = (ImageView) findViewById(R.id.selectedRoomFloor);

        drawerLayout = findViewById(R.id.DrawerLayout2);
        navigationView = findViewById(R.id.nav_view2);
        ToolBar = findViewById(R.id.toolbar_id2);


        Menu menu  = navigationView.getMenu();
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_logout).setVisible(false);

        navigationView.bringToFront();
        setSupportActionBar(ToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,ToolBar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_map);

        ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter.createFromResource(this,R.array.Floor,android.R.layout.simple_spinner_item);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floor.setAdapter(floorAdapter);

        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFloor = parent.getItemAtPosition(position).toString();
                populateRoomSpinner(selectedFloor);
                changeBgImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeBgImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void changeBgImage() {

        String selectedFloor = floor.getSelectedItem().toString();
        String selectedRoom = room.getSelectedItem().toString();
        int imageResource = 0;
        switch (selectedFloor){

            case "1st Floor":
                imageResource = R.drawable.sv_gfloor;
                if(selectedRoom.equals("Alumni")) {
                    imageResource = R.drawable.alumni;
                } else if (selectedRoom.equals("IT and CS Department")) {
                    imageResource = R.drawable.sv_it;
                } else if (selectedRoom.equals("Archive Museum")) {
                    imageResource = R.drawable.sv_archive;
                }else if(selectedRoom.equals("OIA Office")){
                    imageResource = R.drawable.oia_office;
                } else if (selectedRoom.equals("Office for Vicentian Mission")) {
                    imageResource = R.drawable.vincentian;
                } else if (selectedRoom.equals("Archive Office")) {
                    imageResource = R.drawable.archives_office;
                } else if (selectedRoom.equals("AUFEA")) {
                    imageResource = R.drawable.aufea;
                } else if (selectedRoom.equals("College of Law Library")) {
                    imageResource = R.drawable.law_lib;
                } else if (selectedRoom.equals("College of Law Moot Court")) {
                    imageResource = R.drawable.law_moot;
                } else if (selectedRoom.equals("College of Graduate")) {
                    imageResource = R.drawable.college_graduate;
                } else if (selectedRoom.equals("SV Clinic")) {
                    imageResource = R.drawable.sv_clinic;
                }else{
                    imageResource = R.drawable.sv_gfloor;
                }
                break;
            case "2nd Floor":
                imageResource = R.drawable.sv_second;
                if(selectedRoom.equals("AGGSA")) {
                    imageResource = R.drawable.aggsa_office;
                } else if (selectedRoom.equals("College of Law Faculty Room")) {
                    imageResource = R.drawable.law_faculty;
                } else if (selectedRoom.equals("CS Department")) {
                    imageResource = R.drawable.cs_dept;
                }else if(selectedRoom.equals("Female Comfort Room")){
                    imageResource = R.drawable.cr_f;
                } else if (selectedRoom.equals("Graduate School Conference Room")) {
                    imageResource = R.drawable.gs_conferoom;
                } else if (selectedRoom.equals("Graduate School Consultation Room")) {
                    imageResource = R.drawable.gs_consult;
                } else if (selectedRoom.equals("Graduate School Dean Office")) {
                    imageResource = R.drawable.gs_dean;
                } else if (selectedRoom.equals("Legal Aid Office")) {
                    imageResource = R.drawable.lao;
                } else if (selectedRoom.equals("Male Comfort Room")) {
                    imageResource = R.drawable.cr_m;
                } else if (selectedRoom.equals("Room 202")) {
                    imageResource = R.drawable.svtotw;
                } else if (selectedRoom.equals("Room 203")) {
                    imageResource = R.drawable.sv_tofo;
                } else if (selectedRoom.equals("Room 204")) {
                    imageResource = R.drawable.sv_toth;
                } else if (selectedRoom.equals("Room 212")) {
                    imageResource = R.drawable.sv_tit;
                } else if (selectedRoom.equals("Room 213")) {
                    imageResource = R.drawable.sv_tith;
                } else if (selectedRoom.equals("Room 214")) {
                    imageResource = R.drawable.sv_tifo;
                } else if (selectedRoom.equals("Room 215")) {
                    imageResource = R.drawable.sv_tifi;
                } else if (selectedRoom.equals("Room 216")) {
                    imageResource = R.drawable.sv_tisx;
                } else if (selectedRoom.equals("Room 217")) {
                    imageResource = R.drawable.sv_tisv;
                } else if (selectedRoom.equals("Room 218")) {
                    imageResource = R.drawable.sv_tieg;
                } else if (selectedRoom.equals("ADR")) {
                    imageResource = R.drawable.adr;
                } else{
                    imageResource = R.drawable.sv_second;
                }
                break;
            case "3rd Floor":
                imageResource = R.drawable.sv_third;
                if(selectedRoom.equals("ACOMSS Office")) {
                    imageResource = R.drawable.acomss;
                } else if (selectedRoom.equals("CS Dept. Network Lab")) {
                    imageResource = R.drawable.cs_dnlab;
                } else if (selectedRoom.equals("CS Dept. Digital Design Lab")) {
                    imageResource = R.drawable.cs_digilab;
                }else if(selectedRoom.equals("CS Presentation Room")){
                    imageResource = R.drawable.cs_present;
                } else if (selectedRoom.equals("Female Comfort Room")) {
                    imageResource = R.drawable.cr_f;
                }else if (selectedRoom.equals("Male Comfort Room")) {
                    imageResource = R.drawable.cr_m;
                } else if (selectedRoom.equals("Graduate School Consultation Room")) {
                    imageResource = R.drawable.gs_consult;
                } else if (selectedRoom.equals("Room 301")) {
                    imageResource = R.drawable.sv_toi;
                } else if (selectedRoom.equals("Room 302")) {
                    imageResource = R.drawable.sv_too;
                } else if (selectedRoom.equals("Room 303")) {
                    imageResource = R.drawable.sv_thoth;
                }else if (selectedRoom.equals("Room 304")) {
                    imageResource = R.drawable.sv_thofo;
                } else if (selectedRoom.equals("Room 305")) {
                    imageResource = R.drawable.sv_tofi;
                } else if (selectedRoom.equals("Room 306")) {
                    imageResource = R.drawable.sv_tosx;
                }  else if (selectedRoom.equals("Room 307")) {
                    imageResource = R.drawable.sv_tosv;
                } else if (selectedRoom.equals("Room 308")) {
                    imageResource = R.drawable.sv_toeg;
                } else if (selectedRoom.equals("Room 309")) {
                    imageResource = R.drawable.sv_toni;
                } else if (selectedRoom.equals("Room 310")) {
                    imageResource = R.drawable.sv_totn;
                } else if (selectedRoom.equals("Room 311")) {
                    imageResource = R.drawable.sv_toii;
                } else if (selectedRoom.equals("Room 312")) {
                    imageResource = R.drawable.titw;
                } else if (selectedRoom.equals("Room 313")) {
                    imageResource = R.drawable.sv_thith;
                } else if (selectedRoom.equals("Room 314")) {
                    imageResource = R.drawable.sv_thith;
                } else if (selectedRoom.equals("Room 315")) {
                    imageResource = R.drawable.sv_thifi;
                } else if (selectedRoom.equals("Room 316")) {
                    imageResource = R.drawable.sv_thisx;
                } else if (selectedRoom.equals("Room 317")) {
                    imageResource = R.drawable.sv_thisv;
                } else{
                    imageResource = R.drawable.sv_third;
                }
        }
        selected.setImageResource(imageResource);
    }

    private void populateRoomSpinner(String selectedFloor) {
        String[] rooms;
        switch(selectedFloor){
            case "1st Floor":
                rooms = getResources().getStringArray(R.array.Room1st);
                break;
            case "2nd Floor":
                rooms = getResources().getStringArray(R.array.Room2nd);
                break;
            case "3rd Floor":
                rooms = getResources().getStringArray(R.array.Room3rd);
                break;
            default:
                rooms = new String[0];
                break;
        }
        ArrayAdapter<String> RoomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,rooms);
        RoomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(RoomAdapter);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int clicked = item.getItemId();

        if(clicked == R.id.nav_home){
            Intent intent = new Intent(MAP_SV.this, MainActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_map){
            Intent intent = new Intent(MAP_SV.this, MAP_SV.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_forum){
            Intent intent = new Intent(MAP_SV.this, forum_noAcc.class);
            startActivity(intent);
        } else if (clicked == R.id.nav_login){
            Intent intent = new Intent(MAP_SV.this, SignInActivity.class);
            startActivity(intent);
        }else if(clicked == R.id.nav_logout){
            Intent intent = new Intent(MAP_SV.this, SignInActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid!",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}