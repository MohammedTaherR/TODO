package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class update_page extends AppCompatActivity {
    TextView intentTask,IntentGoal,IntentTime;
    ProgressBar intentProgress;
    dbhandler db= new dbhandler(this);
    Button back,add,minus,delete,update;

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
back= findViewById(R.id.button);
add=findViewById(R.id.button3);
minus=findViewById(R.id.button4);
update=findViewById(R.id.button5);
delete=findViewById(R.id.button6);

delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        db.delete(intent_taskname);
        Intent i = new Intent(update_page.this,MainActivity.class);
        startActivity(i);
    }
});
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent update_text= new Intent(update_page.this,update_text_page.class);
        startActivity(update_text);
    }
});
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


    }
});

minus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});


    }

}