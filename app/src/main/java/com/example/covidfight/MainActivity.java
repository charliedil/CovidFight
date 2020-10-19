package com.example.covidfight;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3, card4, card5, card6;

    //ensures that the notification pop up only occurs the first the app is launched
    //adds functionality to each card that is on the home page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if(firstStart) {
            showStartDialog();
        }

        card1 = (CardView) findViewById(R.id.mapCard);
        card2 = (CardView) findViewById(R.id.statsCard);
        card3 = (CardView) findViewById(R.id.businessCard);
        //card4 = (CardView) findViewById(R.id.infoCard);
        card5 = (CardView) findViewById(R.id.reportCard);
        card6 = (CardView) findViewById(R.id.settingsCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        //card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
    }

    //Displays a pop up screen that asks user whether they want to enable notifications
    private void showStartDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Notifications")
                .setMessage("Would you like like to enable notifications?")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createNotificationChannel();
                        Calendar calendar = Calendar.getInstance();

                        calendar.set(Calendar.HOUR_OF_DAY, 8);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        Intent intent = new Intent(getApplicationContext(), Reminder.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    }
                })

                .setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    //creates the notification channel that reminds users to wear their mask
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "MaskReminderChannel";
            String description = "Channel to remind people to wear their masks";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("maskNotify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //links each card on Home Page to their respective activities
    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.mapCard :
                i = new Intent(this,MapsActivity.class);
                startActivity(i);
                break;

            case R.id.statsCard :
                i = new Intent(this,Statictics.class);
                startActivity(i);
                break;

            case R.id.businessCard :
                i = new Intent(this,Business.class);
                startActivity(i);
                break;

//            case R.id.infoCard :
//                i = new Intent(this,);
//                startActivity(i);
//                break;

            case R.id.reportCard :
                i = new Intent(this,ReportActivity.class);
                startActivity(i);
                break;

           /* case R.id.settingsCard :
                i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                break;*/
        }


    }

  /*  private void launchBusiness() {
        Intent intent = new Intent(this, BusinessActivity.class);
        startActivity(intent);
    }*/


}
