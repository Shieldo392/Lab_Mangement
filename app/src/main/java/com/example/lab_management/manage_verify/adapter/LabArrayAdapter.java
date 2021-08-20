package com.example.lab_management.manage_verify.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab_management.R;
import com.example.lab_management.objects.Lab;


import java.util.List;

public class LabArrayAdapter extends BaseAdapter {
    Context mContext;
    int layoutID;
    List<Lab> labs;

    public LabArrayAdapter(Context mContext, int layoutID, List<Lab> labs) {
        this.mContext = mContext;
        this.layoutID = layoutID;
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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutID, parent, false);

        TextView tv_lab_name = view.findViewById(R.id.tv_labName);
        tv_lab_name.setText(labs.get(position).getLab_Name());

        return view;
    }
}