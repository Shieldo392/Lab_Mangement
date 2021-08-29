package com.example.lab_management.sqlhelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.RegisterLab;
import com.example.lab_management.objects.Subject;
import com.example.lab_management.objects.Term;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper extends SQLiteOpenHelper {

    private static final String TAG= "SQLiteHelper";
    public static final String Database_Name = "Lab_Management";
    public static final int DB_VERSION = 11;

    // tbl_VerifyReport
    public static final String TBL_VERIFY = "verify_report";
    public static final String VERIFY_ID = "verify_id"; // ghi ro id cai j
    public static final String VERIFY_LAB_ID = "lab_id";
    public static final String VERIFY_USER_ID = "user_id";
    public static final String VERIFY_SHIFT = "verify_shift";
    public static final String VERIFY_TIME = "verify_time";
    public static final String VERIFY_NOTE = "verify_note";

    //tbl_User
    public static final String TBL_USER = "user";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_CODE_TEACHER = "user_code_teacher";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_FULL_NAME = "user_full_name";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_DATE_OF_BIRTH = "user_dob";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_TYPE = "user_type";

    //tbl_Lab
    public static final String TBL_LAB = "lab";
    public static final String LAB_ID= "lab_id";
    public static final String LAB_NAME = "lab_name";
    public static final String LAB_AREA = "lab_area";
    public static final String LAB_FLOOR = "lab_floor";
    public static final String LAB_NOTE = "lab_note";

    // tbl RegisterLab
    public static final String TBL_REGISTERLAB = "registerlab";
    public static final String REGISTERLAB_ID = "registerlab_id";
    //public static final String USER_ID = "user_id";
    public static final String REGISTERLAB_SESSION = "registerlab_session";
    public static final String REGISTERLAB_TIME = "registerlab_time";
    //public static final String TERM_ID = "term_id";
    public static final String REGISTERLAB_REPLACED = "registerlab_replaced";

    // tbl_subject
    public static final String TBL_SUBJECT = "subject";
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";

    // tbl_term
    public static final String TBL_TERM = "term";
    public static final String TERM_ID = "term_id";
    public static final String TERM_NAME = "term_name";

    public static final String CREATE_TBL_SUBJECT =
            "CREATE TABLE IF NOT EXISTS " + TBL_SUBJECT + "(" +
                    SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SUBJECT_NAME + " TEXT)";

    public static final String CREATE_TBL_TERM =
            "CREATE TABLE IF NOT EXISTS " + TBL_TERM + "(" +
                    TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TERM_NAME + " TEXT," +
                    SUBJECT_ID + " INTEGER)";

    public static final String CREATE_TBL_REGISTERLAB =
            "CREATE TABLE IF NOT EXISTS " + TBL_REGISTERLAB + "(" +
                    REGISTERLAB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LAB_ID + " INTEGER," +
                    USER_ID + " INTEGER, " +
                    REGISTERLAB_SESSION + " TEXT," +
                    REGISTERLAB_TIME + " TEXT," +
                    TERM_ID + " INTEGER," +
                    REGISTERLAB_REPLACED + " TEXT)";

    public static final String CREATE_TBL_LAB = String.format("" +
            "create table if not exists %s (" +
            " %s integer primary key autoincrement," +
            " %s text," +
            " %s text," +
            " %s text," +
            " %s text)", TBL_LAB, LAB_ID, LAB_NAME, LAB_AREA, LAB_FLOOR, LAB_NOTE);

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

            db.execSQL(CREATE_TBL_SUBJECT);
            db.execSQL(CREATE_TBL_TERM);
            db.execSQL(CREATE_TBL_REGISTERLAB);
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

        db.execSQL("DROP TABLE IF EXISTS " + TBL_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_TERM);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_REGISTERLAB);
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
                list.add(new Lab(cursor.getInt(0),
                                 cursor.getString(1),
                                 cursor.getString(2),
                                 cursor.getString(3),
                                 cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public Lab GetLabByID(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TBL_LAB + " WHERE " + LAB_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        Lab lab = null;
        if(cursor!= null && cursor.moveToFirst()) {
            lab = new Lab(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        db.close();
        return lab;
    }

    public long Insert_Lab(Lab lab){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LAB_NAME, lab.getLab_Name());
        contentValues.put(LAB_AREA, (lab.getKhuNha()!=null?lab.getKhuNha():""));
        contentValues.put(LAB_FLOOR, (lab.getTang()!=null?lab.getTang():""));
        contentValues.put(LAB_NOTE, (lab.getGhiChu()!= null?lab.getGhiChu():""));

        long rowID = db.insert(TBL_LAB, null, contentValues);
        db.close();
        return rowID;
    }

    public int DeleteLabByID(int lab_id){
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TBL_LAB, LAB_ID+"=?", new String[]{lab_id+""});
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
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TBL_USER +" where " + USER_NAME +" = ?";
        Cursor cursor= db.rawQuery(sql, new String[]{username});
        if (cursor != null && cursor.moveToFirst()){
            do{
                User user = new User();
                String user_name = cursor.getString(cursor.getColumnIndex(USER_NAME));
                String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                user.setUserName(user_name);
                user.setPassword(password);
                return user;
            }while (cursor.moveToNext());
        }
        return null;
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

        contentValues.put(VERIFY_LAB_ID, report.getLabID());
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

        contentValues.put(VERIFY_LAB_ID, report.getLabID());
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
        int rowID = db.delete(TBL_VERIFY, VERIFY_ID+"=?", new String[]{id+""});
        db.close();
        return rowID;
    }

    public int Delete_Device_By_ID(int id){
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TBL_DEVICE,"id=?",new String[]{id+""});
        db.close();
        return rowID;
    }
    public int Delete_Verify_ALL() {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TBL_VERIFY, null, null);
        db.close();
        return rowID;
    }

    // table ResgisterLab
    public int GetTableCount(String tableName){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + tableName;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        Log.d(TAG, "GetTableCount: " + tableName + " " + cursor.getCount());
        return cursor.getCount();
    }

    public long Insert_Subject(Subject subject) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SUBJECT_ID, subject.getSubjectID());
        contentValues.put(SUBJECT_NAME, subject.getSubjectName());

        long rowID = db.insert(TBL_SUBJECT, null, contentValues);
        db.close();
        return rowID;
    }

    public long Insert_Term(Term term) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TERM_ID, term.getTermID());
        contentValues.put(TERM_NAME, term.getTermName());
        contentValues.put(SUBJECT_ID, term.getSubjectID());

        long rowID = db.insert(TBL_TERM, null, contentValues);
        db.close();
        return rowID;
    }

    @SuppressLint("SimpleDateFormat")
    public boolean Insert_RegisterLab(RegisterLab registerLab){
        System.err.println(registerLab.getRegisterID() + "+" + registerLab.getLabID() + "+" + registerLab.getTermID() + "+" + registerLab.getTime());
        System.err.println("AAA: " + registerLab.getTime()+"");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(REGISTERLAB_ID, registerLab.getRegisterID());
        contentValues.put(LAB_ID, registerLab.getLabID());
        contentValues.put(USER_ID, registerLab.getUserID());
        contentValues.put(REGISTERLAB_SESSION, registerLab.getSession());
        contentValues.put(REGISTERLAB_TIME, registerLab.getTime());

        contentValues.put(TERM_ID, registerLab.getTermID());

        if (registerLab.getReplaced() == null || registerLab.getReplaced().equals("")){
            contentValues.put(REGISTERLAB_REPLACED, "");
        }
        else {
            contentValues.put(REGISTERLAB_REPLACED, registerLab.getReplaced());
        }

        long rowID = db.insert(TBL_REGISTERLAB, null, contentValues);
        db.close();
        return rowID >= 0;
    }

    @SuppressLint("SimpleDateFormat")
    public boolean Update_RegisterLab(RegisterLab registerLab){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REGISTERLAB_ID, registerLab.getRegisterID());
        contentValues.put(LAB_ID, registerLab.getLabID());
        contentValues.put(USER_ID, registerLab.getUserID());
        contentValues.put(REGISTERLAB_SESSION, registerLab.getSession());
        contentValues.put(REGISTERLAB_TIME, registerLab.getTime());

        contentValues.put(TERM_ID, registerLab.getTermID());

        if (registerLab.getReplaced() == null){
            contentValues.put(REGISTERLAB_REPLACED, "");
        }
        else {
            contentValues.put(REGISTERLAB_REPLACED, registerLab.getReplaced());
        }

        int rowEffect = db.update(TBL_REGISTERLAB, contentValues, REGISTERLAB_ID + " = ?",
                new String[]{String.valueOf(registerLab.getRegisterID())});
        db.close();
        return rowEffect != -1;
    }

    public boolean Delete_RegisterLab(RegisterLab registerLab) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TBL_REGISTERLAB, REGISTERLAB_ID + " = ?",
                new String[]{String.valueOf(registerLab.getRegisterID())});
        db.close();
        return (rowEffect != -1);
    }

    @SuppressLint("SimpleDateFormat")
    public List<RegisterLab> GetAllRegisterLabByUserID(int userID) {
        SQLiteDatabase db = getReadableDatabase();
        List<RegisterLab> registerLabsList = new ArrayList<RegisterLab>();
        String sql = "SELECT * FROM " + TBL_REGISTERLAB + " WHERE " + USER_ID + " = " + userID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                registerLabsList.add(new RegisterLab(cursor.getInt(cursor.getColumnIndex(REGISTERLAB_ID)),
                        cursor.getInt(cursor.getColumnIndex(LAB_ID)),
                        cursor.getInt(cursor.getColumnIndex(USER_ID)),
                        cursor.getString(cursor.getColumnIndex(REGISTERLAB_SESSION)),
                        cursor.getString(cursor.getColumnIndex(REGISTERLAB_TIME)),
                        cursor.getInt(cursor.getColumnIndex(TERM_ID)),
                        cursor.getString(cursor.getColumnIndex(REGISTERLAB_REPLACED))));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return registerLabsList;
    }

    public List<Lab> GetAllLab(){
        SQLiteDatabase db = getReadableDatabase();
        List<Lab> labsList = new ArrayList<Lab>();
        String sql = "SELECT * FROM " + TBL_LAB;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // LAB_ID, LAB_NAME, LAB_AREA, LAB_FLOOR, LAB_NOTE
                labsList.add(new Lab(cursor.getInt(cursor.getColumnIndex(LAB_ID)),
                cursor.getString(cursor.getColumnIndex(LAB_NAME)),
                cursor.getString(cursor.getColumnIndex(LAB_AREA)),
                cursor.getString(cursor.getColumnIndex(LAB_FLOOR)),
                cursor.getString(cursor.getColumnIndex(LAB_NOTE))));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return labsList;
    }

    public List<Term> GetAllTerm() {
        SQLiteDatabase db = getReadableDatabase();
        List<Term> termsList = new ArrayList<>();
        String sql = "SELECT * FROM " + TBL_TERM;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                termsList.add(new Term(cursor.getInt(cursor.getColumnIndex(TERM_ID)),
                        cursor.getString(cursor.getColumnIndex(TERM_NAME)),
                        cursor.getInt(cursor.getColumnIndex(SUBJECT_ID))));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return termsList;
    }

    public Term GetTermByID(int id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TBL_TERM + " WHERE " + TERM_ID + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        Term term = null;
        if (cursor != null && cursor.moveToFirst()) {
            term = new Term(cursor.getInt(cursor.getColumnIndex(TERM_ID)),
                    cursor.getString(cursor.getColumnIndex(TERM_NAME)),
                    cursor.getInt(cursor.getColumnIndex(SUBJECT_ID)));
        }
        db.close();
        return term;
    }
}
