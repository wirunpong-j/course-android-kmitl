package kmitl.lab03.wirunpong58070126;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
