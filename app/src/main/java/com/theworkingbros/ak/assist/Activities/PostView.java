package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.theworkingbros.ak.assist.Data.BlogRecyclerAdapter;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PostView extends AppCompatActivity {
    Button deletebtn,replybtn;
    ProgressDialog mProgress;
  public   String userid,postid,parent,uniqueid,uid,parentnode,imageUrl,replyusername,uniquekey;
    TextView title,desc,username,timestamp,replyuser,replytext;
    ImageView image,verified;
    FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private List<Blog> bloglist;
    FirebaseUser user;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef,mReff,MUserRef,mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        deletebtn=findViewById(R.id.deletepostbtn);
        title=findViewById(R.id.postview_titlelist);
        desc=findViewById(R.id.postview_desc);
        bloglist = new ArrayList<>();
        mDatabase=FirebaseDatabase.getInstance();
        verified=findViewById(R.id.verifiied);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth= FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mRef=mDatabase.getReference().child("AssistBlog");

        username=findViewById(R.id.usernameTV);
        replyuser=findViewById(R.id.replyusernameTV);
        image=findViewById(R.id.post_imglist);
        replytext=findViewById(R.id.reply);
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
                //timestamp.setText(parent);




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
                uniquekey=dataSnapshot.child("uniquekey").getValue(String.class);
                String parentnode=uniquekey;
               // mReply=mDatabase.getReference().child("AssistBlog").child(parentnode);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



////////////////////




        MUserRef=mDatabase.getReference().child("AssistUsers");
        MUserRef.keepSynced(true);
        MUserRef.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               replyuser.setText(dataSnapshot.child(uid).child("name").getValue(String.class));
                                               if (Objects.equals(dataSnapshot.child(uid).child("verified").getValue(String.class), "true")) {
                                                   verified.setVisibility(View.VISIBLE);
                                               } else {
                                                   verified.setVisibility(View.GONE);
                                               }
                                               replyusername=dataSnapshot.child(uid).child("name").getValue(String.class);

                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       });

            /////////////////////////







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


replybtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mReply=mDatabase.getReference().child("AssistBlog").child(parent);
        addreply();



    }
});

        }


    private void addreply() {
        mProgress.setMessage("Posting..");
        mProgress.show();

        final String reply = replytext.getText().toString().trim();


        if (!TextUtils.isEmpty(reply)) {
           // mReff=mDatabase.getReference().child("AssistBlog").child(parent);
            DatabaseReference newPost = mReply.push();
            Map<String, String> dataToSave = new HashMap<>();
            //  java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            //System.out.println();
            String formatteddate = sdf.format(resultdate);
            final String uniqueid = (user.getUid() + formatteddate);
            dataToSave.put("reply", reply);
            dataToSave.put("uniquekey", uniquekey);
            dataToSave.put("timestamp", formatteddate);//String.valueOf(java.lang.System.currentTimeMillis()));
            dataToSave.put("userid", user.getUid());
            dataToSave.put("username", replyusername);
            dataToSave.put(uniqueid, uniqueid);
            newPost.setValue(dataToSave);
            mProgress.dismiss();
            finish();

        }

}
    @Override
    protected void onStart() {
        super.onStart();
       // mReply=mDatabase.getReference().child("AssistBlog").child(parent);
        mReply.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Blog blog=dataSnapshot.getValue(Blog.class);
                bloglist.add(blog);
                Collections.reverse(bloglist);




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

    }}

