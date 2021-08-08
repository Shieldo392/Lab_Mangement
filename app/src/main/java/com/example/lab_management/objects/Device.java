package com.example.lab_management.objects;

public class Device {
    private int deviceID;
    private String deviceName;
    private String type;
    private boolean status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public Device(int deviceID, String deviceName, String type, boolean status) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.type = type;
        this.status = status;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
