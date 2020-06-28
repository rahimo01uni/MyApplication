package com.health.myapplication.Database;

public class general_model {
    sleep_model sleep_log;
    symptom_model symptom_log;
    medication_model medication_log;
    String type;
    String time;
    public general_model(String type,String time,sleep_model sleep_log) {
        this.sleep_log = sleep_log;
        this.type=type;
    }

    public general_model(String type,String time,symptom_model symptom_log) {
        this.symptom_log = symptom_log;
        this.type=type;
    }

    public general_model(String type,String time,medication_model medication_log) {
        this.medication_log = medication_log;
        this.type=type;
    }

    public sleep_model getSleep_log() {
        return sleep_log;
    }

    public symptom_model getSymptom_log() {
        return symptom_log;
    }

    public medication_model getMedication_log() {
        return medication_log;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }
}
