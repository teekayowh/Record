package com.example.medrecord.medicine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecord.R;

import java.util.Calendar;

public class MakeMedicine extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_medicine);

        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText medicinename = findViewById(R.id.etMedicineName);
        EditText medicinetime = findViewById(R.id.etMedicineTime);
        EditText medicinelocation = findViewById(R.id.etMedicineLocation);
        EditText medicinenotes = findViewById(R.id.etMedicineNotes);
        long uniqueId = (new MedicineDatabase(getApplicationContext())).getNewId();
        TimePicker medicinetimePicker = findViewById(R.id.medicinetimePicker);

        // Intent
        Intent intent = new Intent(MakeMedicine.this, MedicineAlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("id", uniqueId);
        intent.putExtra("message", medicinename.getText().toString());
        intent.putExtra("time", medicinetime.getText().toString());
        intent.putExtra("location", medicinelocation.getText().toString());
        intent.putExtra("notes", medicinenotes.getText().toString());

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MakeMedicine.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = medicinetimePicker.getCurrentHour();
                int minute = medicinetimePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

                MedicineNote Medicinenote = new MedicineNote(uniqueId, medicinename.getText().toString(),medicinetime.getText().toString(),
                        medicinelocation.getText().toString(), medicinenotes.getText().toString());
                MedicineDatabase sDB = new MedicineDatabase(this);
                sDB.addMedicine(Medicinenote);
                Toast.makeText(this, "Medicine Saved.", Toast.LENGTH_SHORT).show();

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

