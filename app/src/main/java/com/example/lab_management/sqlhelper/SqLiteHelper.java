package com.example.lab_management.sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.utils.DefineName;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper extends SQLiteOpenHelper {

    private static final String TAG= "SQLiteHelper";
    public static final String Database_Name = "Lab_Management";
    public static final int DB_VERSION = 2;

    //tbl_Lab
    public static final String TBL_LAB = "lab";
    public static final String LAB_ID= "id";
    public static final String LAB_NAME = "name";



    // tbl_VerifyReport
    public static final String TBL_VERIFY = "verify_report";
    public static final String VERIFY_ID = "id";
    public static final String VERIFY_LAB_ID = "lab_id";
    public static final String VERIFY_USER_ID = "user_id";
    public static final String VERIFY_SHIFT = "shift";
    public static final String VERIFY_TIME = "time";
    public static final String VERIFY_NOTE = "note";

    //tbl_User
    public static final String TBL_USER = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CODE_TEACHER = "code_teacher";
    public static final String USER_EMAIL = "email";
    public static final String USER_FULL_NAME = "full_name";
    public static final String USER_GENDER = "gender";
    public static final String USER_DATE_OF_BIRTH = "dob";
    public static final String USER_ADDRESS = "address";
    public static final String USER_TYPE = "type";

    public static final String CREATE_TBL_LAB = String.format("" +
            "create table if not exists %s (" +
            "%s integer primary key autoincrement," +
            "%s text)", TBL_LAB, LAB_ID, LAB_NAME);

    //tbl_Device
    public static final String TBL_DEVICE = "device";
    public static final String DEVICE_ID = "id";
    public static final String DEVICE_NAME = "name";
    public static final String DEVICE_TYPE = "type";
    public static final String DEVICE_LAB_ID = "lab_id";
    public static final String DEVICE_STATUS = "status";
    public static final String DEVICE_DATE = "date";
    public static final String DEVICE_NOTE = "note";
    public static final String CREATE_TBL_DEVICE = String.format("" +
                    "create table if not exists %s(" +
                    " %s integer primary key autoincrement," +
                    " %s text," +
                    " %s text," +
                    " %s integer not null," +
                    " %s text," +
                    " %s text," +
                    " %s text)",
            TBL_DEVICE, DEVICE_ID, DEVICE_NAME, DEVICE_TYPE, DEVICE_LAB_ID, DEVICE_STATUS, DEVICE_DATE, DEVICE_NOTE);

    public static final String CREATE_TBL_VERIFY = String.format("" +
                    "create table if not exists %s (" +
                    " %s integer primary key autoincrement," +
                    " %s integer not null, " +
                    " %s integer not null," +
                    " %s integer not null," +
                    " %s text," +
                    " %s text)",
            TBL_VERIFY,VERIFY_ID, VERIFY_LAB_ID, VERIFY_USER_ID, VERIFY_SHIFT, VERIFY_TIME, VERIFY_NOTE);

    public static final String CREATE_TBL_USER = String.format("" +
                    "create table if not exists %s (" +
                    " %s integer primary key autoincrement," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s text," +
                    " %s integer)",
            TBL_USER, USER_ID, USER_NAME, USER_PASSWORD, USER_CODE_TEACHER, USER_EMAIL, USER_FULL_NAME,
            USER_GENDER, USER_DATE_OF_BIRTH, USER_ADDRESS, USER_TYPE);
    private static SqLiteHelper sqliteHelper = null;


    public static SqLiteHelper getInstance(Context context){
        if(sqliteHelper == null)
            sqliteHelper = new SqLiteHelper(context);
        return sqliteHelper;
    }


    public SqLiteHelper(@Nullable Context context) {
        super(context, Database_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TBL_DEVICE);
            db.execSQL(CREATE_TBL_LAB);
            db.execSQL(CREATE_TBL_USER);
            db.execSQL(CREATE_TBL_VERIFY);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.e(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXISTS " + TBL_DEVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_LAB);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_VERIFY);

        onCreate(db);
    }
    //tbl device
    public long Insert_Device(Device device){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEVICE_NAME, device.getTenTb());
        contentValues.put(DEVICE_TYPE, device.getLoaitb());
        contentValues.put(DEVICE_LAB_ID, device.getMaPhong());
        contentValues.put(DEVICE_STATUS, device.getTinhtrang());
        contentValues.put(DEVICE_DATE, device.getNgaynhap());
        contentValues.put(DEVICE_NOTE, device.getGhichu());


        long rowID = database.insert(TBL_DEVICE, null, contentValues);
        database.close();
        return rowID;
    }

    public long Update_Device_By_ID(Device device){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEVICE_NAME, device.getTenTb());
        contentValues.put(DEVICE_TYPE, device.getLoaitb());
        contentValues.put(DEVICE_LAB_ID, device.getMaPhong());
        contentValues.put(DEVICE_STATUS, device.getTinhtrang());
        contentValues.put(DEVICE_DATE, device.getNgaynhap());
        contentValues.put(DEVICE_NOTE, device.getGhichu());
        int rowID = database.update(TBL_DEVICE, contentValues, "id=?", new String[]{device.getMaTb()+""});
        database.close();
        return rowID;
    }

    public List<Device> Get_Device() {
        List<Device> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TBL_DEVICE;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                int lab_id = cursor.getInt(3);
                String status= cursor.getString(4);
                String date = cursor.getString(5);
                String note = cursor.getString(6);
                list.add(new Device(id, name, type, lab_id, status, date, note));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public List<Device> GetListDeviceByLabID(int labID){
        List<Device> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TBL_DEVICE +" where " + DEVICE_LAB_ID +" = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{labID+""});
        if(cursor!=null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                int lab_id = cursor.getInt(3);
                String status= cursor.getString(4);
                String date = cursor.getString(5);
                String note = cursor.getString(6);
                list.add(new Device(id, name, type, lab_id, status, date, note));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //tbl_lab
    public int Get_Count_Lab(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_LAB;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        return count;
    }

    public List<Lab> Get_Lab(){
        List<Lab> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_LAB;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!= null && cursor.moveToFirst()){
            do{
                list.add(new Lab(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public long Insert_Lab(Lab lab){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LAB_NAME, lab.getLab_Name());
        long rowID = db.insert(TBL_LAB, null, contentValues);
        db.close();
        return rowID;
    }

    //tbl_USER

    public int Get_Count_User(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_USER;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        return count;
    }
    public List<User> Get_User(){
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_USER;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!= null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String user_name = cursor.getString(1);
                String pass = cursor.getString(2);
                String code_user = cursor.getString(3);
                String email = cursor.getString(4);
                String fullname = cursor.getString(5);
                String gender = cursor.getString(6);
                String dob = cursor.getString(7);
                String address = cursor.getString(8);
                int type = cursor.getInt(9);

                list.add(new User(id, user_name, pass, code_user, email, fullname, gender, dob, address, type));

            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public long Insert_User(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.getUserName());
        contentValues.put(USER_PASSWORD, user.getPassword());
        contentValues.put(USER_CODE_TEACHER, user.getCodeUser());
        contentValues.put(USER_EMAIL, user.getEmail());
        contentValues.put(USER_FULL_NAME, user.getFullName());
        contentValues.put(USER_GENDER, user.getGender());
        contentValues.put(USER_DATE_OF_BIRTH, user.getDateOfBirth());
        contentValues.put(USER_ADDRESS, user.getAddress());
        contentValues.put(USER_TYPE, user.getType());
        long rowID = db.insert(TBL_USER, null, contentValues);
        db.close();
        return rowID;
    }

    public User GetUser(String username){
        User user = new User();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TBL_USER +" where " + USER_NAME +" = ?";
        Cursor cursor= db.rawQuery(sql, new String[]{username});
        if (cursor != null && cursor.moveToFirst()){
            do{
                String user_name = cursor.getString(cursor.getColumnIndex(USER_NAME));
                String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                user.setUserName(user_name);
                user.setPassword(password);
            }while (cursor.moveToNext());
        }
        return user;
    }



    //TBL_Verify_Report
    public int Get_Count_Verify(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_VERIFY;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        return count;
    }

    public List<VerifyReport> Get_Verify(){
        List<VerifyReport> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_VERIFY;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!= null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                int lab_id = cursor.getInt(1);
                int user_id = cursor.getInt(2);
                int shift_int = cursor.getInt(3);
                String time = cursor.getString(4);
                String note = cursor.getString(5);
                list.add(new VerifyReport(id, lab_id, user_id, shift_int==1?true:false, time, note));
            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public long Insert_Verify(VerifyReport report){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(VERIFY_LAB_ID, report.getMaPhong());
        contentValues.put(VERIFY_USER_ID, report.getUser_id());
        contentValues.put(VERIFY_SHIFT, report.getShift()?1:0);
        contentValues.put(VERIFY_TIME, report.getTime());
        contentValues.put(VERIFY_NOTE, report.getNote());



        long rowID = db.insert(TBL_VERIFY, null, contentValues);
        db.close();
        return rowID;
    }

    public int Update_Verify_By_ID(VerifyReport report){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(VERIFY_LAB_ID, report.getMaPhong());
        contentValues.put(VERIFY_USER_ID, report.getUser_id());
        contentValues.put(VERIFY_SHIFT, report.getShift()?1:0);
        contentValues.put(VERIFY_TIME, report.getTime());
        contentValues.put(VERIFY_NOTE, report.getNote());
        String id = report.getId()+"";
        int rowID = db.update(TBL_VERIFY, contentValues, "id=?", new String[]{id});
        db.close();
        return rowID;
    }

    public int Delete_Verify_By_ID(int id){
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TBL_VERIFY, "id=?", new String[]{id+""});
        db.close();
        return rowID;
    }

    public int Delete_Verify_ALL() {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TBL_VERIFY, null, null);
        db.close();
        return rowID;
    }

}
