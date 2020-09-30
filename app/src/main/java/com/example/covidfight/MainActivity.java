package com.example.covidfight;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3, card4, card5, card6;


    //private Button btnLaunchBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        card1 = (CardView) findViewById(R.id.mapCard);
        card2 = (CardView) findViewById(R.id.statsCard);
        card3 = (CardView) findViewById(R.id.businessCard);
        card4 = (CardView) findViewById(R.id.infoCard);
        card5 = (CardView) findViewById(R.id.reportCard);
        card6 = (CardView) findViewById(R.id.settingsCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
    }

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

//            case R.id.infoCard :
//                i = new Intent(this,);
//                startActivity(i);
//                break;

            case R.id.reportCard :
                i = new Intent(this,ReportActivity.class);
                startActivity(i);
                break;

            case R.id.settingsCard :
                i = new Intent(this,ReportActivity.class);
                startActivity(i);
                break;
        }


    }

  /*  private void launchBusiness() {
        Intent intent = new Intent(this, BusinessActivity.class);
        startActivity(intent);
    }*/


}
