package com.workingbros.ak.assist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageButton cgpa,cal,map,club;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cgpa= findViewById(R.id.cgpa);
        map=findViewById(R.id.map);
        club=findViewById(R.id.club);
        cal=findViewById(R.id.cal);


    }
    public void clickcgpa(View v){
        Intent main=new Intent(MainActivity.this,CGPA.class);
        startActivity(main);
    }
    public void clickmap(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }
    public void clickclub(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }
    public void clickcal(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }
}
