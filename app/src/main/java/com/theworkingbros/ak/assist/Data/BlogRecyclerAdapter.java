package com.theworkingbros.ak.assist.Data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Activities.PostView;
import com.theworkingbros.ak.assist.Activities.PostViewwoimg;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

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
    DatabaseReference mRef;





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
    public void onBindViewHolder(@NonNull BlogRecyclerAdapter.ViewHolder holder, int position) {
        Blog blog= blogList.get(position);
        String imageUrl=null;
        holder.title.setText(blog.getTitle());
        holder.desc.setText(blog.getDesc());
        holder.username.setText(blog.getUsername());
        holder.userid.setText(blog.getUserid());


     //   java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
       // String formatteddate= dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())).getTime());
        holder.timestamp.setText(blog.getTimestamp());
       // time=formatteddate;
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




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next activity
                    ////
                    mDatabase=FirebaseDatabase.getInstance();
                    mRef=mDatabase.getReference().child("AssistBlog");
                    mRef.keepSynced(true);
                    tit=title.getText().toString();
                    uid=userid.getText().toString();
                    user=username.getText().toString();
                    time=timestamp.getText().toString();
                    des=desc.getText().toString();
                    //drawable= img.getDrawable();
                    if (img.getDrawable() != null){
                        imgview=uri;
                    img.setVisibility(View.VISIBLE);}
                   /* final String uniqueid= uid+time;
                    final Query userQuery = mRef.orderByChild(uniqueid);

                    userQuery.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            parent = dataSnapshot.getKey();

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
                    img.setDrawingCacheEnabled(true);
                    Bitmap b=img.getDrawingCache();


                    Intent intent= new Intent(context, PostView.class);
                    intent.putExtra("username",user);
                    intent.putExtra("desc",des);
                    intent.putExtra("title",tit);
                    intent.putExtra("time",time);
                    intent.putExtra("userid",uid);
                  //  intent.putExtra("parent",parent);
                   // intent.putExtra("Bitmap",b);

                  // if(uri!=null)
                       // intent.putExtra("img",uri);

                  //  intent.putExtra("parent1",uniqueid);
                    context.startActivity(intent);





}

                    });}}}
