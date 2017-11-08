package com.bellkung.moneyflow.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bellkung.moneyflow.R;
import com.bellkung.moneyflow.model.ListModel;
import com.bellkung.moneyflow.utils.KeyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BellKunG on 8/11/2017 AD.
 */

public class ManageListDialogFragment extends DialogFragment {

    private ListModel mList;
    private DialogFragmentListener listener;
    private String action;

    private String status;

    @BindView(R.id.incomeBtn) Button incomeBtn;
    @BindView(R.id.expenseBtn) Button expenseBtn;
    @BindView(R.id.detailTextEdit) EditText detailTextEdit;
    @BindView(R.id.amountTextEdit) EditText amountTextEdit;

    public interface DialogFragmentListener {
        void onSaveDataListener(ListModel list);
        void onEditDataListener(ListModel list);
        void onDeleteDataListener(ListModel list);
    }


    public static ManageListDialogFragment newInstance(ListModel list, String status) {
        Bundle args = new Bundle();
        args.putParcelable(KeyUtils.KEY_LIST, list);
        args.putString(KeyUtils.ACTION_STATUS, status);
        ManageListDialogFragment fragment = new ManageListDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mList = getArguments().getParcelable(KeyUtils.KEY_LIST);
        this.status = getArguments().getString(KeyUtils.ACTION_STATUS);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_manage_list_dialog, null);
        ButterKnife.bind(this, view);

        switch (this.status) {
            case KeyUtils.STATUS_ADD:
                    builder.setView(view)
                        .setPositiveButton(KeyUtils.SAVE_BTN, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListModel list = new ListModel(action, String.valueOf(detailTextEdit.getText()),
                                        Double.parseDouble(String.valueOf(amountTextEdit.getText())));
                                listener.onSaveDataListener(list);
                            }
                        })
                        .setNegativeButton(KeyUtils.CANCEL_BTN, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                break;

            case KeyUtils.STATUS_EDIT:
                builder.setView(view)
                        .setPositiveButton(KeyUtils.SAVE_BTN, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mList.setSymbol(action);
                                mList.setDetail(String.valueOf(detailTextEdit.getText()));
                                mList.setAmount(Double.parseDouble(String.valueOf(amountTextEdit.getText())));
                                listener.onEditDataListener(mList);
                            }
                        })
                        .setNegativeButton(KeyUtils.DELETE_BTN, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeleteDataListener(mList);
                            }
                        });
                break;
        }


        setupView();

        return builder.create();
    }

    private void setupView() {
        switch (this.status) {

            case KeyUtils.STATUS_ADD:
                incomeBtnPressed();
                break;

            case KeyUtils.STATUS_EDIT:

                switch(this.mList.getSymbol()) {
                    case KeyUtils.ACTION_INCOME:
                        incomeBtnPressed();
                        break;
                    case KeyUtils.ACTION_EXPENSE:
                        expenseBtnPressed();
                }
        }

        detailTextEdit.setText(mList.getDetail());
        amountTextEdit.setText(String.valueOf(mList.getAmount()));
    }

    public void setListener(DialogFragmentListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.incomeBtn)
    public void incomeBtnPressed() {
        this.action = KeyUtils.ACTION_INCOME;
        this.incomeBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLIMEGREEN)));
        this.incomeBtn.setTextColor(getResources().getColor(R.color.colorWHITESMOKE));
        this.expenseBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGHOSTWHITE)));
        this.expenseBtn.setTextColor(getResources().getColor(R.color.colorFUCHSIA));
    }

    @OnClick(R.id.expenseBtn)
    public void expenseBtnPressed() {
        this.action = KeyUtils.ACTION_EXPENSE;
        this.expenseBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorCRIMSON)));
        this.expenseBtn.setTextColor(getResources().getColor(R.color.colorWHITESMOKE));
        this.incomeBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGHOSTWHITE)));
        this.incomeBtn.setTextColor(getResources().getColor(R.color.colorFUCHSIA));
    }
}
