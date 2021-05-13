package com.example.medrecord.appointment;

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

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<AppointmentNotes> notes;

    AppointmentAdapter(Context context, List<AppointmentNotes> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String  Eventname    = notes.get(i).getEventname();
        String  Eventtime     = notes.get(i).getEventtime();
        String  Eventlocation     = notes.get(i).getEventlocation();
        String  Eventnotes      = notes.get(i).getEventnotes();
        long    id       = notes.get(i).getId();
        Log.d("Time at ", "time at: "+Eventtime);

        viewHolder.nEventName.setText(Eventname);
        viewHolder.nEventtime.setText(Eventtime);
        viewHolder.nEventlocation.setText(Eventlocation);
        viewHolder.nEventnotes.setText(Eventnotes);
        viewHolder.nID.setText(String.valueOf(notes.get(i).getId()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nEventName,nEventtime,nEventlocation,nEventnotes,nID;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nEventName  = itemView.findViewById(R.id.tvEventName);
            nEventlocation   = itemView.findViewById(R.id.tvEventLocation);
            nEventtime   = itemView.findViewById(R.id.tvEventTime);
            nEventnotes   = itemView.findViewById(R.id.tvEventNotes);
            nID     = itemView.findViewById(R.id.listId);
        }
    }
}
