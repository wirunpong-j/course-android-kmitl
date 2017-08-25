package kmitl.lab03.wirunpong58070126.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import kmitl.lab03.wirunpong58070126.model.Dot;


public class DotView extends View {

    private Paint paint;
    private ArrayList<Dot> allDot;

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (allDot != null) {
            for (Dot dot: allDot) {
                if (dot != null) {
                    paint.setARGB(255, dot.getColor_r(), dot.getColor_g(), dot.getColor_b());
                    canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
                }
            }
        }

    }

    public void setAllDot(ArrayList<Dot> allDot) {
        this.allDot = allDot;
    }
}
