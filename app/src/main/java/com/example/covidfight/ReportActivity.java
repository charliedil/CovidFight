package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.Authenticator;
import java.util.Properties;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextAddress;
    private EditText editTextMessage;
    private Button submitButton;
    String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        editTextEmail = (EditText) findViewById(R.id.enter_email);
        editTextAddress = (EditText) findViewById(R.id.enter_address);
        editTextMessage = (EditText) findViewById(R.id.enter_description);
        submitButton = (Button) findViewById(R.id.submitButton);

        sEmail = "covidfightapp@gmail.com";
        sPassword = "TeamK#1!";

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Sessionsession = Session.getInstance(properties, new Authenticator(){

                })
            }
        });
    }

    private void sendEmail(){
        String email = editTextEmail.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String subject = "Reported COVID Violation";
        String message = editTextMessage.getText().toString().trim();
        SendMail sm = new SendMail(this, email, subject, address, message);
        sm.execute();
    }

    @Override
    public void onClick(View v){
        sendEmail();
    }


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