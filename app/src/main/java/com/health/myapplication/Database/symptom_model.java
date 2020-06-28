package com.health.myapplication.Database;

import java.util.ArrayList;
import java.util.List;

public class symptom_model {
    String mood,note,date,time,status;
    ArrayList<String> Symptoms;

    public symptom_model(String mood, String note, String date, String time, String status, ArrayList<String> symptoms) {
        this.mood = mood;
        this.note = note;
        this.date = date;
        this.time = time;
        this.status = status;
        Symptoms = symptoms;
    }

    public String getMood() {
        return mood;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getSymptoms() {
        return Symptoms;
    }
}

