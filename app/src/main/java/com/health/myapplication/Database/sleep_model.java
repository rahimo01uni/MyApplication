package com.health.myapplication.Database;

public class sleep_model {
String Id, SleepTime,WakeUpTime,Date,Duration,QualityOfSleep,NightWokeUp, Note, Time;

    public sleep_model(String sleepTime, String wakeUpTime) {
        SleepTime = sleepTime;
        WakeUpTime = wakeUpTime;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public sleep_model() {
    }

    public void setId(String id) {
        Id = id;
    }

    public void setSleepTime(String sleepTime) {
        SleepTime = sleepTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        WakeUpTime = wakeUpTime;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setQualityOfSleep(String qualityOfSleep) {
        QualityOfSleep = qualityOfSleep;
    }

    public void setNightWokeUp(String nightWokeUp) {
        NightWokeUp = nightWokeUp;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getId() {
        return Id;
    }

    public String getSleepTime() {
        return SleepTime;
    }

    public String getWakeUpTime() {
        return WakeUpTime;
    }

    public String getDate() {
        return Date;
    }

    public String getDuration() {
        return Duration;
    }

    public String getQualityOfSleep() {
        return QualityOfSleep;
    }

    public String getNightWokeUp() {
        return NightWokeUp;
    }

    public String getNote() {
        return Note;
    }
}
