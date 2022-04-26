package com.example.todo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.util.Date;
import android.text.format.DateFormat;
import android.widget.Toast;

public class addTaskPage extends AppCompatActivity {
    EditText Taskname,initial,max;
    Spinner goals;
TextView Datetxt,timetxt;
DatePickerDialog.OnDateSetListener setListener;
int t1hour,t1min;
Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_task_page);

      dbhandler db= new dbhandler(addTaskPage.this);
      Taskname= findViewById(R.id.editTextTextPersonName);
      initial= findViewById(R.id.editTextTextPersonName6);
      max=findViewById(R.id.editTextTextPersonName7);
      goals=findViewById(R.id.spinner);
      Datetxt=findViewById(R.id.textView12);
      timetxt=findViewById(R.id.textView14);


      save= findViewById(R.id.button2);
      Calendar calendar= Calendar.getInstance();
      final  int year =     calendar.get(calendar.YEAR);
      final  int month =     calendar.get(calendar.MONTH);
      final  int day =     calendar.get(calendar.DAY_OF_MONTH);

      Datetxt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
DatePickerDialog datePickerDialog = new DatePickerDialog(addTaskPage.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
datePickerDialog.show();
          }
      });
      setListener= new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
              month=month+1;
              String date = dayOfMonth+"/"+month+"/"+year;
              Datetxt.setText(date);

          }
      };
      timetxt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              TimePickerDialog timePickerDialog =  new TimePickerDialog(addTaskPage.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                  @Override
                  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      t1hour=hourOfDay;
                      t1min= minute;
                      String time = t1hour+":"+t1min;
                      SimpleDateFormat  f24Hours =  new SimpleDateFormat("HH:mm");
                      try{
                          Date date= f24Hours.parse(time);
                          SimpleDateFormat f12hours= new SimpleDateFormat("hh:mm aa");
                          timetxt.setText(f12hours.format(date));


                      } catch (ParseException e) {
                          e.printStackTrace();
                      }


                  }
              },12,0,false);
              timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              timePickerDialog.show();

          }
      });



save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(addTaskPage.this, MainActivity.class);
        String DueDate= Datetxt.getText().toString();
        String DueTime=timetxt.getText().toString();
        String task_name= Taskname.getText().toString();
        // String selectedGoal= goals.getSelectedItem().toString();
        String selectedGoal= "";
        String init_val= initial.getText().toString();
        String max_val= max.getText().toString();
        db.addlist(task_name, selectedGoal, DueTime, init_val, max_val, DueDate);
        Log.d("DB",task_name+selectedGoal+DueTime+init_val+max_val+DueDate);
        Toast.makeText(addTaskPage.this, "successfully added", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
});


    }
}