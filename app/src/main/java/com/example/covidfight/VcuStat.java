package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private TextView activeStudentCaseNumber,activeEmployeeCaseNumber,isolationNumber,quarantineNumber,negativeEntryResultNumber,positiveEntryResultNumber,negavtivePrevelenceNumber,positivePrevelenceNumber;
    int studentCase, employeeCase,isoNumber,quaNumber,negativeEntryNum,positiveEntryNum,negPrevelenceNum,posPrevelenceNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcu_stat);
        /** Button to close activity */
        Button closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();



    }


   private  void getData(){
       final Gson gson= new Gson();

       String url="https://quinn50.dev/vcucovid/api/v1";


       StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {

               activeStudentCaseNumber=findViewById(R.id.ActiveStudentCaseNumber);
               activeEmployeeCaseNumber=findViewById(R.id.ActiveEmployeeCaseNumber);
               isolationNumber=findViewById(R.id.IsolationNumber);
               quarantineNumber=findViewById(R.id.QuarantineNumber);
               negativeEntryResultNumber=findViewById(R.id.NegavtiveEntryResultNumber);
               positiveEntryResultNumber=findViewById(R.id.PositiveEntryResultNumber);
               positivePrevelenceNumber=findViewById(R.id.positiveTestNumbers);
               negavtivePrevelenceNumber=findViewById(R.id.negativeTestNumbers);

               VcuCase vcuCase=gson.fromJson(response,VcuCase.class);
               //get data from database and set to EditView
               setDataVcu(vcuCase);
               //Make animated numbers
               animateNumberVcu();


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

    private void animateNumberVcu() {
        ValueAnimator valueAnimator = getValueAnimator(studentCase, activeStudentCaseNumber);
        valueAnimator.start();

        ValueAnimator valueAnimator2 = getValueAnimator(employeeCase, activeEmployeeCaseNumber);
        valueAnimator2.start();

        ValueAnimator valueAnimator3 = getValueAnimator(isoNumber, isolationNumber);
        valueAnimator3.start();

        ValueAnimator valueAnimator4 = getValueAnimator(quaNumber, quarantineNumber);
        valueAnimator4.start();

        ValueAnimator valueAnimator5 = getValueAnimator(negativeEntryNum, negativeEntryResultNumber);
        valueAnimator5.start();

        ValueAnimator valueAnimator6 = getValueAnimator(positiveEntryNum, positiveEntryResultNumber);
        valueAnimator6.start();

        ValueAnimator valueAnimator7 = getValueAnimator(posPrevelenceNum, positivePrevelenceNumber);
        valueAnimator7.start();

        ValueAnimator valueAnimator8 = getValueAnimator(negPrevelenceNum, negavtivePrevelenceNumber);
        valueAnimator8.start();
    }

    private void setDataVcu(VcuCase vcuCase) {
        int studentSize =vcuCase.getStudents().size();
        Data dataStudent=vcuCase.getStudents().get(studentSize-1);
        String x1=Integer.toString(dataStudent.getValue());
        activeStudentCaseNumber.setText(x1);
        studentCase=(int) dataStudent.getValue();

        int employeeSize=vcuCase.getEmployees().size();
        Data dataEmployee=vcuCase.getEmployees().get(employeeSize-1);
        String x2=Integer.toString(dataEmployee.getValue());
        activeStudentCaseNumber.setText(x2);
        employeeCase=(int) dataEmployee.getValue();

        int isoSize=vcuCase.getIsolations().size();
        Data dataIsolation=vcuCase.getIsolations().get(isoSize-1);
        String x3=Integer.toString(dataIsolation.getValue());
        isolationNumber.setText(x3);
        isoNumber=(int) dataIsolation.getValue();

        int quaSize=vcuCase.getQuarantines().size();
        Data dataQuarantine=vcuCase.getQuarantines().get(quaSize-1);
        String x4=Integer.toString(dataIsolation.getValue());
        quarantineNumber.setText(x4);
        quaNumber=(int) dataQuarantine.getValue();

        int negEntrySize=vcuCase.getNegatives().size();
        Data dataNegEntry=vcuCase.getNegatives().get(negEntrySize-1);
        String x5=Integer.toString(dataNegEntry.getValue());
        negativeEntryResultNumber.setText(x4);
        negativeEntryNum=(int) dataNegEntry.getValue();

        int posEntrySize=vcuCase.getPositives().size();
        Data dataPosEntry=vcuCase.getPositives().get(posEntrySize-1);
        String x6=Integer.toString(dataPosEntry.getValue());
        positiveEntryResultNumber.setText(x6);
        positiveEntryNum=(int) dataPosEntry.getValue();

        int posPreveSize=vcuCase.getPrevalencePositive().size();
        Data dataPosPreve=vcuCase.getPrevalencePositive().get(posPreveSize-1);
        String x7=Integer.toString(dataPosPreve.getValue());
        positivePrevelenceNumber.setText(x7);
        posPrevelenceNum=(int) dataPosPreve.getValue();

        int negPreveSize=vcuCase.getPrevalenceNegative().size();
        Data dataNegPreve=vcuCase.getPrevalenceNegative().get(negPreveSize-1);
        String x8=Integer.toString(dataNegPreve.getValue());
        negavtivePrevelenceNumber.setText(x8);
        negPrevelenceNum=(int) dataNegPreve.getValue();
    }

    private ValueAnimator getValueAnimator(int studentCase, final TextView activeStudentCaseNumber) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, studentCase);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeStudentCaseNumber.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        return valueAnimator;
    }


}