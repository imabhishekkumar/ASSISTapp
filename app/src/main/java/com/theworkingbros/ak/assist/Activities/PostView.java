package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.net.URI;

public class PostView extends AppCompatActivity {
    Button deletebtn;
  public   String userid,postid,parent,uniqueid,uid,parentnode,imageUrl;
    TextView title,desc,username,timestamp;
    ImageView image;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef,mReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        deletebtn=findViewById(R.id.deletepostbtn);
        title=findViewById(R.id.postview_titlelist);
        desc=findViewById(R.id.postview_desc);
        mDatabase=FirebaseDatabase.getInstance();
        mAuth= FirebaseAuth.getInstance();
        mRef=mDatabase.getReference().child("AssistBlog");
        username=findViewById(R.id.usernameTV);
        image=findViewById(R.id.post_imglist);
        timestamp=findViewById(R.id.timestamp);
        user=mAuth.getCurrentUser();
        final Blog blog=new  Blog();
         userid=user.getUid();



        Intent intent = getIntent();

        postid=intent.getStringExtra("userid");
        title.setText(intent.getStringExtra("title"));


        username.setText(intent.getStringExtra("username"));


        desc.setText(intent.getStringExtra("desc"));
        uid=intent.getStringExtra("userid");
        timestamp.setText(intent.getStringExtra("time"));
        if(userid.equals(postid)){
            deletebtn.setVisibility(View.VISIBLE);
        }

            uniqueid= uid+intent.getStringExtra("time");
        final Query userQuery = mRef.orderByChild(uniqueid);

        userQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

              parent = dataSnapshot.getKey();

                parentnode=parent;
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    imageUrl = dataSnapshot.child(parent).child("image").getValue(String.class);
                    if(imageUrl!=null) {
                        Uri uri = Uri.parse(imageUrl);

                        image.setVisibility(View.VISIBLE);
                        Picasso.with(PostView.this)
                                .load(uri)
                                .into(image);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mReff=mDatabase.getReference().child("AssistBlog").child(parent);
              //  mRef.keepSynced(true);
                mReff.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent main=new Intent(PostView.this, MainActivity.class);
                        startActivity(main);
                    }
                });

            }
        });

        }


    }

