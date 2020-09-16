package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class VcuStat extends AppCompatActivity {

    private TextView activeCaseVCUNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcu_stat);

        activeCaseVCUNum=findViewById(R.id.activeCaseNumber);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 600);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeCaseVCUNum.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();

    }
}