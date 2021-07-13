package com.example.lab_management.sqlhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab_management.utils.DefineName;

public class SqLiteHelper extends SQLiteOpenHelper {




    public SqLiteHelper(@Nullable Context context) {
        super(context, DefineName.DATABASE_NAME, null ,DefineName.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
