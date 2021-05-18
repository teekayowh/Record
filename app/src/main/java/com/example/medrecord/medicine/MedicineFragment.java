package com.example.medrecord.medicine;

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

public class MedicineFragment extends Fragment {

    public MedicineFragment() {

    }

    private Button mButtonMakeMedicine;
    private Context thiscontext;
    private RecyclerView recyclerView;
    private List<MedicineNote> mMedicine;
    private MedicineAdapter mMedicineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_medicine, container, false);

        mButtonMakeMedicine = (Button) v.findViewById(R.id.MakeMedicine);
        thiscontext = container.getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.MedicineList);
        mMedicine = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        mMedicineAdapter = new MedicineAdapter(thiscontext, new MedicineDatabase(thiscontext).getAllMedicine());
        recyclerView.setAdapter(mMedicineAdapter);


        mButtonMakeMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMakeMedicine();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        mMedicineAdapter = new MedicineAdapter(thiscontext, new MedicineDatabase(thiscontext).getAllMedicine());
        recyclerView.setAdapter(mMedicineAdapter);
        super.onResume();
    }

    private void openMakeMedicine() {
        Intent intent = new Intent(thiscontext, MakeMedicine.class);
        startActivity(intent);
    }
}