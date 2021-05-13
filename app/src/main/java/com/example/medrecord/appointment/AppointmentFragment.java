package com.example.medrecord.appointment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medrecord.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentFragment extends Fragment {

    public AppointmentFragment() {

    }

    private Button mButtonMakeAppointment;
    private Context thiscontext;
    private RecyclerView recyclerView;
    private List<AppointmentNotes> mAppointments;
    private AppointmentAdapter mAppointmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointments, container, false);

        mButtonMakeAppointment = (Button) v.findViewById(R.id.MakeAppointments);
        thiscontext = container.getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.AppointmentList);
        mAppointments = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        mAppointmentAdapter = new AppointmentAdapter(thiscontext, new AppointmentDatabase(thiscontext).getAllNotes());
        recyclerView.setAdapter(mAppointmentAdapter);


        mButtonMakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeAppointment();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        mAppointmentAdapter = new AppointmentAdapter(thiscontext, new AppointmentDatabase(thiscontext).getAllNotes());
        recyclerView.setAdapter(mAppointmentAdapter);
        super.onResume();
    }

    private void openMakeAppointment() {
        Intent intent = new Intent(thiscontext, MakeAppointment.class);
        startActivity(intent);
    }
}

//TODO: Create adapter to use recyclerview
//TODO: Create a "database" to store alarm informaton
//TODO: Find a way to display alarm in recyclerview
//TODO: Once pendingintent/alarm notification pop-up must auto delete alarm info from recyclerview

//TODO: Add Adapter, SimpleDatabase, Note and the part on AddNote which triggers transfer of data from alarmreceiver to simpledatabse, STATUS: DONE
//TODO: For inserting info into recyclerview change id in custom_list_view to nTitle, nTime, etc, part 3 22:31, STATUS: DONE
//TODO: change apptfrag according to recyclerview 32:03
//TODO: CLEAN UP ERRORS IN APPTADAPTER AND DATABASE, STATUS: DONE

//TODO: How do i show my appointments in the recyclerview
//TODO: Change time in makeappointment layout to time of appt and indicate time picker widget is time of notification



