package by.grsu.ftf.indoornav.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

/**
 * Created by Вадим on 04.11.2017.
 */

public class ProgressRSSI extends View {

    private Bitmap tachometer, tachometer1;
    private Bitmap arrow, arrow1;
    private Matrix matrix;
    private int angleRSSI;
    private int start;
    private int stop;
    private int x, xTachometer;
    private int y, yTachometer;
    private Drawable drawable;

    public ProgressRSSI(Context context) {
        this(context, null);
    }

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        matrix = new Matrix();
        tachometer = BitmapFactory.decodeResource(getResources(), R.drawable.tachometer);
        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrowpro);
        drawable = getResources().getDrawable(R.drawable.tachometer);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(tachometer1, xTachometer, yTachometer, null);

        matrix.setTranslate(x, y);
        matrix.preRotate(angleRSSI - 30, arrow1.getWidth() / 2, arrow1.getHeight() / 2);
        canvas.drawBitmap(arrow1, matrix, null);
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
        tachometer1 = Bitmap.createScaledBitmap(tachometer, Math.min(getMeasuredWidth(), getMeasuredHeight()), Math.min(getMeasuredWidth(), getMeasuredHeight()), true);
        arrow1 = Bitmap.createScaledBitmap(arrow, Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2, Math.min(getMeasuredWidth(), getMeasuredHeight()) / 8, true);

        xTachometer = width / 2 - tachometer1.getWidth() / 2;
        yTachometer = height / 2 - tachometer1.getHeight() / 2;

        x = (width / 2) - arrow1.getWidth() / 2;
        y = (height / 2) - arrow1.getHeight() / 2;
    }
}