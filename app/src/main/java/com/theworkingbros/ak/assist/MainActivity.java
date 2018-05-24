package com.theworkingbros.ak.assist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
ImageButton cgpa,cal,map,club;
TextView greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cgpa= findViewById(R.id.cgpa);
        map=findViewById(R.id.map);
        club=findViewById(R.id.club);
        cal=findViewById(R.id.cal);
        greeting=findViewById(R.id.greeting);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
          greeting.setText(R.string.morning);
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greeting.setText(R.string.afternoon);
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            greeting.setText(R.string.Evening);
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            greeting.setText(R.string.Night);
        }
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
        Intent main=new Intent(MainActivity.this,club.class);
        startActivity(main);
    }
    public void clickcal(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }

    public void libbtn(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }
    public void dowbtn(View v)
    { Intent main=new Intent(MainActivity.this,CourseMaterial.class );
      startActivity(main);



    }
}
