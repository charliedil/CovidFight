package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Statictics extends AppCompatActivity {

    private ImageView VcuImageView,RichmondImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statictics);

        /** Button to close activity */
        Button closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        VcuImageView=findViewById(R.id.vcuImageView);
        VcuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Statictics.this, VcuStat.class);
                startActivity(intent);
            }
        });

        RichmondImageView=findViewById(R.id.richmondStat);
        RichmondImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Statictics.this,RichmondStat.class);
                startActivity(intent);
            }
        });

    }
}