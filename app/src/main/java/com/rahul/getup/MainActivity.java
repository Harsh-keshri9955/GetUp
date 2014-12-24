package com.rahul.getup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This sample demonstrates how to schedule an alarm that causes a service to
 * be started. This is useful when you want to schedule alarms that initiate
 * long-running operations, such as retrieving a daily forecast.
 * This particular sample retrieves content from the Google home page once a day and
 * checks it for the search string "doodle". If it finds this string, that indicates
 * that the page contains a custom doodle instead of the standard Google logo.
 */
public class MainActivity extends Activity {
    private static final int MIN_INTERVAL = 10;
    private static final int MAX_INTERVAL = 90;

    AlarmReceiver alarm = new AlarmReceiver();

    private Button buttonStart, buttonStop;
    private TextView tvDiffTime;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonStop = (Button) findViewById(R.id.button_stop);
        tvDiffTime = (TextView) findViewById(R.id.textView_diff);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);

        if(PreferenceManager.getEnabledStatus(this)) buttonStart.setText("Update Alarm");
        tvDiffTime.setText(" " + PreferenceManager.getDiffTime(this) + " minutes");

        numberPicker.setMinValue(MIN_INTERVAL);
        numberPicker.setMaxValue(MAX_INTERVAL);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAlarm();

                Toast.makeText(getBaseContext(), "Alarm set", Toast.LENGTH_LONG).show();
                buttonStart.setText("Update Alarm");
                tvDiffTime.setText(" " + PreferenceManager.getDiffTime(getBaseContext()) + " minutes");
                PreferenceManager.setEnabledStatus(getBaseContext(), true);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.cancelAlarm(getBaseContext());
                Toast.makeText(getBaseContext(), "Alarm cancelled", Toast.LENGTH_LONG).show();

                buttonStart.setText("Start Alarm");
                PreferenceManager.setEnabledStatus(getBaseContext(), false);
            }
        });
    }

    private void updateAlarm() {
        int interval = numberPicker.getValue();
        alarm.cancelAlarm(getBaseContext());
        alarm.setAlarm(getBaseContext(), interval);

        PreferenceManager.setDiffTime(this, interval);
    }
}
