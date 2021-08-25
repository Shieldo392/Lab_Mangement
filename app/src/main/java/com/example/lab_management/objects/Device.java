package com.example.lab_management.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Device implements Parcelable {
    private int maPhong = 1;
    private int maTb = 1;
    private String tenTb;
    private String loaitb;
    private String tinhtrang;
    private String ngaynhap;
    private String ghichu;


    public Device(int maTb, String tenTb, String loaitb,int maPhong ,String tinhtrang, String ngaynhap, String ghichu) {

        this.maTb = maTb;
        this.tenTb = tenTb;
        this.loaitb = loaitb;
        this.maPhong = maPhong;
        this.tinhtrang = tinhtrang;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
    }


    public Device( String tenTb, String loaitb, String tinhtrang, String ngaynhap, String ghichu) {
        this.tenTb = tenTb;
        this.loaitb = loaitb;
        this.tinhtrang = tinhtrang;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Device() {
    }


    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }


    public int getMaTb() {
        return maTb;
    }

    public void setMaTb(int maTb) {
        this.maTb = maTb;
    }

    public String getTenTb() {
        return tenTb;
    }

    public void setTenTb(String tenTb) {
        this.tenTb = tenTb;
    }

    public String getLoaitb() {
        return loaitb;
    }

    public void setLoaitb(String loaitb) {
        this.loaitb = loaitb;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.maPhong);
        dest.writeInt(this.maTb);
        dest.writeString(this.tenTb);
        dest.writeString(this.loaitb);
        dest.writeString(this.tinhtrang);
        dest.writeString(this.ngaynhap);
        dest.writeString(this.ghichu);
    }
    public Device(Parcel in){
        this.maPhong = in.readInt();
        this.maTb = in.readInt();
        this.tenTb = in.readString();
        this.loaitb = in.readString();
        this.tinhtrang = in.readString();
        this.ngaynhap = in.readString();
        this.ghichu = in.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new Device(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };
    @Override
    public String toString(){
        return
                "Tên thiết bị: "+tenTb+"\n"+
                "Loại thiết bị: "+loaitb+"\n"+
                "Tình trạng: "+ tinhtrang+"\n"+
                "Ngày nhập: "+ngaynhap+"\n"+
                "Ghi chú: "+ghichu;
    }

}
