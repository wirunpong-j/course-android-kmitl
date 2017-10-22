package com.bellkung.espresso.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bellkung.espresso.R;
import com.bellkung.espresso.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BellKunG on 22/10/2017 AD.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SearchResultHolder> {

    private List<UserInfo> data;

    public MyAdapter() {
        this.data = new ArrayList<>();
    }

    public void setData(List<UserInfo> data) {
        this.data = data;
    }

    @Override
    public SearchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_info, null, false);
        return new SearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchResultHolder holder, int position) {
        UserInfo userInfo = data.get(position);
        holder.textName.setText(userInfo.getName());
        holder.textAge.setText(userInfo.getAge());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class SearchResultHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textName)
        public TextView textName;

        @BindView(R.id.textAge)
        public TextView textAge;

        public SearchResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

