package lab07.bellkung.mylazyinstagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

    public PostAdapter(Activity activity) {
        this.activity = activity;
        this.data = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(this.activity).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setData(List<PostsModel> data) {
        this.data = data;
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
