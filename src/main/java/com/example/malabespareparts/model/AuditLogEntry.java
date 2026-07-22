package com.example.malabespareparts.model;

public class AuditLogEntry {
    private  String date;
    private  String time;
    private  String action;
    private  String user;

    public AuditLogEntry(){

    }

    public  AuditLogEntry(String date, String time, String action, String user){
        this.date=date;
        this.time=time;
        this.action=action;
        this.user=user;
    }

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public String getAction() {return action;}

    public void setAction(String action) {this.action = action;}

    public String getUser() {return user;}

    public void setUser(String user) {this.user = user;}

    @Override
    public String toString() {
        return date + "," + time + "," + action + "," + user;
    }


}
