package com.example.lab_management.objects;

public class VerifyReport {
    private int id;
    private int user_ID; // mã giảng viên
    private String shift; //Ca làm việc
    private int time; // Thứ làm việc (Thứ 2 -> chủ nhật) <=> (2-8)
    private String note;

    public VerifyReport(int id, int userID, String shift, int time, String note) {
        this.id = id;
        this.user_ID = userID;
        this.shift = shift;
        this.time = time;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
