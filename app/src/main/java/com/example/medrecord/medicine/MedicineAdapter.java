package com.example.medrecord.medicine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medrecord.R;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<com.example.medrecord.medicine.MedicineAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<MedicineNote> medicineNotes;

    MedicineAdapter(Context context, List<MedicineNote> medicineNotes){
        this.inflater = LayoutInflater.from(context);
        this.medicineNotes = medicineNotes;
    }


    @NonNull
    @Override
    public com.example.medrecord.medicine.MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_medicine_list_view,viewGroup,false);
        return new com.example.medrecord.medicine.MedicineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String  Medicinename   = medicineNotes.get(i).getMedicinename();
        String  Medicinetime     = medicineNotes.get(i).getMedicinetime();
        String  Medicinelocation     = medicineNotes.get(i).getMedicinelocation();
        String  Medicinenotes      = medicineNotes.get(i).getMedicinenotes();
        long    id       = medicineNotes.get(i).getId();
        Log.d("Time at ", "time at: "+Medicinetime);

        viewHolder.nMedicinename.setText(Medicinename);
        viewHolder.nMedicinetime.setText(Medicinetime);
        viewHolder.nMedicinelocation.setText(Medicinelocation);
        viewHolder.nMedicinenotes.setText(Medicinenotes);
        viewHolder.nID.setText(String.valueOf(medicineNotes.get(i).getId()));

        // Delete appt onhold - just to test that deleting works
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MedicineDatabase sDB = new MedicineDatabase(view.getContext());
                sDB.deleteMedicine(id);
                medicineNotes.remove(i);
                notifyItemRemoved(i);

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return medicineNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nMedicinename,nMedicinetime,nMedicinelocation,nMedicinenotes,nID;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nMedicinename  = itemView.findViewById(R.id.tvMedicineName);
            nMedicinelocation   = itemView.findViewById(R.id.tvMedicineLocation);
            nMedicinetime   = itemView.findViewById(R.id.tvMedicineTime);
            nMedicinenotes  = itemView.findViewById(R.id.tvMedicineNotes);
            nID     = itemView.findViewById(R.id.listId);
        }
    }
}
