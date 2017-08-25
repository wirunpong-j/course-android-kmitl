package kmitl.lab03.wirunpong58070126;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRandomDot(View view) {
        Random rd = new Random();
        int centerX = rd.nextInt(500);
        int centerY = rd.nextInt(500);

        TextView x = (TextView) findViewById(R.id.randomNum);
        x.setText("X : " + centerX + ", Y : " + centerY);

    }
}
