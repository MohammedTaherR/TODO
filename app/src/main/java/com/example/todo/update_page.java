package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class update_page extends AppCompatActivity {
    TextView intentTask,IntentGoal,IntentTime;
    ProgressBar intentProgress;
    dbhandler db= new dbhandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        intentTask=findViewById(R.id.textView6);
        IntentGoal=findViewById(R.id.textView8);
        IntentTime = findViewById(R.id.textView7);
        intentProgress=findViewById(R.id.progressBar1);
        Intent intent= getIntent();
        String intent_taskname= intent.getStringExtra("taskname");
        String intent_time=intent.getStringExtra("time");
        String intent_goal =intent.getStringExtra("Goal");

        IntentTime.setText(intent_time);
IntentGoal.setText(intent_goal);
intentTask.setText(intent_taskname);




    }

}