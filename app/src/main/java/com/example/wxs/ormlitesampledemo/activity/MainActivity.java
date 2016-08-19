package com.example.wxs.ormlitesampledemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wxs.ormlitesampledemo.R;

public class MainActivity extends Activity implements View.OnClickListener{


    private Button addClassBtn;
    private Button addStuBtn;
    private Button queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addClassBtn = (Button)findViewById(R.id.add_class);
        addStuBtn = (Button)findViewById(R.id.add_student);
        queryBtn = (Button)findViewById(R.id.query_btn);

        addClassBtn.setOnClickListener(this);
        addStuBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_class:
                startActivity(new Intent(MainActivity.this,AddClassActivity.class));
                break;

            case R.id.add_student:
                startActivity(new Intent(MainActivity.this,AddStuActivity.class));
                break;

            case R.id.query_btn:
                startActivity(new Intent(MainActivity.this,QueryActivity.class));
                break;
        }
    }
}
