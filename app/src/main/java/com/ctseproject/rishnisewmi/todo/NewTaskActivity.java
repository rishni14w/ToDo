package com.ctseproject.rishnisewmi.todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    //private Button btnAdd; //save button
    private EditText editText;
    private EditText editText_desc;
    private EditText editText_date;
    private MenuItem menuITEM;

    Calendar myCalendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.newTaskTitle);
        setContentView(R.layout.activity_new_task);

        editText=findViewById(R.id.new_task_txt);
        editText_desc=findViewById(R.id.new_descrip_txt);
        editText_date=findViewById(R.id.new_date_txt);
        //btnAdd=findViewById(R.id.btn_save); //save btn
        dbHelperr=new DbHelper(this);
        menuITEM=findViewById(R.id.save);

        //datepicker
        date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        editText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewTaskActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public void updateLabel()
    {
        String format="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
        editText_date.setText(sdf.format(myCalendar.getTime()));
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
            toastMessage("success");
            Intent intent=new Intent(NewTaskActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else {
            toastMessage("fail");
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String newEntry=editText.getText().toString();
        String newDesc=editText_desc.getText().toString();
        String date=editText_date.getText().toString();
        if (editText.length()!=0)
        {
            AddData(newEntry,newDesc,date);
        }
        else {
            toastMessage("error");
        }
        return super.onOptionsItemSelected(item);
    }

}
