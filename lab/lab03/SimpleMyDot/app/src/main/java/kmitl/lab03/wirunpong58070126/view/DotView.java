package kmitl.lab03.wirunpong58070126.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab03.wirunpong58070126.model.Dot;


public class DotView extends View {

    private Paint paint;
    private Dot dot;

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
        if (dot != null) {
            paint.setColor(Color.RED);
            canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
        }
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }
}
