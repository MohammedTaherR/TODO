package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add;
    RecyclerView mRecyclerview;
    myAdapter adapter;
    dbhandler db = new dbhandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerView);
        add = findViewById(R.id.addTask);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addTaskPage.class);
                startActivity(intent);

            }
        });


        //  mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //Binds the adapter with recyclerview


    }
}