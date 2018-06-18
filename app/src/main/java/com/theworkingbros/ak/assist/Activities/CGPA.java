package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.theworkingbros.ak.assist.R;


public class CGPA extends AppCompatActivity {
    EditText s1, s2, s3, s4, s5, s6, s7, s8, g1, g2, g3, g4, g5, g6, g7, g8, s9, g9; //Declaring the variables of EditText widget
    TextView cgpa; //Declaring the variable for TextView widget
    Button submit;// Declaring the variable for Button widget
    Spinner sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9;
    int i,check=0,less=0;
    Double sp[] = new Double[10];
    Double sub[] = new Double[10];
    Double gr[] = new Double[10];
    Double fin[] = new Double[10];
    Double res = 0.0, sumg = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgp);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
        s7 = findViewById(R.id.s7);
        s8 = findViewById(R.id.s8);
        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        g6 = findViewById(R.id.g6);
        g7 = findViewById(R.id.g7);
        g8 = findViewById(R.id.g8);
        s9 = findViewById(R.id.s9);
        g9 = findViewById(R.id.g9);
        submit = findViewById(R.id.submitt);
        cgpa = findViewById(R.id.cgpa);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);
        sp4 = findViewById(R.id.sp4);
        sp5 = findViewById(R.id.sp5);
        sp6 = findViewById(R.id.sp6);
        sp7 = findViewById(R.id.sp7);
        sp8 = findViewById(R.id.sp8);
        sp9 = findViewById(R.id.sp9);





        String[] items = new String[]{"100", "50"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);

        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);
        sp3.setAdapter(adapter);
        sp4.setAdapter(adapter);
        sp5.setAdapter(adapter);
        sp6.setAdapter(adapter);
        sp7.setAdapter(adapter);
        sp8.setAdapter(adapter);
        sp9.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (i = 1; i <= 9; i++) {
                    sub[i] = 0.0;
                    gr[i] = 0.0;
                    fin[i] = 0.0;
                    sp[i] = 0.0;

                }
                res = 0.0;
                sumg = 0.0;
                check=0;
                less=0;

                try {
                    sub[1] = Double.parseDouble(s1.getText().toString());
                    sub[2] = Double.parseDouble(s2.getText().toString());
                    sub[3] = Double.parseDouble(s3.getText().toString());
                    sub[4] = Double.parseDouble(s4.getText().toString());
                    sub[5] = Double.parseDouble(s5.getText().toString());
                    sub[6] = Double.parseDouble(s6.getText().toString());
                    sub[7] = Double.parseDouble(s7.getText().toString());
                    sub[8] = Double.parseDouble(s8.getText().toString());
                    sub[9] = Double.parseDouble(s9.getText().toString());
                    gr[1] = Double.parseDouble(g1.getText().toString());
                    gr[2] = Double.parseDouble(g2.getText().toString());
                    gr[3] = Double.parseDouble(g3.getText().toString());
                    gr[4] = Double.parseDouble(g4.getText().toString());
                    gr[5] = Double.parseDouble(g5.getText().toString());
                    gr[6] = Double.parseDouble(g6.getText().toString());
                    gr[7] = Double.parseDouble(g7.getText().toString());
                    gr[8] = Double.parseDouble(g8.getText().toString());
                    gr[9] = Double.parseDouble(g9.getText().toString());
                    sp[1] = Double.valueOf(String.valueOf(sp1.getSelectedItem()));
                    sp[2] = Double.valueOf(String.valueOf(sp2.getSelectedItem()));
                    sp[3] = Double.valueOf(String.valueOf(sp3.getSelectedItem()));
                    sp[4] = Double.valueOf(String.valueOf(sp4.getSelectedItem()));
                    sp[5] = Double.valueOf(String.valueOf(sp5.getSelectedItem()));
                    sp[6] = Double.valueOf(String.valueOf(sp6.getSelectedItem()));
                    sp[7] = Double.valueOf(String.valueOf(sp7.getSelectedItem()));
                    sp[8] = Double.valueOf(String.valueOf(sp8.getSelectedItem()));
                    sp[9] = Double.valueOf(String.valueOf(sp9.getSelectedItem()));


                    for (i = 1; i <= 9; i++) {

                        if ((sub[i] > 100) || (gr[i] > 6)) {
                            check = 1;
                            break;
                        }
                        if(sub[i]>sp[i])
                        {less=1;}
                        if (sp[i] == 100) {

                            if (sub[i] >= 90 && sub[i] <= 100)
                                fin[i] = 10.0;
                            else if (sub[i] >= 80 && sub[i] < 90)
                                fin[i] = 9.0;
                            else if (sub[i] >= 70 && sub[i] < 80)
                                fin[i] = 8.0;
                            else if (sub[i] >= 60 && sub[i] < 70)
                                fin[i] = 7.0;
                            else if (sub[i] >= 50 && sub[i] < 60)
                                fin[i] = 6.0;
                            else
                                fin[i] = 5.0; // which is fail

                        } else if (sp[i] == 50) {


                            if (sub[i] >= 45 && sub[i] <= 50)
                                fin[i] = 10.0;
                            else if (sub[i] >= 40 && sub[i] < 45)
                                fin[i] = 9.0;
                            else if (sub[i] >= 35 && sub[i] < 40)
                                fin[i] = 8.0;
                            else if (sub[i] >= 30 && sub[i] < 35)
                                fin[i] = 7.0;
                            else if (sub[i] >= 25 && sub[i] < 30)
                                fin[i] = 6.0;
                            else
                                fin[i] = 5.0; // which is fail

                        }

                        sumg = sumg + gr[i]; // total grade point in a semester
                    }

                    for (i = 1; i <= 9; i++) {

                        if (fin[i] != 5.0)
                            res = res + (fin[i] * gr[i]);
                    }
                    if(less==1)
                        cgpa.setText(R.string.lessthan);

                    else if (check == 1) {
                        cgpa.setText(R.string.range);
                    } else if (check == 0) {
                        cgpa.setText(String.valueOf(res / sumg));
                    }



                }
                catch (NumberFormatException e) {
                    cgpa.setText(R.string.invalid);
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(CGPA.this,MainActivity.class));
        super.onBackPressed();
    }
}










