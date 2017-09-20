package kmitl.lab03.wirunpong58070126.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import kmitl.lab03.wirunpong58070126.model.Dot;
import kmitl.lab03.wirunpong58070126.model.Dots;


public class DotView extends View {

    private Paint paint;
    private Dots dots;
    private OnDotViewPressListener onDotViewPressListener;

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();}

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.dots != null) {
            for (Dot dot: dots.getAllDot()) {
                if (dot != null) {
                    paint.setARGB(255, dot.getColor_r(), dot.getColor_g(), dot.getColor_b());
                    canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
                }
            }
        }

    }

    private final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onDotViewPressListener.onDotViewPressed((int) e.getX(), (int) e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onDotViewPressListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
            super.onLongPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y);
        void onDotViewLongPressed(int x, int y);
    }

    public void setOnDotViewPressListener(OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    public void setDots(Dots dots) {
        this.dots = dots;
    }
}
