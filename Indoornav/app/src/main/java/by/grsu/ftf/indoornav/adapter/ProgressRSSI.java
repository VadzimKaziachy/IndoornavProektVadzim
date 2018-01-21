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
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

/**
 * Created by Вадим on 04.11.2017.
 */

public class ProgressRSSI extends View {


    private Bitmap arrow, arrow1;
    private Matrix matrix;
    private int angleRSSI;
    private int start;
    private int stop;
    private int x;
    private int y;
    private Drawable drawable;

    public ProgressRSSI(Context context) {
        this(context, null);
    }

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        matrix = new Matrix();
        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
        drawable = getResources().getDrawable(R.drawable.tachometer);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);

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
        arrow1 = Bitmap.createScaledBitmap(arrow, Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2, Math.min(getMeasuredWidth(), getMeasuredHeight()) / 8, true);
        x = (width / 2) - arrow1.getWidth() / 2;
        y = (height / 2) - arrow1.getHeight() / 2;

        int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
        int left = (getMeasuredWidth() / 2) - min / 2;
        int top = 0;
        int right = (getMeasuredWidth() / 2) + min / 2;
        int bottom = min;
        drawable.setBounds(left, top, right, bottom);
    }
}