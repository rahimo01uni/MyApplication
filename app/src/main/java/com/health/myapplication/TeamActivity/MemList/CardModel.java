package com.health.myapplication.TeamActivity.MemList;

public class CardModel {
    private String  remider,leftdays;

    public CardModel( String remider, String leftdays) {
        this.remider = remider;
        this.leftdays = leftdays;
    }



    public String getRemider() {
        return remider;
    }

    public void setRemider(String remider) {
        this.remider = remider;
    }

    public String getLeftdays() {
        return leftdays;
    }

    public void setLeftdays(String leftdays) {
        this.leftdays = leftdays;
    }
}