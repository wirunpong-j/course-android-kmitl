package com.bellkung.moneyflow.adapter;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bellkung.moneyflow.R;
import com.bellkung.moneyflow.fragment.ManageListDialogFragment;
import com.bellkung.moneyflow.model.ListModel;
import com.bellkung.moneyflow.utils.KeyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by BellKunG on 8/11/2017 AD.
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Activity mActivity;
    private List<ListModel> allList;
    private ListViewAdapterListener listener;

    public interface ListViewAdapterListener {
        void onListViewAdapterListener(ListModel list);
    }

    public ListViewAdapter(Activity activity) {
        this.mActivity = activity;
        this.allList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        ListModel item = this.allList.get(position);
        viewHolder.symbol.setText(item.getSymbol());
        viewHolder.detailTextView.setText(item.getDetail());
        viewHolder.amountTextView.setText(String.valueOf(item.getAmount()));

        switch (item.getSymbol()) {
            case KeyUtils.ACTION_INCOME:
                viewHolder.symbol.setTextColor(this.mActivity.getResources().getColor(R.color.colorGreen));
                viewHolder.detailTextView.setTextColor(this.mActivity.getResources().getColor(R.color.colorGreen));
                viewHolder.amountTextView.setTextColor(this.mActivity.getResources().getColor(R.color.colorGreen));
                break;
            case KeyUtils.ACTION_EXPENSE:
                viewHolder.symbol.setTextColor(this.mActivity.getResources().getColor(R.color.colorRed));
                viewHolder.detailTextView.setTextColor(this.mActivity.getResources().getColor(R.color.colorRed));
                viewHolder.amountTextView.setTextColor(this.mActivity.getResources().getColor(R.color.colorRed));
                break;
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListViewAdapterListener(allList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.allList.size();
    }

    public void setAllList(List<ListModel> allList) {
        this.allList = allList;
    }

    public void setListener(ListViewAdapterListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.symbol)
        TextView symbol;
        @BindView(R.id.detailTextView)
        TextView detailTextView;
        @BindView(R.id.amountTextView)
        TextView amountTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}