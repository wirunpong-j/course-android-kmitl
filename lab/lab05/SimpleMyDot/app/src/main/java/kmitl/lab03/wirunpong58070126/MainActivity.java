package kmitl.lab03.wirunpong58070126;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.wirunpong58070126.model.Dot;
import kmitl.lab03.wirunpong58070126.model.Dots;
import kmitl.lab03.wirunpong58070126.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.onDotsChangeListener, DotView.OnDotViewPressListener, ContainerFragment.OnFragmentInteractionListener {

    private DotView dotView;
    private Dots dots;
    private Random rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);
    }

    private boolean requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            return false;
        }
        return true;
    }

    private Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        Bitmap screenShot = Bitmap.createBitmap(screenView.getWidth(), screenView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenShot);
        screenView.draw(canvas);

        return screenShot;

    }

    public Uri getImageURI(Context inContext, Bitmap bm) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), bm, "Title", null);

        return Uri.parse(path);
    }

    private void shareImage(Bitmap bm) {
        Uri bmURI = getImageURI(this.getApplicationContext(), bm);
        shareMenu(bmURI);
    }

    private void shareMenu(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Image"));
    }

    @Override
    public void onDotViewPressed(int x, int y) {

        int position = dots.findDot(x, y);
        if (position != -1) {
            dots.removeByPosition(position);
        } else {
            rd = new Random();
            Dot dot = new Dot(x, y, rd.nextInt(70) + 30);
            dot.setColor_r(rd.nextInt(256));
            dot.setColor_g(rd.nextInt(256));
            dot.setColor_b(rd.nextInt(256));
            dots.addDot(dot);
        }

    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        Toast.makeText(this, "Bell KunG", Toast.LENGTH_SHORT).show();
    }

    public void onRandomDot(View view) {
        rd = new Random();
        Dot dot = new Dot(rd.nextInt(dotView.getWidth()), rd.nextInt(dotView.getHeight()), rd.nextInt(70) + 30);
        dot.setColor_r(rd.nextInt(256));
        dot.setColor_g(rd.nextInt(256));
        dot.setColor_b(rd.nextInt(256));
        dots.addDot(dot);
    }

    public void onClearDot(View view) {
        dots.clearAllDot();
        dotView.invalidate();
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }


    public void onShareBtnPressed(View view) {
        if (requestPermission()) {
            shareImage(getScreenShot(this.dotView.getRootView()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareImage(getScreenShot(this.dotView.getRootView()));
                }
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
