package com.example.lab_management.manage_verify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab_management.R;
import com.example.lab_management.objects.User;


import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context context;
    int layoutID;
    List<User> users;

    public UserAdapter(Context context, int layoutID, List<User> users) {
        this.context = context;
        this.layoutID = layoutID;
        this.users = users;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutID, parent, false);

        TextView tv_lab_name = view.findViewById(R.id.tv_labName);
        tv_lab_name.setText(users.get(position).getFullName());

        return view;
    }
}
