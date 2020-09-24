package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import java.util.List;


public class Business extends AppCompatActivity {

    private CardView bCardView;

    //List<String> listBusinesses;
    //private Button btnBackToMain;

    //public CardView card1, card2, card3, card4, card5, card6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        bCardView=findViewById(R.id.bCard1);
        bCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Business.this, BusinessPopup.class);
                startActivity(intent);
            }
        });

//        btnBackToMain = (Button) findViewById(R.id.bCard1);
//
//        btnBackToMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

//        card1 = (CardView) findViewById(R.id.bcard1);
//        card2 = (CardView) findViewById(R.id.bcard2);
//        card3 = (CardView) findViewById(R.id.bcard3);
//        card4 = (CardView) findViewById(R.id.bcard4);
//        card5 = (CardView) findViewById(R.id.bcard5);
//        card6 = (CardView) findViewById(R.id.bcard6);


    }

}
