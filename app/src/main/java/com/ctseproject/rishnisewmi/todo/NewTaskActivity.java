package com.ctseproject.rishnisewmi.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

    DbHelper dbHelperr;
    //private Button btnAdd; //save button
    private EditText editText;
    private EditText editText_desc;
    private MenuItem menuITEM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.newTaskTitle);
        setContentView(R.layout.activity_new_task);

        editText=findViewById(R.id.new_task_txt);
        editText_desc=findViewById(R.id.new_descrip_txt);
        //btnAdd=findViewById(R.id.btn_save); //save btn
        dbHelperr=new DbHelper(this);
        menuITEM=findViewById(R.id.save);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void AddData(String newEntry,String newDesc)
    {
        boolean insertData=dbHelperr.insertNewTask(newEntry,newDesc);
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
        if (editText.length()!=0)
        {
            AddData(newEntry,newDesc);
        }
        else {
            toastMessage("error");
        }
        return super.onOptionsItemSelected(item);
    }

}
