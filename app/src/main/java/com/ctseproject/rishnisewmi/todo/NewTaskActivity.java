package com.ctseproject.rishnisewmi.todo;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewTaskActivity extends AppCompatActivity {

    DbHelper dbHelperr;

    private EditText editText;
    private EditText editText_desc;
    private EditText editText_date;

    private MenuItem menuItem1;
    private MenuItem menuItem2;

    Calendar calendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    Validation validate=new Validation();

    private final static String TAG="Lifecycle_watch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.newTaskTitle);

        setContentView(R.layout.activity_new_task);


        editText=findViewById(R.id.new_task_txt);
        editText_desc=findViewById(R.id.new_descrip_txt);
        editText_date=findViewById(R.id.new_date_txt);

        dbHelperr=new DbHelper(this);

        menuItem1=findViewById(R.id.cancel);
        menuItem2=findViewById(R.id.save);

        //datepicker
        date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        editText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewTaskActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Lifecycle Event: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Lifecycle Event: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Lifecycle Event: onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Lifecycle Event: onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Lifecycle Event: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Lifecycle Event: onDestroy");
    }

    public void updateLabel()
    {
        String format="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
        editText_date.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void AddData(String newEntry,String newDesc,String date)
    {
        boolean insertData=dbHelperr.insertNewTask(newEntry,newDesc,date);
        if (insertData)
        {
            //toastMessage("success");

            AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Message").setMessage("Successfully created a new task").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(NewTaskActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }).create();
            dialog.show();

        }
        else {
            //toastMessage("fail");

            AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Error").setMessage("New Task Not created").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(NewTaskActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }).create();
            dialog.show();
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.save)
        {
            String newTask=editText.getText().toString();
            String newDesc=editText_desc.getText().toString();
            String newDate=editText_date.getText().toString();

            if(!validate.isEmpty(newTask))
            {
                AddData(newTask,newDesc,newDate);
            }
            else {
                //toastMessage("error");
                AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter task name").setPositiveButton("OK",null).create();
                dialog.show();
            }
        }
        else
        {
            Intent intent=new Intent(NewTaskActivity.this,MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
