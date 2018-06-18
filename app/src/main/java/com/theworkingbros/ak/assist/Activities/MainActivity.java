package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    ImageButton cgpa,cal,map,club;
    ImageView verified;
    TextView greeting,about,announce;
    String name,uid,imgurl;
    CircleImageView avatar;

    private DatabaseReference mDatabasereference,mdbref,announceDB;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private BlogRecyclerAdapter blogRecyclerAdapter;
    private List<Blog> bloglist;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
  //  private BlogRecyclerAdapterwoimg blogRecyclerAdapterwoimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        announce=findViewById(R.id.announce);
        mDatabase = FirebaseDatabase.getInstance();
        announceDB = mDatabase.getReference().child("Announcement");
        announceDB.keepSynced(true);
        mDatabasereference = mDatabase.getReference().child("AssistBlog");
        mDatabasereference.keepSynced(true);

        mdbref=mDatabase.getReference().child("AssistUsers");
        mdbref.keepSynced(true);
        avatar=findViewById(R.id.avatarpic);
        cgpa = findViewById(R.id.cgpa);
        map = findViewById(R.id.map);
        verified=findViewById(R.id.verify);
        bloglist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        club = findViewById(R.id.club);
        cal = findViewById(R.id.cal);
        greeting = findViewById(R.id.greeting);
        uid=mUser.getUid();
        about = findViewById(R.id.about);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading from database");
        progressDialog.show();
        //progressDialog.dismiss();
       // final Blog blog=new Blog();
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

        mdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name= dataSnapshot.child(uid).child("name").getValue(String.class);


                greeting.setText(getString(R.string.welcome)+name);
                about.setText(dataSnapshot.child(uid).child("about").getValue(String.class));
                if(Objects.equals(dataSnapshot.child(uid).child("verified").getValue(String.class), "true"))
                {
                    verified.setVisibility(View.VISIBLE);
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
        });



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

                blogRecyclerAdapter= new BlogRecyclerAdapter(MainActivity.this,bloglist);
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


        //////////////////////////////////////////////////////
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
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
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onResume() {

        super.onResume();
    }
    @Override
    protected void onStart() {
        super.onStart();
    /*    FirebaseRecyclerAdapter<MainActivity,BlogViewHolder> FBRA= new FirebaseRecyclerAdapter<MainActivity, BlogViewHolder>(
                MainActivity.class,
                R.layout.postrow,
                BlogViewHolder.class,
                mDatabasereference
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, MainActivity model, int position) {
                viewHolder.setTitle(model.getTitle());

            }
        };
        recyclerView.setAdapter(FBRA);*/
/*
        mDatabasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Blog blog=dataSnapshot.getValue(Blog.class);

                bloglist.add(blog);
                Collections.reverse(bloglist);
                /////////////////////////////////////////////
                blogRecyclerAdapterwoimg= new BlogRecyclerAdapterwoimg(MainActivity.this,bloglist);
                recyclerView.setAdapter(blogRecyclerAdapterwoimg);
                blogRecyclerAdapterwoimg.notifyDataSetChanged();

                ////////////////////////////////////////////////////

                blogRecyclerAdapter= new BlogRecyclerAdapter(MainActivity.this,bloglist);
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
        });*/
        }

    public void clickcgpa(View v){
        Intent main=new Intent(MainActivity.this,CGPA.class);
        startActivity(main);

    }
    public void clickmap(View v)
    {
        Intent main=new Intent(MainActivity.this,universitymap.class);
        startActivity(main);

    }
    public void clickclub(View v)
    {
        Intent main=new Intent(MainActivity.this,club.class);
        startActivity(main);

    }
    public void clickcal(View v)
    {
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }

    public void libbtn(View v)
    {Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
       /* Intent main=new Intent(MainActivity.this,library.class);
        startActivity(main);*/
    }
    public void dowbtn(View v)
    { /*Intent main=new Intent(MainActivity.this,CourseMaterial.class );
      startActivity(main);*/

        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();

    }

    public void sublayout(View v)
    {
        Intent main=new Intent(MainActivity.this,Userprofile.class);
        startActivity(main);

    }/*
  public static class BlogViewHolder extends RecyclerView.ViewHolder{
        public BlogViewHolder(View itemView){
            super(itemView);
            View mView=itemView;
        }
        public void setTitle(String s){
            TextView posttitle=itemView.findViewById(R.id.postview_titlelistt);
            posttitle.setText(s);
        }
      public void setDesc(String s){
          TextView postdesc=itemView.findViewById(R.id.post_text);
          postdesc.setText(s);
      }



    }*/

    }

