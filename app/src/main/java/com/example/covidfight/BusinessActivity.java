package com.example.covidfight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusinessActivity extends Activity {

    private Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        btnBackToMain = (Button) findViewById(R.id.btn_back);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
