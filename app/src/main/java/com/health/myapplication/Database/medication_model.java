package com.health.myapplication.Database;

import java.util.ArrayList;

public class medication_model {
    String Id, Med_name,StartDate,EndDate,NextDate,Frequency,Time,Unit,Dose;
    ArrayList<String> Times;

    public medication_model(String med_name, String startDate, String endDate, String frequency, String unit, String dose, ArrayList<String> times) {
        Med_name = med_name;
        StartDate = startDate;
        EndDate = endDate;
        Frequency = frequency;
        Unit = unit;
        Dose = dose;
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
