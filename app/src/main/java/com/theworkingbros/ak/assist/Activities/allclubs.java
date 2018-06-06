package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.theworkingbros.ak.assist.R;

public class allclubs extends AppCompatActivity {
TextView title,president,contact;
ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allclubs);
        title= findViewById(R.id.title);
        president=findViewById(R.id.president);
        contact=findViewById(R.id.contact);
        logo=findViewById(R.id.logo);

        Intent club = getIntent();
        Integer numb = club.getIntExtra("clubnumb",0);

        if(numb==1)
        {
            title.setText(R.string.hardware);
            logo.setBackgroundResource(R.drawable.hardwareclub);
            president.setText(R.string.hardwarepres);
            contact.setText(R.string.hardwarecont);
        }
        else if(numb==2)
        {
            title.setText(R.string.gamiconname);
            logo.setBackgroundResource(R.drawable.joystick);
            president.setText(R.string.gamiconpres);
            contact.setText(R.string.gamiconcontact);
        }
        else if(numb==3)
        {
            title.setText(R.string.codingtitle);
            logo.setBackgroundResource(R.drawable.codingclublogo);
            president.setText(R.string.saif);
            contact.setText(R.string.contsaif);
        }
        else if(numb==4)
        {
            title.setText(R.string.msctitle);
            logo.setBackgroundResource(R.drawable.msc);
            president.setText(R.string.mscpres);
            contact.setText(R.string.msccontact);
        }
        else if(numb==5)
        {
            title.setText(R.string.dsctitle);
            logo.setBackgroundResource(R.drawable.dsc);
            president.setText(R.string.saif);
            contact.setText(R.string.contsaif);
        }
        else if(numb==6)
        {
            title.setText(R.string.cogtitle);
            logo.setBackgroundResource(R.drawable.nopreview);
            president.setText(R.string.saif);
            contact.setText(R.string.contsaif);
        }
    }
}
