package com.example.lab_management.objects;

import java.util.Date;

public class RegisterLab {
    private int registerID;
    private int labID;
    private int userID;
    private String session; // ca thực hành
    private String time; // thứ mấy trong tuần
    //private int count; // số buổi thực hành // we dont need it anymore
    private int termID;
    private String replaced; // thay the ly thuyet

    public RegisterLab(int registerID, int labID, int userID, String session, String time, int termID, String replaced) {
        this.registerID = registerID;
        this.labID = labID;
        this.userID = userID;
        this.session = session;
        this.time = time;
        this.termID = termID;
        this.replaced = replaced;
    }

    public RegisterLab() {
    }

    public int getRegisterID() {
        return registerID;
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getReplaced() {
        return replaced;
    }

    public void setReplaced(String replaced) {
        this.replaced = replaced;
    }

    // TODO
    @Override
    public String toString() {
        return "RegisterLab{" +
                "registerID=" + registerID +
                ", labID=" + labID +
                ", userID=" + userID +
                ", session='" + session + '\'' +
                ", time=" + time +
                ", termID=" + termID +
                ", replaced=" + replaced +
                '}';
    }
}
