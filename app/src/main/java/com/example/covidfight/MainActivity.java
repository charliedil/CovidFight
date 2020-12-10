package com.example.covidfight;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3, card4, card5, card6, card7;

    //ensures that the notification pop up only occurs the first the app is launched
    //adds functionality to each card that is on the home page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if(firstStart) {
            showStartDialog();
        }

        card1 = (CardView) findViewById(R.id.mapCard);
        card2 = (CardView) findViewById(R.id.statsCard);
        card3 = (CardView) findViewById(R.id.businessCard);
        card5 = (CardView) findViewById(R.id.reportCard);
        card6 = (CardView) findViewById(R.id.settingsCard);
        card7 = (CardView) findViewById(R.id.aboutCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
    }

    //Displays a pop up screen that asks user whether they want to enable notifications
    private void showStartDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Notifications")
                .setMessage("Would you like like to set a daily reminder to wear your mask?")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Intent start = new Intent(MainActivity.this, SettingsActivity.class);
                       startActivity(start);
                    }
                })

                .setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    //links each card on Home Page to their respective activities
    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.mapCard :
                i = new Intent(this,MapsActivity.class);
                startActivity(i);
                break;

            case R.id.statsCard :
                i = new Intent(this,Statictics.class);
                startActivity(i);
                break;

            case R.id.businessCard :
                i = new Intent(this,Business.class);
                startActivity(i);
                break;

            case R.id.reportCard :
                i = new Intent(this,ReportActivity.class);
                startActivity(i);
                break;

            case R.id.settingsCard :
                i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                break;

            case R.id.aboutCard :

                i = new Intent(this,AboutUsActivity.class);
                startActivity(i);
                break;
        }
    }
}
