package com.example.lab_management.objects;

import java.util.List;

public class Lab {
    private int lab_ID;
    private String lab_Name;
    /*private String lab_Area; //Khu A7 or A1
    private int lab_floor; // Tầng*/
    private int lab_computer_count;
    private List<Device> deviceList;
    private List<VerifyReport> historyVerify;

    public Lab(int lab_ID, String lab_Name, int lab_computer_count, List<Device> deviceList, List<VerifyReport> historyVerify) {
        this.lab_ID = lab_ID;
        this.lab_Name = lab_Name;
        this.lab_computer_count = lab_computer_count;
        this.deviceList = deviceList;
        this.historyVerify = historyVerify;
    }

    public int getLab_ID() {
        return lab_ID;
    }

    public void setLab_ID(int lab_ID) {
        this.lab_ID = lab_ID;
    }

    public String getLab_Name() {
        return lab_Name;
    }

    public void setLab_Name(String lab_Name) {
        this.lab_Name = lab_Name;
    }

    public int getLab_computer_count() {
        return lab_computer_count;
    }

    public void setLab_computer_count(int lab_computer_count) {
        this.lab_computer_count = lab_computer_count;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<VerifyReport> getHistoryVerify() {
        return historyVerify;
    }

    public void setHistoryVerify(List<VerifyReport> historyVerify) {
        this.historyVerify = historyVerify;
    }
}
