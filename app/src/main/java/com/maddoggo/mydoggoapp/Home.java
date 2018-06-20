package com.maddoggo.mydoggoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.signin.SignIn;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CardView adoptionCard,buySellCard,breedingCard,lostFoundCard,consultationCard,doggopediaCard,locationCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Defining Cards
        adoptionCard = (CardView) findViewById(R.id.adoption_card);
        buySellCard = (CardView) findViewById(R.id.buy_sell_card);
        breedingCard = (CardView) findViewById(R.id.breeding_card);
        lostFoundCard = (CardView) findViewById(R.id.lost_found_card);
        consultationCard = (CardView) findViewById(R.id.consultation_card);
        doggopediaCard = (CardView) findViewById(R.id.doggopedia_card);
        locationCard = (CardView) findViewById(R.id.location_card);

        //Add onClick Listener to the cards
        adoptionCard.setOnClickListener(this);
        buySellCard.setOnClickListener(this);
        breedingCard.setOnClickListener(this);
        lostFoundCard.setOnClickListener(this);
        consultationCard.setOnClickListener(this);
        doggopediaCard.setOnClickListener(this);
        locationCard.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.adoption_card:
                i = new Intent(this, AdoptionMenu.class);
                startActivity(i);
                break;
            case R.id.buy_sell_card:
                i = new Intent(this, BuyOrSellMenu.class);
                startActivity(i);
                break;
            case R.id.breeding_card:
                i = new Intent(this, BreedingMenu.class);
                startActivity(i);
                break;
            case R.id.lost_found_card:
                i = new Intent(this, LostFoundMenu.class);
                startActivity(i);
                break;
            case R.id.consultation_card:
                i = new Intent(this, ConsultationMenu.class);
                startActivity(i);
                break;
            case R.id.doggopedia_card:
                i = new Intent(this, DoggopediaMenu.class);
                startActivity(i);
                break;
            case R.id.location_card:
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            //New account function
            i = new Intent(this, MyProfile.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            //Logout function
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Home.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
