package com.example.medrecord;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.medrecord.appointment.AppointmentFragment;
import com.example.medrecord.conditions.ConditionsFragment;
import com.example.medrecord.medicine.MedicineFragment;
import com.example.medrecord.records.RecordsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SecondActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.widBtmNaviView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Appointments");
        loadFragment(new AppointmentFragment());
    }


    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {
            switch (item.getItemId()) {
                case R.id.menu_appointments:
                    toolbar.setTitle("Appointments");
                    loadFragment(new AppointmentFragment());
                    return true;
                case R.id.menu_medicine:
                    toolbar.setTitle("Medicine");
                    loadFragment(new MedicineFragment());
                    return true;
                case R.id.menu_records:
                    toolbar.setTitle("Records");
                    loadFragment(new RecordsFragment());
                    return true;
                case R.id.menu_conditions:
                    toolbar.setTitle("Conditions");
                    loadFragment(new ConditionsFragment());
                    return true;
                case R.id.menu_more:
                    toolbar.setTitle("More");
                    loadFragment(new MoreFragment());
                    return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.loFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

