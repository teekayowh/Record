package com.example.medrecord.conditions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medrecord.R;

import java.util.List;

public class ConditionAdapter extends RecyclerView.Adapter<ConditionAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ConditionNote> conditionNotes;

    ConditionAdapter(Context context, List<ConditionNote> conditionnotes){
        this.inflater = LayoutInflater.from(context);
        this.conditionNotes = conditionNotes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String  title    = conditionNotes.get(i).getTitle();
        String  date     = conditionNotes.get(i).getDate();
        String  time     = conditionNotes.get(i).getTime();
        long    id       = conditionNotes.get(i).getId();
        Log.d("date on ", "Date on: "+date);

        viewHolder.nTitle.setText(title);
        viewHolder.nDate.setText(date);
        viewHolder.nTime.setText(time);
        viewHolder.nID.setText(String.valueOf(conditionNotes.get(i).getId()));

    }

    @Override
    public int getItemCount() {
        return conditionNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nTitle,nDate,nTime,nID;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nTitle  = itemView.findViewById(R.id.nTitle);
            nDate   = itemView.findViewById(R.id.nDate);
            nTime   = itemView.findViewById(R.id.nTime);
            nID     = itemView.findViewById(R.id.listId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),ConditionDetail.class);
                    i.putExtra("ID",conditionNotes.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
