package com.example.medrecord.conditions;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecord.R;

import java.util.Calendar;

public class EditCondition extends AppCompatActivity {
    Toolbar toolbar;
    EditText nTitle,nContent;
    Calendar c;
    String todaysDate;
    String currentTime;
    long nId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_condition);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent i = getIntent();
        nId = i.getLongExtra("ID",0);
        ConditionDatabase db = new ConditionDatabase(this);
        ConditionNote note = db.getCondition(nId);

        final String title = note.getConditionTitle();
        String content = note.getConditionContent();
        nTitle = findViewById(R.id.noteTitle);
        nContent = findViewById(R.id.noteDetails);
        nTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        nTitle.setText(title);
        nContent.setText(content);

        // set current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);
    }


    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_condition_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            ConditionNote note = new ConditionNote(nId,nTitle.getText().toString(),nContent.getText().toString(),todaysDate,currentTime);
            Log.d("EDITED", "edited: before saving id -> " + note.getConditionId());
            ConditionDatabase sDB = new ConditionDatabase(getApplicationContext());
            long id = sDB.editCondition(note);
            Log.d("EDITED", "EDIT: id " + id);
            goToMain();
            Toast.makeText(this, "Note Edited.", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, ConditionsFragment.class);
        startActivity(i);
    }


}
