package com.theworkingbros.ak.assist.Data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.util.List;


public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder> {
private  Context context;
private List<Blog> blogList;
    public ReplyAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }
    @NonNull
    @Override
    public ReplyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.replies,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyAdapter.ViewHolder holder, int position) {
        Blog blog=blogList.get(position);
        holder.name.setText(blog.getUsername());
        holder.timestamp.setText(blog.getTimestamp());

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView reply;
        public TextView timestamp;
        public ViewHolder(View view, Context ctx) {
            super(view);
            context=ctx;
            name=view.findViewById(R.id.replyusernameTV);
            reply=view.findViewById(R.id.reply);
            timestamp=view.findViewById(R.id.replytime);
        }
    }
}
