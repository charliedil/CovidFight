package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.firebase.auth.ActionCodeSettings;

import java.util.Arrays;

public class Splash extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static int SPLASH_SCREEN = 5000;

    Animation topAnim, bottomAnim;
    ImageView appLogo, appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        appLogo = findViewById(R.id.logo);
        appName = findViewById(R.id.name);

        appLogo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }
}