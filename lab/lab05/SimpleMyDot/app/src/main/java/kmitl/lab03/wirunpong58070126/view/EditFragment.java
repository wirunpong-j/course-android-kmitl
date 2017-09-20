package kmitl.lab03.wirunpong58070126.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import kmitl.lab03.wirunpong58070126.R;

/**
 * Created by BellKunG on 9/20/2017 AD.
 */

public class EditFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

    private int dotView_height;
    private int dotView_width;

    private int dot_x;
    private int dot_y;
    private int dot_radius;

    private SeekBar editDotSizeSeek;
    private SeekBar editPosXSeek;
    private SeekBar editPosYSeek;

    private TextView editDotSizeTextView;
    private TextView editPosXTextView;
    private TextView editPosYTextView;

    private ConfirmDialog confirmDialog;

    public interface ConfirmDialog {
        void onConfirmChanged(int x, int y, int radius);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_layout, null);

        editDotSizeSeek = view.findViewById(R.id.editDotSizeSeek);
        editPosXSeek = view.findViewById(R.id.editPosXSeek);
        editPosYSeek = view.findViewById(R.id.editPosYSeek);

        editDotSizeSeek.setMax(100);
        editPosXSeek.setMax(dotView_width);
        editPosYSeek.setMax(dotView_height);
        editDotSizeSeek.setProgress(dot_radius);
        editPosXSeek.setProgress(dot_x);
        editPosYSeek.setProgress(dot_y);

        editDotSizeSeek.setOnSeekBarChangeListener(this);
        editPosXSeek.setOnSeekBarChangeListener(this);
        editPosYSeek.setOnSeekBarChangeListener(this);

        editDotSizeTextView = view.findViewById(R.id.dotSizeTextView);
        editPosXTextView = view.findViewById(R.id.editPosXTextView);
        editPosYTextView = view.findViewById(R.id.editPosYTextView);

        editDotSizeTextView.setText(String.valueOf(editDotSizeSeek.getProgress()));
        editPosXTextView.setText(String.valueOf(editPosXSeek.getProgress()));
        editPosYTextView.setText(String.valueOf(editPosYSeek.getProgress()));


        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit Dot")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmDialog.onConfirmChanged(editPosXSeek.getProgress(), editPosYSeek.getProgress(), editDotSizeSeek.getProgress());
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.editDotSizeSeek:
                editDotSizeTextView.setText(String.valueOf(seekBar.getProgress()));
                break;
            case R.id.editPosXSeek:
                editPosXTextView.setText(String.valueOf(seekBar.getProgress()));
                break;
            case R.id.editPosYSeek:
                editPosYTextView.setText(String.valueOf(seekBar.getProgress()));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public int getDotView_height() {
        return dotView_height;
    }

    public void setDotView_height(int dotView_height) {
        this.dotView_height = dotView_height;
    }

    public int getDotView_width() {
        return dotView_width;
    }

    public void setDotView_width(int dotView_width) {
        this.dotView_width = dotView_width;
    }

    public int getDot_x() {
        return dot_x;
    }

    public void setDot_x(int dot_x) {
        this.dot_x = dot_x;
    }

    public int getDot_y() {
        return dot_y;
    }

    public void setDot_y(int dot_y) {
        this.dot_y = dot_y;
    }

    public int getDot_radius() {
        return dot_radius;
    }

    public void setDot_radius(int dot_radius) {
        this.dot_radius = dot_radius;
    }

    public void setConfirmDialog(ConfirmDialog confirmDialog) {
        this.confirmDialog = confirmDialog;
    }
}
