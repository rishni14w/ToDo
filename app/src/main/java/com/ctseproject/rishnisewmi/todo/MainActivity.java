package com.ctseproject.rishnisewmi.todo;

import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> adapter;
    ListView lstItems;

    ArrayList<Task> taskList;
    Task task;

    FloatingActionButton fab;
    CheckedTextView checkedTextView;
    Bundle b;
    private final static String TAG="Lifecycle_watch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DbHelper(this);
        lstItems=(ListView)findViewById(R.id.list_view);


        //showItemList
        taskList=new ArrayList<Task>();
        Cursor data=dbHelper.getListContents();
        int numRow=data.getCount();
        if(numRow==0)
        {
            Toast.makeText(this,"There is nothing in the database",Toast.LENGTH_LONG).show();
        }
        else
        {

            if(data.moveToLast())
            {
                do{
                    task=new Task(data.getString(1),data.getString(3));
                    taskList.add(task);

                    Task_ListAdapter adapter_task=new Task_ListAdapter(this,R.layout.listview_row_layout,taskList);
                    lstItems=(ListView)findViewById(R.id.list_view);
                    lstItems.setAdapter(adapter_task);
                }
                while (data.moveToPrevious());

            }
        }

        //fab
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.fab)
                {
                    Intent i= new Intent(MainActivity.this,NewTaskActivity.class);
                    startActivity(i);
                }
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

    /**@Override
    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        b.putBoolean("checkbox",checkedTextView.isChecked());
    }**/

    public  void itemClicked(View view)
    {
        CheckedTextView checkedTextView=(CheckedTextView)view;
        if(!checkedTextView.isChecked())
        {
            checkedTextView.setChecked(true);

            /**switch (view.getId())
            {
                case R.id.checkbox:
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("checkbox",true).commit();
                    /**SharedPreferences Preference=getSharedPreferences("Pref", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=Preference.edit();
                    editor.putBoolean("checkbox",true);
                    editor.commit(); **/
                   /** break;
            }**/
        }
        else
        {
            checkedTextView.setChecked(false);
        }
    }


}
