package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BuyOrSellMenu extends AppCompatActivity implements View.OnClickListener {

    private CardView buySellCard1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_sell_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SellPosting.class);
                startActivity(i);
            }
        });

        buySellCard1 = (CardView) findViewById(R.id.buy_sell_card1);

        buySellCard1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), BuyPage.class);
        startActivity(i);
    }
}
