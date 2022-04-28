package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add;
ListView listView;


    ArrayList<String> time= new ArrayList<>();
    ArrayList<String> task_name=new ArrayList<>();
    ArrayList<String> goal_name=new ArrayList<>();
    ArrayList<String> p_max=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
listView= findViewById(R.id.listView);
        add = findViewById(R.id.addTask);
//
        myAdapter ad = new myAdapter(MainActivity.this,task_name,goal_name,time,p_max);
        listView.setAdapter(ad);
//        ArrayAdapter ad= new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,time);
//        listView.setAdapter(ad);
        dbhandler db = new dbhandler(MainActivity.this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addTaskPage.class);
                startActivity(intent);
            }
        });

        Cursor res = db.getdata();
        while (res.moveToNext()) {
            task_name.add(res.getString(0));
            goal_name.add(res.getString(1));
        time.add(res.getString(2));
            p_max.add(res.getString(4));
        ad.notifyDataSetChanged();
        }
// onclick to show delete or increment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You can change your Task here", Toast.LENGTH_SHORT).show();
            }
        });

    }
}