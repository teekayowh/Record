package com.example.medrecord.medicine;

public class MedicineNote {
    private long id;
    private String medicinename;
    private String medicinelocation;
    private String medicinenotes;
    private String medicinetime;

//    AppointmentNotes(int id, String eventname, String eventtime, String eventlocation, String eventnotes){
//        this.id = id;
//        this.eventname = eventname;
//        this.eventlocation = eventlocation;
//        this.eventnotes = eventnotes;
//        this.eventtime = eventtime;
//    }

    MedicineNote(long id, String medicinename, String medicinetime, String medicinelocation, String medicinenotes) {
        this.id = id;
        this.medicinename = medicinename;
        this.medicinelocation = medicinelocation;
        this.medicinenotes = medicinenotes;
        this.medicinetime = medicinetime;
    }

    MedicineNote() {
        // empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicinetime() {
        return medicinetime;
    }

    public void setMedicinetime(String medicinetime) {
        this.medicinetime = medicinetime;
    }

    public String getMedicinelocation() {
        return medicinelocation;
    }

    public void setMedicinelocation(String medicinelocation) { this.medicinelocation = medicinelocation; }

    public String getMedicinenotes() {
        return medicinenotes;
    }

    public void setMedicinenotes(String medicinenotes) {
        this.medicinenotes = medicinenotes;
    }

}
