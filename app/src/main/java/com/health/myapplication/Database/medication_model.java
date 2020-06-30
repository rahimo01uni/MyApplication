package com.health.myapplication.Database;

import java.util.ArrayList;

public class medication_model {
    String Id, Med_name,StartDate,EndDate,NextDate,Frequency,Time,Unit,Dose,Description;
    ArrayList<String> Times;
    public medication_model()
    { }
    public medication_model(String med_name, String startDate, String endDate, String frequency, String unit, String dose, ArrayList<String> times,String Desc) {
        Med_name = med_name;
        StartDate = startDate;
        EndDate = endDate;
        Frequency = frequency;
        Unit = unit;
        Dose = dose;
        Times = times;
        this.Description=Desc;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setMed_name(String med_name) {
        Med_name = med_name;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public void setNextDate(String nextDate) {
        NextDate = nextDate;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public void setDose(String dose) {
        Dose = dose;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setTimes(ArrayList<String> times) {
        Times = times;
    }

    public String getId() {
        return Id;
    }

    public String getMed_name() {
        return Med_name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getNextDate() {
        return NextDate;
    }

    public String getFrequency() {
        return Frequency;
    }

    public String getTime() {
        return Time;
    }

    public String getUnit() {
        return Unit;
    }

    public String getDose() {
        return Dose;
    }

    public ArrayList<String> getTimes() {
        return Times;
    }
}
