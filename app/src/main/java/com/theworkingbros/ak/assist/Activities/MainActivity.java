package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    ImageButton cgpa, cal, map, club;
    String name, uid, imgurl;
    private CircleImageView avatar;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> bloglist;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabasereference = mDatabase.getReference().child("AssistBlog");
        mDatabasereference.keepSynced(true);
        DatabaseReference mdbref = mDatabase.getReference().child("AssistUsers");
        mdbref.keepSynced(true);
        avatar = findViewById(R.id.avatarpic);
        cgpa = findViewById(R.id.cgpa);
        map = findViewById(R.id.map);
        bloglist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        club = findViewById(R.id.club);
        cal = findViewById(R.id.cal);
        uid = mUser.getUid();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading from database");
        progressDialog.show();


        mdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child(uid).child("name").getValue(String.class);
                imgurl = dataSnapshot.child(uid).child("image").getValue(String.class);
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(Uri.parse(imgurl))
                        .build();
                mUser.updateProfile(profileUpdates);
                Picasso.get()
                        .load(imgurl)
                        .into(avatar);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Blog blog = dataSnapshot.getValue(Blog.class);
                bloglist.add(blog);
                Collections.reverse(bloglist);
                blogRecyclerAdapter = new BlogRecyclerAdapter(MainActivity.this, bloglist);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                if (mUser != null && mAuth != null) {
                    Intent post = new Intent(MainActivity.this, Addpost.class);
                    startActivity(post);
                    finish();
                }
                break;
            case R.id.action_signout:
                if (mUser != null && mAuth != null) {
                    mAuth.signOut();
                    Intent post = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(post);
                    finish();
                }
                break;
            case R.id.action_changepassword:
                if (mUser != null && mAuth != null) {
                    Intent post = new Intent(MainActivity.this, changePassword.class);
                    startActivity(post);
                    break;
                }

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void clickcgpa(View v) {
        Intent main = new Intent(MainActivity.this, CGPA.class);
        startActivity(main);

    }

    public void clickmap(View v) {
        Intent main = new Intent(MainActivity.this, universitymap.class);
        startActivity(main);

    }

    public void clickclub(View v) {
        Intent main = new Intent(MainActivity.this, club.class);
        startActivity(main);

    }

    public void clickcal(View v) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void resultsbtn(View v) {
        Intent main = new Intent(MainActivity.this, results.class);
        startActivity(main);
    }

    public void dowbtn(View v) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

    }

    public void sublayout(View v) {
        Intent main = new Intent(MainActivity.this, Userprofile.class);
        startActivity(main);

    }

}

