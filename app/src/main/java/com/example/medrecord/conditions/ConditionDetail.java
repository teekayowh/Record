package com.example.medrecord.conditions;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.medrecord.R;
import com.example.medrecord.SecondActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConditionDetail extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_detail);
        Toolbar condition_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(condition_toolbar);



        Intent i = getIntent();
        id = i.getLongExtra("ID",0);
        ConditionDatabase db = new ConditionDatabase(this);
        ConditionNote note = db.getCondition(id);
        getSupportActionBar().setTitle(note.getConditionTitle());
        TextView details = findViewById(R.id.noteDesc);
        details.setText(note.getConditionContent());
        details.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConditionDatabase db = new ConditionDatabase(getApplicationContext());
                db.deleteCondition(id);
                Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
                goToMain();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_condition_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            Intent i = new Intent(this,EditCondition.class);
            i.putExtra("ID",id);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void goToMain() {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
}

