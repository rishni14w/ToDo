package com.ctseproject.rishnisewmi.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> adapter;
    ListView lstItems;

    ArrayList<Task> taskList;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DbHelper(this);
        lstItems=(ListView)findViewById(R.id.list_view);


        //showItemList();
        taskList=new ArrayList<Task>();
        Cursor data=dbHelper.getListContents();
        int numRow=data.getCount();
        if(numRow==0)
        {
            Toast.makeText(this,"There is nothing in the database",Toast.LENGTH_LONG).show();
        }
        else
        {

            while (data.moveToNext())
            {
                task=new Task(data.getString(1),data.getString(3));
                taskList.add(task);

                Task_ListAdapter adapter_task=new Task_ListAdapter(this,R.layout.row,taskList);
                lstItems=(ListView)findViewById(R.id.list_view);
                lstItems.setAdapter(adapter_task);
            }
        }
    }

    public void onAddClicked(View view)
    {
        if(view.getId()==R.id.add_task)
        {
            Intent i=new Intent(this,NewTaskActivity.class);
            startActivity(i);
        }
    }


    private void showItemList()
    {
        ArrayList<String> itemList=dbHelper.getToDoList();
        if(adapter==null)
        {
            adapter=new ArrayAdapter<String>(this,R.layout.row,R.id.task_title,itemList);
            lstItems.setAdapter(adapter);
        }
        else
        {
            adapter.clear();
            adapter.addAll(itemList);
            adapter.notifyDataSetChanged();
        }
    }
}
