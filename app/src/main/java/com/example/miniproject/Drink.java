package com.example.miniproject;

public class Drink  {
    private String Dbrand;
    private String Ddate;
    private String Dname;
    private String Dtime;

    public Drink() {
    }

    public Drink(String dbrand, String ddate, String dname, String dtime) {
        Dbrand = dbrand;
        Ddate = ddate;
        Dname = dname;
        Dtime = dtime;
    }

    public String getDbrand() {
        return Dbrand;
    }

    public void setDbrand(String dbrand) {
        Dbrand = dbrand;
    }

    public String getDdate() {
        return Ddate;
    }

    public void setDdate(String ddate) {
        Ddate = ddate;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDtime() {
        return Dtime;
    }

    public void setDtime(String dtime) {
        Dtime = dtime;
    }
}
