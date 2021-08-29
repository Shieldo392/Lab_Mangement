package com.example.lab_management.manage_registerlab.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.lab_management.R;
import com.example.lab_management.objects.RegisterLab;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegisterLabAdapter extends ArrayAdapter<RegisterLab> {
    Activity context;
    int layout;
    List<RegisterLab> list;

    View oldView = null;
    int oldContent = -1;

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
        TextView txt_LabName = row.findViewById(R.id.txt_item_labname);
        TextView txt_Session = row.findViewById(R.id.txt_item_session);
        TextView txt_Time = row.findViewById(R.id.txt_item_time);
        TextView txt_TermName = row.findViewById(R.id.txt_item_termname);
        TextView txt_Replaced = row.findViewById(R.id.txt_item_replaced);

        try{
            RegisterLab registerLab = list.get(position);
            txt_RegisterID.setText(registerLab.getRegisterID()+"");

            String labName = SqLiteHelper.getInstance(context).GetLabByID(registerLab.getLabID()).getLab_Name();
            txt_LabName.setText(labName);

            txt_Session.setText(registerLab.getSession()+"");
            txt_Time.setText(registerLab.getTime());

            Date time = new SimpleDateFormat("dd/MM/yyyy").parse(registerLab.getTime());
            Date now = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

            if (time.compareTo(now) < 0){
                SetRowColor(row, Color.LTGRAY, Color.LTGRAY);
            }
            else if (time.compareTo(now) == 0){
                SetRowColor(row, Color.YELLOW, Color.YELLOW);
            }

            String termName = SqLiteHelper.getInstance(context).GetTermByID(registerLab.getTermID()).getTermName();
            txt_TermName.setText(termName);

            if (registerLab.getReplaced() == null || registerLab.getReplaced().equals("")){
                txt_Replaced.setVisibility(View.GONE);
                row.findViewById(R.id.txt_label_replaced).setVisibility(View.GONE);
            }
            else {
                txt_Replaced.setVisibility(View.VISIBLE);
                row.findViewById(R.id.txt_label_replaced).setVisibility(View.VISIBLE);
                txt_Replaced.setText(registerLab.getReplaced());
            }
        }
        catch (Exception e){
            Log.e("RegisterLabAdapter", e.toString());
        }

        return row;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SetSelectedView(View view){
        if (oldView != null) {
            if (view != oldView) {
                int newContent = ((ColorDrawable)view.findViewById(R.id.subitem_registerlab1).getBackground()).getColor();
                SetRowColor(oldView, oldContent, oldContent);

                SetRowColor(view, Color.RED, newContent);
                oldView = view;
                oldContent = newContent;
            }
        }
        else{
            int newContent = ((ColorDrawable)view.findViewById(R.id.subitem_registerlab1).getBackground()).getColor();

            SetRowColor(view, Color.RED, newContent);

            oldView = view;
            oldContent = newContent;
        }
    }

    public void SetRowColor(View row, int border, int content){
        row.findViewById(R.id.item_registerlab).setBackgroundColor(border);

        row.findViewById(R.id.subitem_registerlab1).setBackgroundColor(content);
        row.findViewById(R.id.subitem_registerlab2).setBackgroundColor(content);
        row.findViewById(R.id.subitem_registerlab3).setBackgroundColor(content);
        row.findViewById(R.id.subitem_registerlab4).setBackgroundColor(content);

    }
}
