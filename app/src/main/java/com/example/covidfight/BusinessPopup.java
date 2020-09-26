package com.example.covidfight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BusinessPopup extends AppCompatActivity {

    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_popup);

        Intent intent = getIntent();
        BusinessItem currentItem = intent.getParcelableExtra("BusinessItem");

        int popImage = currentItem.getImageResource();
        String popTitle = currentItem.getTitle();
        String popDescription = currentItem.getInfo();

        ImageView iv = findViewById(R.id.popImage);
        iv.setImageResource(popImage);

        TextView tv1 = findViewById(R.id.popTitle);
        tv1.setText(popTitle);

        TextView tv2 = findViewById(R.id.popDescription);
        tv2.setText(popDescription);

        /** layout */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int)(width*.8), (int)(height*.75));

        /** button to close popup */
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}
