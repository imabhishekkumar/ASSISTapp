package com.theworkingbros.ak.assist.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

public class PostViewwoimg extends AppCompatActivity {
    Button deletebtn;
    String userid,postid;
    TextView title,desc;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_viewwoimg);
        deletebtn=findViewById(R.id.deletepostbtn);
        title=findViewById(R.id.postview_titlelist);
        desc=findViewById(R.id.postview_desc);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        userid=user.getUid();

        mRef=mDatabase.getReference().child("AssistBlog");
        mRef.keepSynced(true);

        if(userid==postid){
            deletebtn.setVisibility(View.VISIBLE);
        }
        title.setText(userid);
        desc.setText(postid);


    }
}
