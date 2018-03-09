package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_2_Admin;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 28.02.2018.
 */

public class TimerView extends View {
    private int X;
    private int Y;
    private int x;
    private int y;
    private int r;
    private int min;
    private int angle = 0;
    private int xPos;
    private int yPos;
    private String time = "";
    private Paint paint;
    private Paint paintG;
    private Paint paintT;
    private RectF rectF;
    private boolean mFlag = true;
    private Context context;

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        paintG = new Paint();
        paintT = new Paint();
        rectF = new RectF();
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFlag) {
            canvas.drawCircle(x, y, r, paint);
            canvas.drawArc(rectF, 270, angle, false, paintG);
            canvas.drawText(time, xPos, yPos, paintT);
        } else {
            canvas.drawColor(Color.WHITE);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        X = right - left;
        Y = bottom - top;
        x = X / 2;
        y = Y / 2;
        min = Math.min(X, Y);
        r = min / 3;
        paint.setStrokeWidth(min / 10);
        paintG.setStrokeWidth(min / 10);
        paintT.setStrokeWidth(min / 5);
        paintT.setTextSize(min / 5);

        rectF.set(x - r, y - r, x + r, y + r);

        xPos = x;
        yPos = (int) ((Y / 2) - ((paintT.descent() + paintT.ascent()) / 2));
    }

    private void init() {
        paint.setColor(ContextCompat.getColor(context, R.color.borderHigh));
        paint.setStyle(Paint.Style.STROKE);

        paintG.setColor(ContextCompat.getColor(context, R.color.green_600));
        paintG.setStyle(Paint.Style.STROKE);

        paintT.setColor(Color.BLACK);
        paintT.setAntiAlias(true);
        paintT.setTextAlign(Paint.Align.CENTER);
    }

    public void setTimerView(int time) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(time * 1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                angle = (int) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public void tim(int time) {
        this.time = String.valueOf(time);
        invalidate();
    }

    public void mFlag(boolean mFlag) {
        this.mFlag = mFlag;
        invalidate();
    }
}
