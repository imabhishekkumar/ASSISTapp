package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Data.BlogRecyclerAdapter;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Feed extends AppCompatActivity {
    private DatabaseReference mDatabasereference,mdbref,announceDB;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> bloglist;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    TextView announce;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        announceDB = mDatabase.getReference().child("Announcement");
        announceDB.keepSynced(true);
        mDatabasereference = mDatabase.getReference().child("AssistBlog");
        mDatabasereference.keepSynced(true);
        mdbref=mDatabase.getReference().child("AssistUsers");
        mdbref.keepSynced(true);

        bloglist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        uid=mUser.getUid();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading from database");
        progressDialog.show();


        announceDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                announce.setText(dataSnapshot.child("announce").getValue(String.class));
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

     /*   mdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name= dataSnapshot.child(uid).child("name").getValue(String.class);


                greeting.setText(getString(R.string.welcome)+name);
                about.setText(dataSnapshot.child(uid).child("about").getValue(String.class));
                if(Objects.equals(dataSnapshot.child(uid).child("verified").getValue(String.class), "true"))
                {
                    verified.setVisibility(View.VISIBLE);
                }
                else{
                    verified.setVisibility(View.GONE);
                }

                imgurl=dataSnapshot.child(uid).child("image").getValue(String.class);
                Picasso
                        .with(MainActivity.this)
                        .load(imgurl)
                        .into(avatar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        mDatabasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Blog blog=dataSnapshot.getValue(Blog.class);

                bloglist.add(blog);
                Collections.reverse(bloglist);
                /////////////////////////////////////////////
               /* blogRecyclerAdapterwoimg= new BlogRecyclerAdapterwoimg(MainActivity.this,bloglist);
                recyclerView.setAdapter(blogRecyclerAdapterwoimg);
                blogRecyclerAdapterwoimg.notifyDataSetChanged();*/

                ////////////////////////////////////////////////////

                blogRecyclerAdapter= new BlogRecyclerAdapter(Feed.this,bloglist);
                recyclerView.setAdapter(blogRecyclerAdapter);
                blogRecyclerAdapter.notifyDataSetChanged();



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
    }
}
