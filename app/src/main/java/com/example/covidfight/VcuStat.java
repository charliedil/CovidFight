package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

public class VcuStat extends AppCompatActivity {

    private TextView activeStudentCaseNumber,activeEmployeeCaseNumber,isolationNumber,quarantineNumber,negativeEntryResultNumber,positiveEntryResultNumber;
    int studentCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcu_stat);

        activeStudentCaseNumber=findViewById(R.id.ActiveStudentCaseNumber);
        getData();




//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 5);
//        valueAnimator.setDuration(3000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                activeStudentCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
//            }
//        });
//        valueAnimator.start();

        activeEmployeeCaseNumber=findViewById(R.id.ActiveEmployeeCaseNumber);

        ValueAnimator valueAnimator2 = ValueAnimator.ofInt(0, studentCase);
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
        valueAnimator4.setDuration(3000);
        valueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
                negativeEntryResultNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator5.start();


        positiveEntryResultNumber=findViewById(R.id.PositiveEntryResultNumber);

        ValueAnimator valueAnimator6 = ValueAnimator.ofInt(0, 124);
        valueAnimator6.setDuration(3000);
        valueAnimator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                positiveEntryResultNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator6.start();


    }


   private  void getData(){
       final Gson gson= new Gson();

       String url="https://quinn50.dev/vcucovid/api/v1";


       StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {

               VcuCase vcuCase=gson.fromJson(response,VcuCase.class);
               int n=vcuCase.getStudents().size();
               Data data=vcuCase.getStudents().get(n-1);
               String x=Integer.toString(data.getValue());
               activeStudentCaseNumber.setText(x);
               studentCase=data.getValue();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
           }
       });
       RequestQueue queue= Volley.newRequestQueue(this);
       queue.add(request);
       queue.start();


   }



}