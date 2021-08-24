package com.example.lab_management.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Date;

public class DateUtility {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Date Date(int year, int month, int date){
        return java.sql.Date.valueOf(LocalDate.of(2020, 2, 3) + "");
    }
}
