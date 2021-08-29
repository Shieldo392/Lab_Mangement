package com.example.lab_management.manage_verify.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.lab_management.R;
import com.example.lab_management.objects.Lab;
import com.example.lab_management.objects.User;
import com.example.lab_management.objects.VerifyReport;
import com.example.lab_management.sqlhelper.SqLiteHelper;

import java.util.List;

public class VerifyAdapter extends BaseAdapter {
    Activity context;
    int layoutId;
    List<VerifyReport> list;

    public VerifyAdapter(Activity context1, int layoutId, List<VerifyReport> list) {
        this.context = context1;
        this.layoutId = layoutId;
        this.list = list;
    }

    public List<VerifyReport> getList() {
        return list;
    }

    public void setList(List<VerifyReport> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = context.getLayoutInflater().inflate(layoutId, null);
        if(list.size()<=0)
            return convertView;
        SqLiteHelper sqliteHelper = SqLiteHelper.getInstance(context);
        List<User> users = sqliteHelper.Get_User();
        List<Lab> labs = sqliteHelper.Get_Lab();
        TextView tv_header, tv_shift_time, tv_note, tvGV;
        tv_header = convertView.findViewById(R.id.tvSTT);
        tv_shift_time = convertView.findViewById(R.id.tv_shift_time);
        tv_note = convertView.findViewById(R.id.tv_note);
        tvGV = convertView.findViewById(R.id.tvGV);
        VerifyReport report = list.get(position);
        User gv = null;
        for(User user:users){
            if(user.getId_user() == report.getUser_id()){
                gv = user;
                break;
            }
        }
        Lab pth = null;

        for (Lab lab : labs){
            if(lab.getLab_ID() == report.getLabID())
            {
                pth = lab;
                break;
            }
        }

        if(gv!=null && pth!= null){
            tvGV.setText(pth.getLab_Name()+" - " +gv.getFullName());

        } else tvGV.setText(report.getLabID()+" - " +report.getName());


        String strHeader = "Mã phiếu: MP"+ report.getId() +"";
        tv_header.setText(strHeader);

        String strShiftTime = report.getTime() + " - ";
        if(report.getShift()) // true: ca sáng - false: ca chiều
            strShiftTime += " ca sáng";
        else strShiftTime+= " ca chiều";
        tv_shift_time.setText(strShiftTime);

        tv_note.setText("Ghi chú: " + report.getNote());

        return convertView;
    }
}
