package com.theworkingbros.ak.assist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CourseMaterial extends AppCompatActivity {
   Spinner branchname,semester;
   Button submit;
   TextView check;
   int branchid=0;
   String name,sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_material);
        check= findViewById(R.id.check);
        submit=findViewById(R.id.submit);
        branchname= findViewById(R.id.branchname);
        semester= findViewById(R.id.semester);
        String[] items = new String[]{"Computer Science and Engineering"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        branchname.setAdapter(adapter);
        String[] items2 = new String[]{"1","3","5"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2);
        semester.setAdapter(adapter2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        name= (String) branchname.getSelectedItem();
        sem=(String) semester.getSelectedItem();
            if(name.equals("Computer Science and Engineering"))
                 branchid=1;
                        }

        });
    }
    }