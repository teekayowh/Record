package com.example.medrecord.conditions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.medrecord.R;

import java.util.List;

public class ConditionsFragment extends Fragment {


    public ConditionsFragment() {

    }

    Toolbar toolbar;
    RecyclerView recyclerView;
    ConditionAdapter conditionAdapter;
    TextView noItemText;
    ConditionDatabase conditionsDatabase;
    Context thiscontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conditions, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        thiscontext = container.getContext();
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        noItemText = (TextView) v.findViewById(R.id.noItemText);
        conditionsDatabase = new ConditionDatabase(thiscontext);
        List<ConditionNote> allCondition = conditionsDatabase.getAllCondition();
        recyclerView = (RecyclerView) v.findViewById(R.id.allConditionList);

        setHasOptionsMenu(true);

        if(allCondition.isEmpty()){
            noItemText.setVisibility(View.VISIBLE);
        }else {
            noItemText.setVisibility(View.GONE);
            displayList(allCondition);
        }

        return v;
    }


    private void displayList(List<ConditionNote> allNotes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        conditionAdapter = new ConditionAdapter(thiscontext,allNotes);
        recyclerView.setAdapter(conditionAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_condition_menu,menu);
    }

    //changed boolean to void

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            Toast.makeText(thiscontext, "Add New Note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), AddCondition.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ConditionNote> getAllCondition = conditionsDatabase.getAllCondition();
        if(getAllCondition.isEmpty()){
            noItemText.setVisibility(View.VISIBLE);
        }else {
            noItemText.setVisibility(View.GONE);
            displayList(getAllCondition);
        }
    }
}
