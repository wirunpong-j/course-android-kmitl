package kmitl.lab03.wirunpong58070126;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.wirunpong58070126.model.Dot;
import kmitl.lab03.wirunpong58070126.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(0, 0, 50);
        dot.setDotChangedListener(this);

        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random rd = new Random();
        dot.setCenterX(rd.nextInt(dotView.getWidth()));
        dot.setCenterY(rd.nextInt(dotView.getHeight()));
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
