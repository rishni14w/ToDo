package com.ctseproject.rishnisewmi.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.homeTitle);
        setContentView(R.layout.activity_home);
    }

    public void newTaskBtnClicked(View view)
    {
        if(view.getId()==R.id.newtaskbtn)
        {
            Intent i=new Intent(this,NewTaskActivity.class);
            startActivity(i);
        }
    }
}
