package kmitl.lab03.wirunpong58070126.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import kmitl.lab03.wirunpong58070126.R;

/**
 * Created by BellKunG on 9/20/2017 AD.
 */

public class EditFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private int dotView_height;
    private int dotView_width;

    private int dot_x;
    private int dot_y;
    private int dot_radius;

    private int dot_color_r;
    private int dot_color_g;
    private int dot_color_b;

    private SeekBar editDotSizeSeek;
    private SeekBar editPosXSeek;
    private SeekBar editPosYSeek;

    private TextView editDotSizeTextView;
    private TextView editPosXTextView;
    private TextView editPosYTextView;

    private Button changeColorBtn;

    private ConfirmDialog confirmDialog;

    public interface ConfirmDialog {
        void onConfirmChanged(int x, int y, int radius, int color_r, int color_g, int color_b);
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

        changeColorBtn = view.findViewById(R.id.changeColorBtn);
        changeColorBtn.setOnClickListener(this);


        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit Dot")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmDialog.onConfirmChanged(editPosXSeek.getProgress(), editPosYSeek.getProgress(), editDotSizeSeek.getProgress(),
                                dot_color_r, dot_color_g, dot_color_b);
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
    public void onClick(View view) {
        ColorPickerDialogBuilder
                .with(view.getContext())
                .setTitle("Choose Color")
                .initialColor(Color.rgb(dot_color_r, dot_color_g, dot_color_b))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        dot_color_r = Color.red(selectedColor);
                        dot_color_g = Color.green(selectedColor);
                        dot_color_b = Color.blue(selectedColor);

                        confirmDialog.onConfirmChanged(editPosXSeek.getProgress(), editPosYSeek.getProgress(), editDotSizeSeek.getProgress(),
                                dot_color_r, dot_color_g, dot_color_b);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setDotView_height(int dotView_height) {
        this.dotView_height = dotView_height;
    }

    public void setDotView_width(int dotView_width) {
        this.dotView_width = dotView_width;
    }

    public void setDot_x(int dot_x) {
        this.dot_x = dot_x;
    }


    public void setDot_y(int dot_y) {
        this.dot_y = dot_y;
    }

    public void setDot_radius(int dot_radius) {
        this.dot_radius = dot_radius;
    }

    public void setConfirmDialog(ConfirmDialog confirmDialog) {
        this.confirmDialog = confirmDialog;
    }

    public void setDot_color_r(int dot_color_r) {
        this.dot_color_r = dot_color_r;
    }

    public void setDot_color_g(int dot_color_g) {
        this.dot_color_g = dot_color_g;
    }


    public void setDot_color_b(int dot_color_b) {
        this.dot_color_b = dot_color_b;
    }
}
