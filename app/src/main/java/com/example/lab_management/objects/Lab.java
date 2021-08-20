package com.example.lab_management.objects;

import java.util.ArrayList;
import java.util.List;

public class Lab {
    private int lab_ID;
    private String lab_Name;
    /*private String lab_Area; //Khu A7 or A1
    private int lab_floor; // Tầng*/

    private List<Device> deviceList;
    private List<VerifyReport> historyVerify;


    public Lab(int lab_id, String lab_Name){
        this.lab_Name = lab_Name;
        this.lab_ID = lab_id;
        deviceList = new ArrayList<>();
        historyVerify = new ArrayList<>();
    }
    public Lab(int lab_ID, String lab_Name,List<Device> deviceList, List<VerifyReport> historyVerify) {
        this.lab_ID = lab_ID;
        this.lab_Name = lab_Name;
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
