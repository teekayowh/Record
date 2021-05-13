package com.example.medrecord.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medrecord.R;
import com.example.medrecord.conditions.ConditionDatabase;

import java.util.Calendar;

public class MakeAppointment extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);

        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText eventname = findViewById(R.id.etEventName);
        EditText eventtime = findViewById(R.id.etEventTime);
        EditText eventlocation = findViewById(R.id.etEventLocation);
        EditText eventnotes = findViewById(R.id.etEventNotes);
        long uniqueId = (new AppointmentDatabase(getApplicationContext())).getNewId();
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Intent
        Intent intent = new Intent(MakeAppointment.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("id", uniqueId);
        intent.putExtra("message", eventname.getText().toString());
        intent.putExtra("time", eventtime.getText().toString());
        intent.putExtra("location", eventlocation.getText().toString());
        intent.putExtra("notes", eventnotes.getText().toString());

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MakeAppointment.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

                AppointmentNotes note = new AppointmentNotes(uniqueId, eventname.getText().toString(),eventtime.getText().toString(),
                        eventlocation.getText().toString(), eventnotes.getText().toString());
                AppointmentDatabase sDB = new AppointmentDatabase(this);
                sDB.addNote(note);
                Toast.makeText(this, "Note Saved.", Toast.LENGTH_SHORT).show();

                //nav back to main appointment fragment
                onBackPressed();
//                startActivity(new Intent(getApplicationContext(), AppointmentFragment.class));

                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
