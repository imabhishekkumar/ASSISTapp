package com.theworkingbros.ak.assist.Data;
/*
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.util.Date;
import java.util.List;

public class BlogRecyclerAdapterwoimg {
}
package com.theworkingbros.ak.assist.Data;

     */
        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;
        import com.theworkingbros.ak.assist.Model.Blog;
        import com.theworkingbros.ak.assist.R;

        import java.util.Date;
        import java.util.List;

public class BlogRecyclerAdapterwoimg extends RecyclerView.Adapter<BlogRecyclerAdapterwoimg.ViewHolder> {

    private Context context;
    private List<Blog> blogList;


    public BlogRecyclerAdapterwoimg(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogRecyclerAdapterwoimg.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postrowwoimg,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogRecyclerAdapterwoimg.ViewHolder holder, int position) {
        Blog blog= blogList.get(position);
        holder.title.setText(blog.getTitle());
        holder.desc.setText(blog.getDesc());
        holder.username.setText(blog.getUsername());


        java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
        String formatteddate= dateFormat.format(new Date(Long.valueOf(blog.getTimestamp())).getTime());
        holder.timestamp.setText(formatteddate);



    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView timestamp;
        public TextView username;


        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            title= view.findViewById(R.id.post_titlelistt);
            desc= view.findViewById(R.id.post_text);
            timestamp=view.findViewById(R.id.timestamp);
            username=view.findViewById(R.id.usernameTV);

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

