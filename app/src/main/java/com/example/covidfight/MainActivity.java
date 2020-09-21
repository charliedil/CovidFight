package com.example.covidfight;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3, card4;



    private Button btnLaunchBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        card1 = (CardView) findViewById(R.id.mapCard);
        card2 = (CardView) findViewById(R.id.statsCard);
        card3 = (CardView) findViewById(R.id.businessCard);
        card4 = (CardView) findViewById(R.id.infoCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.mapCard :
                i = new Intent(this,MapsActivity.class);
                startActivity(i);
                break;

            /*case R.id.statsCard :
                i = new Intent(this,Statistics.class);
                startActivity(i);
                break;

            case R.id.businessCard :
                i = new Intent(this,BusinessActivity.class);
                startActivity(i);
                break;

            case R.id.infoCard :
                i = new Intent(this,);
                startActivity(i);
                break;*/
        }


    }

    private void launchBusiness() {
        Intent intent = new Intent(this, BusinessActivity.class);
        startActivity(intent);
    }

}
