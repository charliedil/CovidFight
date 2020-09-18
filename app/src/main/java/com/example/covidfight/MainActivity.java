package com.example.covidfight;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLaunchBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLaunchBusiness = (Button) findViewById(R.id.btn_business);
        btnLaunchBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBusiness();
            }
        });
    }

    private void launchBusiness() {
        Intent intent = new Intent(this, BusinessActivity.class);
        startActivity(intent);
    }

}
