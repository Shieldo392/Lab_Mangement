package com.example.lab_management.objects;

public class Device {
    private int deviceID;
    private String deviceName;
    private int deviceCount;

    public Device(int deviceID, String deviceName, int deviceCount) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceCount = deviceCount;
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

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
