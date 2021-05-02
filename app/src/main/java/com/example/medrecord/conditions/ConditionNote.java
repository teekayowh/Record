package com.example.medrecord.conditions;

public class ConditionNote {
    private long Conditionid;
    private String Conditiontitle;
    private String Conditioncontent;
    private String Conditiondate;
    private String Conditiontime;

    ConditionNote(String Conditiontitle,String Conditioncontent,String Conditiondate, String Conditiontime){
        this.Conditiontitle = Conditiontitle;
        this.Conditioncontent = Conditioncontent;
        this.Conditiondate = Conditiondate;
        this.Conditiontime = Conditiontime;
    }

    ConditionNote(long Conditionid,String Conditiontitle,String Conditioncontent,String Conditiondate, String Conditiontime){
        this.Conditionid = Conditionid;
        this.Conditiontitle = Conditiontitle;
        this.Conditioncontent = Conditioncontent;
        this.Conditiondate = Conditiondate;
        this.Conditiontime = Conditiontime;
    }
    ConditionNote(){
        // empty constructor
    }

    public long getId() {
        return Conditionid;
    }

    public void setId(long conditionid) {
        this.Conditionid = Conditionid;
    }

    public String getTitle() {
        return Conditiontitle;
    }

    public void setTitle(String conditiontitle) {
        this.Conditiontitle = Conditiontitle;
    }

    public String getContent() {
        return Conditioncontent;
    }

    public void setContent(String conditioncontent) {
        this.Conditioncontent = Conditioncontent;
    }

    public String getDate() {
        return Conditiondate;
    }

    public void setDate(String conditiondate) {
        this.Conditiondate = Conditiondate;
    }

    public String getTime() {
        return Conditiontime;
    }

    public void setTime(String conditiontime) {
        this.Conditiontime = Conditiontime;
    }
}
