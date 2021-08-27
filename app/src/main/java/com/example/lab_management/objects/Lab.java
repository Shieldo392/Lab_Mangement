package com.example.lab_management.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Lab implements Parcelable {
    private int lab_ID;
    private String lab_Name;
    /*private String lab_Area; //Khu A7 or A1
    private int lab_floor; // Tầng*/

    private List<Device> deviceList;
    private List<VerifyReport> historyVerify;
    private String khuNha;
    private String tang;
    private String ghiChu;

    public Lab(int maPhong, String tenPhong, String khuNha, String tang,  String ghiChu) {
        this.lab_ID = maPhong;
        this.lab_Name = tenPhong;
        this.khuNha = khuNha;
        this.tang = tang;
        this.ghiChu = ghiChu;
    }

    public Lab( String tenPhong, String khuNha, String tang, int slManHinh, int slChuot,
                int slDieuHoa, int chuotHong, int dieuHoaHong, int manHinhHong, String ghiChu) {
        this.lab_Name = tenPhong;
        this.khuNha = khuNha;
        this.tang = tang;
        this.ghiChu = ghiChu;
        this.deviceList = new ArrayList<>();
        this.historyVerify = new ArrayList<>();
    }

    protected Lab(Parcel in) {
        lab_ID = in.readInt();
        lab_Name = in.readString();
        deviceList = in.createTypedArrayList(Device.CREATOR);
        historyVerify = in.createTypedArrayList(VerifyReport.CREATOR);
        khuNha = in.readString();
        tang = in.readString();
        ghiChu = in.readString();
    }

    public static final Creator<Lab> CREATOR = new Creator<Lab>() {
        @Override
        public Lab createFromParcel(Parcel in) {
            return new Lab(in);
        }

        @Override
        public Lab[] newArray(int size) {
            return new Lab[size];
        }
    };

    public Lab() {

    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaPhong() {
        return lab_ID;
    }

    public void setMaPhong(int maPhong) {
        this.lab_ID = maPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.lab_Name = tenPhong;
    }

    public String getKhuNha() {
        return khuNha;
    }

    public void setKhuNha(String khuNha) {
        this.khuNha = khuNha;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tên phòng: "+ lab_Name + "\n" +
                "Khu nhà: " +khuNha +"\n" +
                "Tầng : " +tang +"\n" +
                "Ghi Chú : " + ghiChu + "\n ";

    }

    public Lab(int lab_id, String lab_Name){
        this.lab_Name = lab_Name;
        this.lab_ID = lab_id;
        this.deviceList = new ArrayList<>();
        this.historyVerify = new ArrayList<>();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.lab_ID);
        dest.writeString(this.lab_Name);
        dest.writeString(this.khuNha);
        dest.writeString(this.tang);
        dest.writeString(this.ghiChu);
    }
}
