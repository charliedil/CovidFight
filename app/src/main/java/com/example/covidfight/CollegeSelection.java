package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CollegeSelection extends AppCompatActivity implements View.OnClickListener {
    public CardView card1, card2, card3, card4, card5, card6, card7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_selection);

        card1 = (CardView) findViewById(R.id.GMUCard);
        card2 = (CardView) findViewById(R.id.JMUCard);
        card3 = (CardView) findViewById(R.id.URCard);
        card4 = (CardView) findViewById(R.id.UVACard);
        card5 = (CardView) findViewById(R.id.VCUCard);
        card6 = (CardView) findViewById(R.id.VTCard);
        card7 = (CardView) findViewById(R.id.WMCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
    }

    //links each card on Home Page to their respective activities
    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.GMUCard:
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;

            case R.id.JMUCard:
                i = new Intent(this, Statictics.class);
                startActivity(i);
                break;

            case R.id.URCard:
                i = new Intent(this, Business.class);
                startActivity(i);
                break;

           /* case R.id.UVACard :
                i = new Intent(this,);
                startActivity(i);
                break;*/

            case R.id.VCUCard:
                i = new Intent(this, ReportActivity.class);
                startActivity(i);
                break;

            case R.id.VTCard:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;

            case R.id.WMCard:

                i = new Intent(this, AboutUsActivity.class);
                startActivity(i);
                break;


        }
    }
}