package com.bellkung.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bellkung.moneyflow.adapter.ListViewAdapter;
import com.bellkung.moneyflow.database.ListDatabase;
import com.bellkung.moneyflow.fragment.ManageListDialogFragment;
import com.bellkung.moneyflow.model.ListModel;
import com.bellkung.moneyflow.utils.KeyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class MainActivity extends AppCompatActivity implements ManageListDialogFragment.DialogFragmentListener,
        ListViewAdapter.ListViewAdapterListener {

    @BindView(R.id.balanceTextView) TextView balanceTextView;
    @BindView(R.id.listRecycleView) RecyclerView listRecycleView;

    private ListDatabase listDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listDatabase = Room.databaseBuilder(getApplicationContext(),
                ListDatabase.class, KeyUtils.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        ButterKnife.bind(this);
        updateUI();
    }

    private void updateUI() {
        List<ListModel> allList = this.listDatabase.listRoomDAO().getAll();

        ListViewAdapter adapter = new ListViewAdapter(this);
        adapter.setListener(this);
        adapter.setAllList(allList);

        this.listRecycleView.setLayoutManager(new GridLayoutManager(this, KeyUtils.ROW_ITEM));
        this.listRecycleView.setAdapter(adapter);

        setBalanceColor();
    }

    private void setBalanceColor() {
        double expense = this.listDatabase.listRoomDAO().getExpense();
        double income = this.listDatabase.listRoomDAO().getIncome();
        double summary = income - expense;

        if (summary > income * 0.5) {
            this.balanceTextView.setTextColor(getResources().getColor(R.color.colorGreen));
        } else if (summary > income * 0.25 && summary <= income * 0.5) {
            this.balanceTextView.setTextColor(getResources().getColor(R.color.colorYellow));
        } else {
            this.balanceTextView.setTextColor(getResources().getColor(R.color.colorRed));
        }

        this.balanceTextView.setText(String.valueOf(summary));
    }

    @OnClick(R.id.addListBtn)
    public void addListBtnPressed(View view) {
        ManageListDialogFragment manageFragment = ManageListDialogFragment.newInstance(
                new ListModel(KeyUtils.ACTION_DEFAULT, KeyUtils.DETAIL_DEFAULT, KeyUtils.AMOUNT_DEFAULT), KeyUtils.STATUS_ADD);
        manageFragment.setListener(this);
        manageFragment.show(getSupportFragmentManager(), KeyUtils.TAG);
        updateUI();
    }

    @Override
    public void onSaveDataListener(ListModel list) {
        this.listDatabase.listRoomDAO().insert(list);
        updateUI();
    }

    @Override
    public void onEditDataListener(ListModel list) {
        this.listDatabase.listRoomDAO().update(list);
        updateUI();
    }

    @Override
    public void onDeleteDataListener(ListModel list) {
        this.listDatabase.listRoomDAO().delete(list);
        updateUI();
    }


    @Override
    public void onListViewAdapterListener(ListModel list) {
        ManageListDialogFragment manageFragment = ManageListDialogFragment.newInstance(list, KeyUtils.STATUS_EDIT);
        manageFragment.setListener(this);
        manageFragment.show(getSupportFragmentManager(), KeyUtils.TAG);
        updateUI();
    }
}
