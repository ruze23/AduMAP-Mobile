package com.neva.final_adumap.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neva.final_adumap.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PostHolder>{

    private Context context;
    private List<posts> PostsList;

    public Adapter(Context context, List<posts> posts){
        this.context = context;
        PostsList = posts;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_forum, parent, false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        posts Post = PostsList.get(position);
        holder.user.setText(Post.getName());
        holder.date.setText(Post.getDate());
        holder.content.setText(Post.getContent());
    }

    @Override
    public int getItemCount() {
        return PostsList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
            TextView user, date, content;
            public PostHolder(@NonNull View itemView) {
                super(itemView);

                user = itemView.findViewById(R.id.PostUser);
                date = itemView.findViewById(R.id.PostDate);
                content = itemView.findViewById(R.id.PostContent);
            }
        }
}
