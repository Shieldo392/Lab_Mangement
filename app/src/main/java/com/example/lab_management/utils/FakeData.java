package com.example.lab_management.utils;

import android.content.Context;
import android.widget.Toast;


import com.example.lab_management.objects.Device;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

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
        list.add(new User(1, "duongvu", "12345", "12316233",
                "habas@gmail.com", "Vũ Thị Dương", "nữ", "10/03/1998",
                "Hà Nội", 0));
        list.add(new User(1, "damamnh", "12345", "12316233",
                "habas@gmail.com", "hà Mạnh Đào", "nam", "10/03/1998",
                "Hà Nội", 0));
        list.add(new User(1, "abcd", "12345", "12316233",
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


}