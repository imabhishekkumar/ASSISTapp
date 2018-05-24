package com.theworkingbros.ak.assist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class club extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        TextView club1, club2,club3,club4,club5,club6;

        club1 = findViewById(R.id.club1);
        club2 = findViewById(R.id.club2);
        club3 = findViewById(R.id.club3);
        club4 = findViewById(R.id.club4);
        club5 = findViewById(R.id.club5);
        club6 = findViewById(R.id.club6);
        club1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",1);
                startActivity(club);
            }
        });
        club2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",2);
                startActivity(club);
            }
        });
        club3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",3);
                startActivity(club);
            }
        });
        club4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",4);
                startActivity(club);
            }
        });
        club5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",5);
                startActivity(club);
            }
        });
        club6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent club = new Intent(club.this, allclubs.class);
                club.putExtra("clubnumb",6);
                startActivity(club);
            }
        });
    }
}