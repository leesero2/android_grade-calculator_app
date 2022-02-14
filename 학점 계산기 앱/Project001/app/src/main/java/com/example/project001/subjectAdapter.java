package com.example.project001;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class subjectAdapter extends BaseAdapter {
    private ArrayList<subject> subjects = new ArrayList<>();
    Context context;
    LayoutInflater inflacter;
    int layout;
    public  subjectAdapter(Context context, int layout){
        this.context = context;
        inflacter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflacter.inflate(layout,parent,false);
        }

        TextView tv_name = (TextView)convertView.findViewById(R.id.tv_name);
        tv_name.setText(subjects.get(position).sName);

        TextView tv_score = (TextView)convertView.findViewById(R.id.tv_score);
        tv_score.setText(subjects.get(position).sScore);

        return convertView;
    }

    public  void addSubject(subject subject){
        subjects.add(subject);
    }
}
