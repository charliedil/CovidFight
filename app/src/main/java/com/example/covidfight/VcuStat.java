package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class VcuStat extends AppCompatActivity {

    private TextView activeStudentCaseNumber,activeEmployeeCaseNumber,isolationNumber,quarantineNumber,negativeEntryResultNumber,positiveEntryResultNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcu_stat);

        activeStudentCaseNumber=findViewById(R.id.ActiveStudentCaseNumber);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 124);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeStudentCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();

        activeEmployeeCaseNumber=findViewById(R.id.ActiveEmployeeCaseNumber);

        ValueAnimator valueAnimator2 = ValueAnimator.ofInt(0, 169);
        valueAnimator2.setDuration(3000);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeEmployeeCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator2.start();

        isolationNumber=findViewById(R.id.IsolationNumber);

        ValueAnimator valueAnimator3 = ValueAnimator.ofInt(0, 124);
        valueAnimator3.setDuration(3000);
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                isolationNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator3.start();

        quarantineNumber=findViewById(R.id.QuarantineNumber);

        ValueAnimator valueAnimator4 = ValueAnimator.ofInt(0, 124);
        valueAnimator3.setDuration(3000);
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                quarantineNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator4.start();

        negativeEntryResultNumber=findViewById(R.id.NegavtiveEntryResultNumber);

        ValueAnimator valueAnimator5 = ValueAnimator.ofInt(0, 124);
        valueAnimator5.setDuration(3000);
        valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeStudentCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator5.start();


        positiveEntryResultNumber=findViewById(R.id.PositiveEntryResultNumber);

        ValueAnimator valueAnimator6 = ValueAnimator.ofInt(0, 124);
        valueAnimator6.setDuration(3000);
        valueAnimator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeStudentCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator6.start();


    }
}