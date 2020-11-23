package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReportActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextAddress;
    private EditText editTextMessage;
    private Button submitButton;
    String sEmail, sPassword, subject, finalMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        /** Button to close activity */
        Button closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        editTextEmail = (EditText) findViewById(R.id.enter_email);
        editTextAddress = (EditText) findViewById(R.id.enter_address);
        editTextMessage = (EditText) findViewById(R.id.enter_description);
        submitButton = (Button) findViewById(R.id.submitButton);

        sEmail = "covidfightapp@gmail.com";
        sPassword = "TeamK#1!";
        subject = "Reported COVID Violation";

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(sEmail));
                    message.setSubject(subject);
                    finalMessage = "Contact Email: " + editTextEmail.getText().toString().trim() + "\nAddress: " + editTextAddress.getText().toString().trim() +
                            "\nMessage: \n" + editTextMessage.getText().toString().trim();
                    message.setText(finalMessage);

                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class SendMail extends AsyncTask<Message, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ReportActivity.this,"Please Wait", "Sending Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e){
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")){
                AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color= '#509324'>Success</font"));
                builder.setMessage("Report sent successfully.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        editTextEmail.setText("");
                        editTextAddress.setText("");
                        editTextMessage.setText("");
                    }
                });

                builder.show();
            } else{
                Toast.makeText(getApplicationContext()
                        , "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
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