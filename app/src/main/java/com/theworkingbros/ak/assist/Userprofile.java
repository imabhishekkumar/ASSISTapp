package com.theworkingbros.ak.assist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Userprofile extends AppCompatActivity {
 ImageView avatar;
 Uri imgsrc;
 TextView name;
 Button avtrbtn,save;
 private static final int PICKIMG=100;
 private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        avatar = findViewById(R.id.avatar);
        avtrbtn = findViewById(R.id.avtrbtn);
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs = getSharedPreferences("prefname", 0);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", name.getText().toString());
                edit.commit();
                name.setText(prefs.getString("name", "username"));
                Intent save = new Intent(Userprofile.this, MainActivity.class);
                startActivity(save);



            }

        });




        avtrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectavtr();
            }
        });


    }
    private void selectavtr(){
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICKIMG);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode==PICKIMG){
            imgsrc=data.getData();
            avatar.setImageURI(imgsrc);
        }
    }

}
