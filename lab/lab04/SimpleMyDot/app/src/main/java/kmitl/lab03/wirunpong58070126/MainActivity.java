package kmitl.lab03.wirunpong58070126;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.wirunpong58070126.model.Dot;
import kmitl.lab03.wirunpong58070126.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    private Dot dot;
    private DotView dotView;
    private ArrayList<Dot> allDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allDot = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Random rd = new Random();
                    int radius = rd.nextInt(70) + 30;

                    if (!checkDotOnboard((int) motionEvent.getX(), (int) motionEvent.getY(), radius)) {
                        createDot((int) motionEvent.getX(), (int) motionEvent.getY(), radius);
                    }

                }

                return true;
            }
        });
    }

    public boolean checkDotOnboard(int x, int y, int r) {
        for (Dot dot: allDot) {
            double distance = Math.pow(Math.pow(dot.getCenterX() - x, 2) + Math.pow(dot.getCenterY() - y, 2), 0.5);
            if (distance <= r) {
                allDot.remove(dot);
                dotView.invalidate();
                return true;
            }
        }
        return false;
    }

    public void createDot(int x, int y, int r) {
        dot = new Dot(this, x, y, r);

        Random rd = new Random();
        dot.setColor_r(rd.nextInt(256));
        dot.setColor_g(rd.nextInt(256));
        dot.setColor_b(rd.nextInt(256));
        allDot.add(dot);
    }

    public void onRandomDot(View view) {
        Random rd = new Random();
        dot = new Dot(this, rd.nextInt(dotView.getWidth()), rd.nextInt(dotView.getHeight()), rd.nextInt(70) + 30);
        dot.setColor_r(rd.nextInt(256));
        dot.setColor_g(rd.nextInt(256));
        dot.setColor_b(rd.nextInt(256));
        allDot.add(dot);
    }

    public void onClearDot(View view) {
        allDot.clear();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setAllDot(allDot);
        dotView.invalidate();
    }




}
