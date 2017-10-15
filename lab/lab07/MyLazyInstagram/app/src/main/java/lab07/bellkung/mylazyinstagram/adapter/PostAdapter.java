package lab07.bellkung.mylazyinstagram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import lab07.bellkung.mylazyinstagram.R;
import lab07.bellkung.mylazyinstagram.model.PostsModel;

/**
 * Created by BellKunG on 13/10/2017 AD.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<PostsModel> data;
    private String userImageProfile;
    private String username;
    private int row;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        this.data = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (this.row == 3) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_item, null);
        }
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PostsModel posts = data.get(position);
        Glide.with(this.activity).load(posts.getUrl()).into(holder.imageView);

        if (this.row == 1) {
            holder.imageProfile.setBorderColor(Color.DKGRAY);
            holder.imageProfile.setBorderWidth(1);
            Glide.with(this.activity).load(this.userImageProfile).into(holder.imageProfile);
            holder.listUsername.setText(this.username);
            holder.likedTextView.setText(String.valueOf(posts.getLike()) + " others");
            holder.userPostsTextView.setText(this.username);
            holder.postsCommentTextView.setText("View all " + String.valueOf(posts.getComment()) + " comments");
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setData(List<PostsModel> data) {
        this.data = data;
    }

    public void setUserImageProfile(String userImageProfile) {
        this.userImageProfile = userImageProfile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRow(int row) {
        this.row = row;
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CircularImageView imageProfile;
        TextView listUsername, likedTextView, userPostsTextView, postsCommentTextView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageProfile = itemView.findViewById(R.id.listImageProfile);
            listUsername = itemView.findViewById(R.id.listUserName);
            likedTextView = itemView.findViewById(R.id.likedTextView);
            userPostsTextView = itemView.findViewById(R.id.userPostsTextView);
            postsCommentTextView = itemView.findViewById(R.id.postsCommentTextView);
        }
    }
}
