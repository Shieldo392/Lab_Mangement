package com.example.lab_management.manage_registerlab.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab_management.R;
import com.example.lab_management.objects.RegisterLab;

import java.text.SimpleDateFormat;
import java.util.List;

public class RegisterLabAdapter extends ArrayAdapter<RegisterLab> {
    Activity context;
    int layout;
    List<RegisterLab> list;

    public RegisterLabAdapter(@NonNull Context context, int resource, @NonNull List<RegisterLab> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.layout = resource;
        this.list = objects;
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater flater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = flater.inflate(layout, parent,false);

        TextView txt_RegisterID = row.findViewById(R.id.txt_item_registerlabid);
        TextView txt_LabID = row.findViewById(R.id.txt_item_labid);
        TextView txt_Session = row.findViewById(R.id.txt_item_session);
        TextView txt_Time = row.findViewById(R.id.txt_item_time);
        TextView txt_TermID = row.findViewById(R.id.txt_item_termid);
        TextView txt_Replaced = row.findViewById(R.id.txt_item_replaced);

        try{
            RegisterLab registerLab = list.get(position);
            txt_RegisterID.setText(registerLab.getRegisterID()+"");
            txt_LabID.setText(registerLab.getLabID()+"");
            txt_Session.setText(registerLab.getSession()+"");
            txt_Time.setText(registerLab.getTime());
            txt_TermID.setText(registerLab.getTermID()+"");
            txt_Replaced.setText(registerLab.getReplaced());
        }
        catch (Exception e){
            Log.e("RegisterLabAdapter", e.toString());
        }

        return row;
    }
}
