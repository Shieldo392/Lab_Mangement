package com.example.lab_management.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class VerifyReport implements Parcelable{
    private int id;
    private int labID;
    private int user_id; // tên giảng viên
    private boolean shift; //true: ca sáng, false: ca chiều
    private String time; // Thứ làm việc (Thứ 2 -> chủ nhật) <=> (2-8)
    private String note;

    public VerifyReport(int id, int maPhong, int tenGV, boolean shift, String time, String note) {
        this.id = id;
        this.labID = maPhong;
        this.user_id = tenGV;
        this.shift = shift;
        this.time = time;
        this.note = note;
    }

    public VerifyReport() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isShift() {
        return shift;
    }

    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return user_id;
    }

    public void setUser_ID(int user_ID) {
        this.user_id = user_ID;
    }

    public boolean getShift() {
        return shift;
    }

    public void setShift(boolean shift) {
        this.shift = shift;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    /*private int id;
    private int maPhong;
    private int user_id; // tên giảng viên
    private boolean shift; //true: ca sáng, false: ca chiều
    private String time; // Thứ làm việc (Thứ 2 -> chủ nhật) <=> (2-8)
    private String note;*/

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.labID);
        dest.writeInt(this.user_id);
        dest.writeByte((byte) (this.shift?1:0));
        dest.writeString(this.time);
        dest.writeString(this.note);
    }
    public VerifyReport(Parcel in){
        this.id = in.readInt();
        this.labID = in.readInt();
        this.user_id = in.readInt();
        this.shift = in.readByte()!=0;
        this.time = in.readString();
        this.note = in.readString();

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new VerifyReport(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };

}
