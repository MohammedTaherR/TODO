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
int p_min=0;

    ArrayList<String> time= new ArrayList<>();
    ArrayList<String> task_name=new ArrayList<>();
    ArrayList<String> goal_name=new ArrayList<>();
    ArrayList<String> p_max=new ArrayList<>();
    ArrayList<String> date= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
listView= findViewById(R.id.listView);
        add = findViewById(R.id.addTask);
        myAdapter ad = new myAdapter(MainActivity.this,task_name,goal_name,time,p_max);
        listView.setAdapter(ad);

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
                Intent intent= new Intent(MainActivity.this,update_page.class);

                intent.putExtra("taskname", task_name.get(position));
                intent.putExtra("time",time.get(position));
                intent.putExtra("Goal",goal_name.get(position));
                intent.putExtra("Progressmin",p_min);
                intent.putExtra("Progressmax",p_max);
                intent.putExtra("date",date);





                startActivity(intent);
                Toast.makeText(MainActivity.this, "You can change your Task here", Toast.LENGTH_SHORT).show();
            }
        });

    }
}