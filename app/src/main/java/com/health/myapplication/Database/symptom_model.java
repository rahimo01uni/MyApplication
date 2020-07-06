package com.health.myapplication.Database;

import java.util.ArrayList;
import java.util.List;

public class symptom_model {
    String id,mood,note,date,time,status,symptom;
    ArrayList<String> Symptoms;
    ArrayList<String> times;

    public symptom_model(){}
    public symptom_model(String mood, String note, String date, String time, String status, ArrayList<String> symptoms) {
        this.mood = mood;
        this.note = note;
        this.date = date;
        this.time = time;
        this.status = status;
        Symptoms = symptoms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public ArrayList<String> getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        Symptoms = symptoms;
    }

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }
}

