package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextView;
    public static final String SHARED_PREFS = "shared";
    public static final String TEXT = "text";

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /** Button to close activity */
        Button closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTextView = findViewById(R.id.textView);

        Button buttonTimePicker = findViewById(R.id.timepicker_button);
        buttonTimePicker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        Button buttonCancelAlarm = findViewById(R.id.cancel_button);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cancelAlarm();
            }
        });
        loadData();
        updateViews();
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //createNotificationChannel();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        saveData();
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Reminder Set For: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(timeText);
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, mTextView.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        mTextView.setText(text);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), Reminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1, intent,0);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void cancelAlarm(){

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Intent intent = new Intent(getApplicationContext(), Reminder.class);
        Intent intent = new Intent(this, Reminder.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1, intent, 0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("No Reminder Set");
        saveData();
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
}