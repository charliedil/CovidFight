package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportActivity extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }
    Button submit = (Button) findViewById(R.id.simpleButton);

    //Work in progress
    /*final EditText your_name = (EditText) findViewById(R.id.enter_name);
    final EditText your_address = (EditText) findViewById(R.id.enter_address);
    final EditText your_description = (EditText) findViewById(R.id.enter_description);


    Button submit = (Button) findViewById(R.id.post_message);

    submit.setOnClickListener(new View.OnClickListener(){
        @Override

        public void onClick(View v){
            String name = your_name.getText().toString();
            String email = your_email.getText().toString();
            String subject = your_subject.getText().toString();
            String message = your_message.getText().toString();

            if (TextUtils.isEmpty(name)) {
                your_name.setError("Enter Your Name");
                your_name.requestFocus();
                return;
            }
            Boolean onError = false;
            if(TextUtils.isEmpty((subject))){
                your_address.setError("Enter the Address");
                your_address.requestFocus();
                return;
            }
            if(TextUtils.isEmpty((message))){
                your_description.setError("Enter a Description");
                your_description.requestFocus();
                return;
            }

            Intent sendEmail = new Intent(andri)
        }*/
}