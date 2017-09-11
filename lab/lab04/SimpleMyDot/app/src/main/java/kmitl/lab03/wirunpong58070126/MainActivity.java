package kmitl.lab03.wirunpong58070126;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.wirunpong58070126.model.Dot;
import kmitl.lab03.wirunpong58070126.model.Dots;
import kmitl.lab03.wirunpong58070126.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.onDotsChangeListener, DotView.OnDotViewPressListener {

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


}
