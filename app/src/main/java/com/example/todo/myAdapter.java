package com.example.todo;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class myAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private  final ArrayList<String> TaskName;
    private final  ArrayList<String> Goal;
    private final ArrayList<String> time;
private final ArrayList<String> P_init;
private  final  ArrayList<String> P_max;

    public myAdapter(Activity context, ArrayList<String> taskName, ArrayList<String> goal, ArrayList<String> time, ArrayList<String> p_init, ArrayList<String> p_max) {
        super(context, R.layout.mylayout);
        this.context = context;
        TaskName = taskName;
        Goal = goal;
        this.time = time;
        P_init = p_init;
        P_max = p_max;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        dbhandler db = new dbhandler(getContext());
        View rowView = inflater.inflate(R.layout.mylayout, null, true);
        TextView tw1 = (TextView) rowView.findViewById(R.id.textView3);//Taskname
        TextView tw2 = (TextView) rowView.findViewById(R.id.textView4);//Timev
        ProgressBar p1= (ProgressBar)rowView.findViewById(R.id.progressBar);//progressbar
        TextView tw3 = (TextView) rowView.findViewById(R.id.textView5);//Goal

String  T_name= TaskName.get(position);
tw1.setText(T_name);
String Time=time.get(position);
tw2.setText(Time);
String G_Task= Goal.get(position);
tw3.setText(G_Task);
p1.setMax(Integer.parseInt(P_max.get(position)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            p1.setMin(Integer.parseInt(P_init.get(position)));
        }


        return rowView;
    }
}
