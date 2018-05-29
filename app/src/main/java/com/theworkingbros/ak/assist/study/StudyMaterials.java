package com.theworkingbros.ak.assist.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theworkingbros.ak.assist.R;

public class StudyMaterials extends AppCompatActivity {
    private static FirebaseDatabase db;
    private static DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymaterial);
    db= FirebaseDatabase.getInstance();
    ref=db.getReference("message");
    ref.setValue("Hello,World");

    }
}
