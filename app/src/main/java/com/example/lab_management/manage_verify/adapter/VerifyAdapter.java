package com.example.lab_management.manage_verify.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
        LinearLayout l_background = convertView.findViewById(R.id.ln_background);
        if(position%2==0)
            l_background.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        else l_background.setBackgroundColor(context.getResources().getColor(R.color.white));
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
            tvGV.setText("Ph??ng: " + pth.getLab_Name()+" \t Gi???ng vi??n: " +gv.getFullName());

        } else tvGV.setText(report.getLabID()+" - " +report.getName());


        String strHeader = "M?? phi???u: MP"+ report.getId() +"";
        tv_header.setText(strHeader);

        String strShiftTime = report.getTime() + " - ";
        if(report.getShift()) // true: ca s??ng - false: ca chi???u
            strShiftTime += "\t ca s??ng";
        else strShiftTime+= "\t ca chi???u";
        tv_shift_time.setText("Th???i gian: " + strShiftTime);

        tv_note.setText("Ghi ch??: " + report.getNote());

        return convertView;
    }
}
