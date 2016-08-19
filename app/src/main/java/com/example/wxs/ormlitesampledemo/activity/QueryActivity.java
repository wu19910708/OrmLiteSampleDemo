package com.example.wxs.ormlitesampledemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wxs.ormlitesampledemo.R;

import java.sql.SQLException;

import dao.ClassDao;
import model.ClassModel;

/**
 * Created by wxs on 16/8/19.
 */
public class QueryActivity extends Activity implements View.OnClickListener{

    private Button query_class_btn;
    private TextView class_info_tv;
    private EditText class_id_edit;
    private Button query_stu_btn;
    private TextView student_info_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        query_class_btn = (Button)findViewById(R.id.query_class_btn);
        class_info_tv = (TextView)findViewById(R.id.class_info_tv);
        class_id_edit = (EditText)findViewById(R.id.class_id_edit);
        query_stu_btn = (Button)findViewById(R.id.query_stu_btn);
        student_info_tv = (TextView)findViewById(R.id.student_info_tv);

        query_class_btn.setOnClickListener(this);
        query_stu_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_class_btn:
                try {
                    StringBuffer sb = new StringBuffer();
                    for(ClassModel classModel : ClassDao.getInstance(QueryActivity.this).queryAll()){
                        sb.append(classModel.toString()).append(",");
                    }
                    class_info_tv.setText(sb.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.query_stu_btn:

                break;
        }
    }
}
