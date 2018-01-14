package by.grsu.ftf.indoornav.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
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

    private Bitmap tachometer, tachometer1;
    private Bitmap arrow;
    private Matrix matrix;
    private int angleRSSI;
    private int start;
    private int stop;
    private int x;
    private int y;

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ProgressRSSI, 0, 0);
        typedArray.recycle();

        matrix = new Matrix();
        tachometer = BitmapFactory.decodeResource(getResources(), R.drawable.tachometer);
        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(tachometer1, 0, 0, null);

        matrix.setTranslate(x, y);
        matrix.preRotate(angleRSSI, arrow.getWidth() / 2, arrow.getHeight() / 2);
        canvas.drawBitmap(arrow, matrix, null);
    }

    public void setRSSI(Float progressRSSI) {
        stop = Math.round(240 * progressRSSI);
        ValueAnimator animator = ValueAnimator.ofInt(start, stop);
        animator.setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                angleRSSI = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
        start = stop;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        tachometer1 = Bitmap.createScaledBitmap(tachometer, Math.min(width, height), Math.min(width, height), true);

        x = (Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2) - arrow.getWidth() / 2;
        y = (Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2) - arrow.getHeight() / 2;
    }
}