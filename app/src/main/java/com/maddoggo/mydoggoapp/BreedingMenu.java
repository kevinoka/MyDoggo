package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class BreedingMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner breedingSpinner1 = (Spinner) findViewById(R.id.spinnerBreeding1);

        ArrayAdapter<String> breedingAdapter1 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul1));
        breedingAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner1.setAdapter(breedingAdapter1);


        Spinner breedingSpinner2 = (Spinner) findViewById(R.id.spinnerBreeding2);

        ArrayAdapter<String> breedingAdapter2 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul2));
        breedingAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner2.setAdapter(breedingAdapter2);


        Spinner breedingSpinner3 = (Spinner) findViewById(R.id.spinnerBreeding3);

        ArrayAdapter<String> breedingAdapter3 = new ArrayAdapter<>(BreedingMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.breeding3));
        breedingAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedingSpinner3.setAdapter(breedingAdapter3);


    Button btn = (Button)findViewById(R.id.buttonBreeding);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), BreedingResult.class);
            startActivity(i);
        }
    });
}}
