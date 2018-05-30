package com.theworkingbros.ak.assist.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.util.Date;
import java.util.List;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Blog> blogList;
    ////
    //FirebaseDatabase database =FirebaseDatabase.getInstance();
    //DatabaseReference mRef= database.getReference();

    /////////
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

        java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
        String formatteddate= dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())).getTime());
        holder.timestamp.setText(formatteddate);
        imageUrl=blog.getImage();
        //TODO: Use picassa library
        Picasso.with(context)
                .load(imageUrl)
                .into(holder.img);

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
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            title= view.findViewById(R.id.post_titlelist);
            desc= view.findViewById(R.id.post_text);
            img= view.findViewById(R.id.post_imglist);
            timestamp=view.findViewById(R.id.timestamp);
            userid=null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next activity
                    ////



                    ////
                }
            });
        }
    }
}
