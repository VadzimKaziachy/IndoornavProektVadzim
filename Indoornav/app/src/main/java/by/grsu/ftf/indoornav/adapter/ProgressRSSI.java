package by.grsu.ftf.indoornav.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

/**
 * Created by Вадим on 04.11.2017.
 */

public class ProgressRSSI extends View {


    private int angleRSSI;
    private int start;
    private int stop;
    private int x;
    private int y;
    private Drawable tachometer;
    private Drawable arrow;

    public ProgressRSSI(Context context) {
        this(context, null);
    }

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        arrow = getResources().getDrawable(R.drawable.arrow);
        tachometer = getResources().getDrawable(R.drawable.tachometer);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        tachometer.draw(canvas);
        canvas.rotate(angleRSSI, x, y);
        arrow.draw(canvas);
    }

    public void setRSSI(Float progressRSSI) {
        stop = Math.round(240 * progressRSSI);
        ValueAnimator animator = ValueAnimator.ofInt(start, stop);
        animator.setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                angleRSSI = (int) animation.getAnimatedValue() - 30;
                invalidate();
            }
        });
        animator.start();
        start = stop;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width = right - left;
        int height = bottom - top;
        x = width / 2;
        y = height / 2;

        int min = Math.min(width, height);
        int leftTachometer = ((right - left) / 2) - min / 2;
        int rightTachometer = ((right - left) / 2) + min / 2;
        int bottomTachometer = min;
        tachometer.setBounds(leftTachometer, 0, rightTachometer, bottomTachometer);

        int leftArrow = (width / 2) - min / 4;
        int rightArrow = (width / 2) + min / 4;
        int topArrow = (min / 2) - ((rightArrow - leftArrow) / 8);
        int bottomArrow = (min / 2) + ((rightArrow - leftArrow) / 8);
        arrow.setBounds(leftArrow, topArrow, rightArrow, bottomArrow);


    }
}