package com.example.medrecord;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView errmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etUserid);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btLogin);
        errmsg = (TextView) findViewById(R.id.tvErrorMessage);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String username, String userPassword) {
        if ((username.equals("guankeelim")) && (userPassword.equals("Ohtaeki00"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else {
            errmsg.setVisibility(View.VISIBLE);
            Login.setEnabled(true);
        }
    }
}

//TODO: how to add background (logo/coloured box on top of the app) in app?
//TODO: edit fontstyle and fontsize in etUserid and etPassword?
//TODO: how to include different databases? Currently app only allow 1 user
//TODO: include the app loading page (w logo)
//TODO: currently having upload option which will branch out into page with all the records and respective descriptions
//TODO: change into format where recyclerview exists with upload option as a button at btmright corner