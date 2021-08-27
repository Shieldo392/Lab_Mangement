package com.example.lab_management.manage_labs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab_management.R;
import com.example.lab_management.objects.Lab;

import java.util.List;

public class LabAdapter extends BaseAdapter {

    Context context;
    List<Lab> labs;

    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }

    public LabAdapter(Context context, List<Lab> labs) {
        this.context = context;
        this.labs = labs;
    }

    @Override
    public int getCount() {
        return labs.size();
    }

    @Override
    public Object getItem(int position) {
        return labs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_lab, parent, false);

        Lab lab = labs.get(position);
        TextView tv_LabName = convertView.findViewById(R.id.tv_labName);
        TextView tv_area = convertView.findViewById(R.id.tv_area);
        TextView tv_floor = convertView.findViewById(R.id.tv_floor);
        TextView tv_note = convertView.findViewById(R.id.tv_note);

        tv_LabName.setText(lab.getLab_Name());
        if(!lab.getKhuNha().isEmpty())
            tv_area.setText(lab.getKhuNha());
        else tv_area.setText("Không có thông tin");

        if(!lab.getTang().isEmpty())
            tv_floor.setText(lab.getTang());
        else tv_floor.setText("Không có thông tin");
        tv_note.setText(lab.getGhiChu());

        return convertView;
    }
}
