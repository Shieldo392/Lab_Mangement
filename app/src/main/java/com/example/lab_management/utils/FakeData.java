package com.example.lab_management.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.RequiresApi;

import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.RegisterLab;
import com.example.lab_management.objects.Subject;
import com.example.lab_management.objects.Term;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class FakeData {
    public static void InsertLab(Context context) {
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<Lab> list = new ArrayList<>();
        list.add(new Lab(1, "A7-01"));
        list.add(new Lab(1, "A7-02"));
        list.add(new Lab(1, "A7-03"));
        list.add(new Lab(1, "A7-04"));
        list.add(new Lab(1, "A8-01"));
        list.add(new Lab(1, "A8-02"));
        list.add(new Lab(1, "A8-03"));
        list.add(new Lab(1, "A8-04"));

        if (sqliteHelper.Get_Count_Lab() <= 0) {
            for (Lab lab : list) {
                long result = sqliteHelper.Insert_Lab(lab);
                if (result == -1) {
                    Toast.makeText(context, "Them khong thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    public static void InsertUser(Context context) {
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<User> list = new ArrayList<>();
        list.add(new User(1, "abcd", "12345", "12316233",
                "habas@gmail.com", "Nguyễn Văn A", "nam", "10/03/1998",
                "Hà Nội", 0));
        list.add(new User(2, "duongvu", "12345", "12316233",
                "habas@gmail.com", "Vũ Thị Dương", "nữ", "10/03/1998",
                "Hà Nội", 0));
        list.add(new User(3, "damamnh", "12345", "12316233",
                "habas@gmail.com", "hà Mạnh Đào", "nam", "10/03/1998",
                "Hà Nội", 0));
        list.add(new User(4, "abcd", "12345", "12316233",
                "habas@gmail.com", "Nguyễn Thùy Linh", "nữ", "10/03/1998",
                "Hà Nội", 0));


        if (sqliteHelper.Get_Count_User() <= 0) {
            for (User user : list) {
                long result = sqliteHelper.Insert_User(user);
                if (result == -1) {
                    Toast.makeText(context, "Them khong thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    public static void Insert_Verify(Context context){
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<VerifyReport> list = new ArrayList<>();
        list.add(new VerifyReport(1, 1, 1, true, "10/3/2021", "Đầy đủ thiết bị"));
        list.add(new VerifyReport(1, 2, 2, false, "11/3/2021", "Đầy đủ thiết bị"));
        list.add(new VerifyReport(1, 4, 4, true, "12/3/2021", "Đầy đủ thiết bị"));
        list.add(new VerifyReport(1, 3, 3, false, "13/3/2021", "Đầy đủ thiết bị"));
        list.add(new VerifyReport(1, 1, 1, true, "14/3/2021", "Đầy đủ thiết bị"));


        if (sqliteHelper.Get_Count_Verify() <= 0) {
            for (VerifyReport verifyReport : list) {
                long result = sqliteHelper.Insert_Verify(verifyReport);
                if (result == -1) {
                    Toast.makeText(context, "Them khong thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public static void Insert_Device(Context context){
        SqLiteHelper sqLiteHelper=SqLiteHelper.getInstance(context);
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device(1,"Chuột acer","Chuột",1,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(2,"MH Dell S2421H","Màn hình",3,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(4,"Case Dell 1","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 2","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 3","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 4","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 5","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 6","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 7","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 8","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 9","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 10","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(4,"Case Dell 11","Case",1,"Hỏng","1/1/2019","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 1","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 2","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 3","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 4","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 5","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 6","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 7","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 8","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 9","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 10","Màn hình",2,"Tốt","1/1/2020","Không có"));
        deviceList.add(new Device(3,"MH Dell S2422H 11","Màn hình",2,"Tốt","1/1/2020","Không có"));

        if(sqLiteHelper.Get_Count_Device()<=0){
            for(Device device: deviceList){
                long result = sqLiteHelper.Insert_Device(device);
                if(result==-1){
                    Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public static void Insert_Subject_Term(Context context){
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<Subject> list1 = new ArrayList<>();
        list1.add(new Subject(1, "Phát triển ứng dụng trên thiết bị di động"));
        list1.add(new Subject(2, "Lập trình PHP"));
        list1.add(new Subject(3, "Lập trình Java"));

        if (sqliteHelper.GetTableCount(SqLiteHelper.TBL_SUBJECT) <= 0){
            for (Subject subject : list1) {
                long result = sqliteHelper.Insert_Subject(subject);
                if (result == -1) {
                    Toast.makeText(context, "Them khong thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        List<Term> list2 = new ArrayList<>();
        list2.add(new Term(1, "HP4789 - LT Android", list1.get(0).getSubjectID()));
        list2.add(new Term(2, "HP5124 - LT Android", list1.get(0).getSubjectID()));
        list2.add(new Term(3, "HP4886 - LT PHP", list1.get(1).getSubjectID()));
        list2.add(new Term(4, "HP4921 - LT PHP", list1.get(1).getSubjectID()));
        list2.add(new Term(5, "HP4886 - LT Java", list1.get(2).getSubjectID()));
        list2.add(new Term(6, "HP5341 - LT Java", list1.get(2).getSubjectID()));


        if (sqliteHelper.GetTableCount(SqLiteHelper.TBL_TERM) <= 0){
            for (Term term : list2) {
                long result = sqliteHelper.Insert_Term(term);
                if (result == -1) {
                    Toast.makeText(context, "Them khong thanh cong! Insert_Subject_Term", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void Insert_RegisterLab(Context context) {
        // nhieu qua
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<RegisterLab> list = new ArrayList<>();
        // int registerID, int labID, int userID, String session, Date time, int termID, Date replaced
        list.add(new RegisterLab(1, 1, 1, "1, 2, 3", "30/07/2021", 1, ""));
        list.add(new RegisterLab(2, 1, 1, "4, 5, 6", "21/06/2021", 2, ""));
        list.add(new RegisterLab(3, 2, 2, "1, 2, 3", "05/09/2021", 3, "03/09/2021"));
        list.add(new RegisterLab(4, 2, 1, "10, 11, 12", "05/09/2021", 3, ""));
        list.add(new RegisterLab(5, 3, 2, "7, 8, 9", "06/09/2021", 3, "05/09/2021"));
        if (sqliteHelper.GetTableCount(SqLiteHelper.TBL_REGISTERLAB) <= 0){
            for (RegisterLab registerLab : list) {
                long result = sqliteHelper.Insert_RegisterLab(registerLab);
                if (result <= 0) {
                    Toast.makeText(context, "Them khong thanh cong! Insert_RegisterLab", Toast.LENGTH_SHORT).show();
                    System.out.println("Them khong thanh cong! Insert_RegisterLab");
                }
            }
        }
    }
}