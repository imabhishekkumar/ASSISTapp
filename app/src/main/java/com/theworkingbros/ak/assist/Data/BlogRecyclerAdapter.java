package com.theworkingbros.ak.assist.Data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Activities.MainActivity;
import com.theworkingbros.ak.assist.Activities.PostView;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.util.List;
import java.util.Objects;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Blog> blogList;
    String uid;
    String user;
    String tit;
    String des;
    String time;
    String uri=null;
    String imgview;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef,MUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;




    public BlogRecyclerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postrow,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final BlogRecyclerAdapter.ViewHolder holder, int position) {
        Blog blog= blogList.get(position);
        String imageUrl=null;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uid=mUser.getUid();
        mDatabase=FirebaseDatabase.getInstance();
        MUserRef=mDatabase.getReference().child("AssistUsers");
        MUserRef.keepSynced(true);
        MUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(Objects.equals(dataSnapshot.child(uid).child("verified").getValue(String.class), "true"))
                {
                    holder.verified.setVisibility(View.VISIBLE);
                }
                else{
                    holder.verified.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        holder.title.setText(blog.getTitle());
        holder.desc.setText(blog.getDesc());
        holder.username.setText(blog.getUsername());
        holder.userid.setText(blog.getUserid());
     
        holder.timestamp.setText(blog.getTimestamp());
       
        imageUrl=blog.getImage();
        if(imageUrl!=null)
        {

        Picasso.with(context)
                .load(imageUrl)
                .into(holder.img);
        uri=imageUrl;}

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public ImageView img;
        public TextView username;
        public  TextView userid;
        public ImageView verified;




        String userID,parent,myParentNode;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            title= view.findViewById(R.id.postview_titlelistt);
            desc= view.findViewById(R.id.post_text);
            img= view.findViewById(R.id.post_imglist);
            timestamp=view.findViewById(R.id.timestamp);
            username=view.findViewById(R.id.usernameTV);
            userid=view.findViewById(R.id.post_userid);
            verified=view.findViewById(R.id.verifiied);




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  
                    mDatabase=FirebaseDatabase.getInstance();
                    mRef=mDatabase.getReference().child("AssistBlog");
                    mRef.keepSynced(true);
                    tit=title.getText().toString();
                    uid=userid.getText().toString();
                    user=username.getText().toString();
                    time=timestamp.getText().toString();
                    des=desc.getText().toString();
                  
                    if (img.getDrawable() != null){
                        imgview=uri;
                    img.setVisibility(View.VISIBLE);}
                  
                    img.setDrawingCacheEnabled(true);
                    Bitmap b=img.getDrawingCache();


                    Intent intent= new Intent(context, PostView.class);
                    intent.putExtra("username",user);
                    intent.putExtra("desc",des);
                    intent.putExtra("title",tit);
                    intent.putExtra("time",time);
                    intent.putExtra("userid",uid);
              
                    context.startActivity(intent);





}

                    });}}}
