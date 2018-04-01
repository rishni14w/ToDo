package com.ctseproject.rishnisewmi.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Task_ListAdapter extends ArrayAdapter<Task> {
    private LayoutInflater mInflater;
    private ArrayList<Task> tasks;
    private int viewResourceId;

    public Task_ListAdapter(Context context, int textViewResourceId, ArrayList<Task> tasks)
    {
        super(context,textViewResourceId,tasks);
        this.tasks=tasks;
        mInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId=textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents)
    {
        convertView=mInflater.inflate(viewResourceId,null);

        Task task=tasks.get(position);

        if(task!=null)
        {
            TextView taskTitle=(TextView)convertView.findViewById(R.id.task_title);
            TextView date=(TextView)convertView.findViewById(R.id.date_display);

            if(taskTitle!=null)
            {
                taskTitle.setText(task.getTask());
            }
            if(date!=null)
            {
                date.setText(task.getDate());
            }

        }
        return convertView;
    }
}
