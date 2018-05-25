package com.theworkingbros.ak.assist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
ImageButton cgpa,cal,map,club;
android.support.constraint.ConstraintLayout sublayout;
TextView greeting;
String studentname,greet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref= getSharedPreferences("prefname",0);

        studentname = pref.getString("name", null);

        sublayout=findViewById(R.id.sublayout);
        cgpa= findViewById(R.id.cgpa);
        map=findViewById(R.id.map);
        club=findViewById(R.id.club);
        cal=findViewById(R.id.cal);
        greeting=findViewById(R.id.greeting);
        Calendar c = Calendar.getInstance();
        //int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(studentname!=null) {
          /*  if (timeOfDay >= 0 && timeOfDay < 12) {
                greet = "Good Morning";
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                greet = "Good Afternoon";
            } else if (timeOfDay >= 16 && timeOfDay < 21) {
                greet = "Good Evening";
            } else if (timeOfDay >= 21 && timeOfDay < 24) {
                greet = "Good Night";
            }*/
            greeting.setText("Hello,"+studentname);
        }
         else
        {
            /*if(timeOfDay >= 0 && timeOfDay < 12){
                greet="Good Morning "+ stname;
            }else if(timeOfDay >= 12 && timeOfDay < 16){
                greet="Good Afternoon "+ stname;
            }else if(timeOfDay >= 16 && timeOfDay < 21){
                greet="Good Evening "+ stname;
            }else if(timeOfDay >= 21 && timeOfDay < 24){
                greet="Good Night "+ stname;
            }*/
            greeting.setText("Hello!");
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
    public void clicklayout(View v){
        Intent main=new Intent(MainActivity.this,Userprofile.class);
        startActivity(main);

    }


    }

