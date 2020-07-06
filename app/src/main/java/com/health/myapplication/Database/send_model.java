package com.health.myapplication.Database;

import java.util.ArrayList;

public class send_model {
    String name;
    ArrayList<medication_model> med;
    ArrayList<sleep_model> sleep;
    ArrayList<symptom_model> symptom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<medication_model> getMed() {
        return med;
    }

    public void setMed(ArrayList<medication_model> med) {
        this.med = med;
    }

    public ArrayList<sleep_model> getSleep() {
        return sleep;
    }

    public void setSleep(ArrayList<sleep_model> sleep) {
        this.sleep = sleep;
    }

    public ArrayList<symptom_model> getSymptom() {
        return symptom;
    }

    public void setSymptom(ArrayList<symptom_model> symptom) {
        this.symptom = symptom;
    }
}
