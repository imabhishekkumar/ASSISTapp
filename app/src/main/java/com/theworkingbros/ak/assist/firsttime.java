package com.theworkingbros.ak.assist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class firsttime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsttime);
        SharedPreferences dataSave = getSharedPreferences("firstLog", 0);

        if(dataSave.getString("firstTime", "").toString().equals("no")){
            Intent first=new Intent(firsttime.this,MainActivity.class);
            startActivity(first);
        }
        else{
            SharedPreferences.Editor editor = dataSave.edit();
            editor.putString("firstTime", "no");
            editor.commit();
            Intent first=new Intent(firsttime.this,Userprofile.class);
            startActivity(first);
        }
    }
}
