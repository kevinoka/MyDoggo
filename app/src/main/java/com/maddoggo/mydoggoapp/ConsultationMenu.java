package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ConsultationMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner consulSpinner = (Spinner) findViewById(R.id.spinnerConsul1);

        ArrayAdapter<String> consulAdapter = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul1));
        consulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner.setAdapter(consulAdapter);


        Spinner consulSpinner2 = (Spinner) findViewById(R.id.spinnerConsul2);

        ArrayAdapter<String> consulAdapter2 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul2));
        consulAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner2.setAdapter(consulAdapter2);


        Spinner consulSpinner3 = (Spinner) findViewById(R.id.spinnerConsul3);

        ArrayAdapter<String> consulAdapter3 = new ArrayAdapter<>(ConsultationMenu.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.consul3));
        consulAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consulSpinner3.setAdapter(consulAdapter3);

        Button btn = (Button)findViewById(R.id.buttonConsul);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ConsultationResult.class);
                startActivity(i);
            }
        });

    }

}
