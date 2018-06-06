package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.theworkingbros.ak.assist.R;
import com.theworkingbros.ak.assist.download.subjects;

public class CourseMaterial extends AppCompatActivity {
   Spinner branchname,semester;
   Button submit;
   TextView check;
   int branchid=0;
   String link;
   String name,sem;
   subjects obj= new subjects();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_material);


        submit=findViewById(R.id.submitt);
        branchname= findViewById(R.id.branchname);
        semester= findViewById(R.id.semester);
        String[] items = new String[]{"Computer Science and Engineering"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        branchname.setAdapter(adapter);
        String[] items2 = new String[]{"1","2","3","4"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2);
        semester.setAdapter(adapter2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        name= (String) branchname.getSelectedItem();
        sem=(String) semester.getSelectedItem();
            if(name.equals("Computer Science and Engineering"))
                 branchid=1;

              link=obj.download(branchid,Integer.parseInt(sem));
                Uri uriUrl = Uri.parse(link);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                        }

        });

    }
    }