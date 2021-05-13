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

    public long getConditionId() {
        return Conditionid;
    }

    public void setConditionId(long conditionid) {
        this.Conditionid = Conditionid;
    }

    public String getConditionTitle() {
        return Conditiontitle;
    }

    public void setConditionTitle(String conditiontitle) {
        this.Conditiontitle = Conditiontitle;
    }

    public String getConditionContent() {
        return Conditioncontent;
    }

    public void setConditionContent(String conditioncontent) { this.Conditioncontent = Conditioncontent;
    }

    public String getConditionDate() {
        return Conditiondate;
    }

    public void setConditionDate(String conditiondate) {
        this.Conditiondate = Conditiondate;
    }

    public String getConditionTime() {
        return Conditiontime;
    }

    public void setConditionTime(String conditiontime) {
        this.Conditiontime = Conditiontime;
    }
}
