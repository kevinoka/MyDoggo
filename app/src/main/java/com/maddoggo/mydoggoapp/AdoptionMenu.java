package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import com.wenchao.cardstack.CardStack;

public class AdoptionMenu extends AppCompatActivity implements CardStack.CardEventListener {

    private CardStack card_stack;
    private CardAdapter card_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FavoriteAdoption.class);
                startActivity(i);
            }
        });

        initImages();

        card_stack = (CardStack)findViewById(R.id.card_stack);
        card_stack.setContentResource(R.layout.card_layout);
        card_stack.setStackMargin(20);
        card_stack.setAdapter(card_adapter);

        card_stack.setListener(this);

    }

    private void initImages() {
        card_adapter = new CardAdapter(getApplicationContext(),0);
        card_adapter.add(R.drawable.dog1);
        card_adapter.add(R.drawable.dog2);
        card_adapter.add(R.drawable.dog3);
        card_adapter.add(R.drawable.dog4);
    }

    @Override
    public boolean swipeEnd(int i, float v) {
        return v > 300;
    }

    @Override
    public boolean swipeStart(int i, float v) {
        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public void discarded(int i, int i1) {

    }

    @Override
    public void topCardTapped() {

    }
}
