package com.example.medrecord.appointment;

public class AppointmentNotes {
    private long id;
    private String eventname;
    private String eventlocation;
    private String eventnotes;
    private String eventtime;

//    AppointmentNotes(int id, String eventname, String eventtime, String eventlocation, String eventnotes){
//        this.id = id;
//        this.eventname = eventname;
//        this.eventlocation = eventlocation;
//        this.eventnotes = eventnotes;
//        this.eventtime = eventtime;
//    }

    AppointmentNotes(long id, String eventname, String eventtime, String eventlocation, String eventnotes){
        this.id = id;
        this.eventname = eventname;
        this.eventlocation = eventlocation;
        this.eventnotes = eventnotes;
        this.eventtime = eventtime;
    }
    AppointmentNotes(){
        // empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) { this.eventlocation = eventlocation; }

    public String getEventnotes() {
        return eventnotes;
    }

    public void setEventnotes(String eventnotes) {
        this.eventnotes = eventnotes;
    }
}