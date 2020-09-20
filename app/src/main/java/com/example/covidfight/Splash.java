package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       /* RelativeLayout rl = findViewById(R.id.relativeLayout);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.logo);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500,400);
        params.leftMargin = 100;
        params.topMargin = 300;
        rl.addView(iv,params);*/
    }
}