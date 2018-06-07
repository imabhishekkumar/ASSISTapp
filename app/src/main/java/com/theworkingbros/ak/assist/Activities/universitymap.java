package com.theworkingbros.ak.assist.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.theworkingbros.ak.assist.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class universitymap extends AppCompatActivity {
    ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universitymap);
        map=findViewById(R.id.map);

        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(map);
        pAttacher.update();

    }

}
