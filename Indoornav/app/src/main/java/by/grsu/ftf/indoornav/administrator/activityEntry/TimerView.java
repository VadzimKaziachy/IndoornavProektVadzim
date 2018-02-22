package by.grsu.ftf.indoornav.administrator.activityEntry;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 21.02.2018.
 */

public class TimerView extends View {

    private Paint paint;
    private Paint paintG;
    private Paint paintT;
    private Paint paintL;
    private Paint paintOk;
    private Path path;
    private Context context;
    private RectF rectf;
    private int X;
    private int Y;
    private int x;
    private int y;
    private int r;
    private int min;
    private int xPos;
    private int yPos;
    private int yPosOk;
    private int sector;
    private String time = "";
    private boolean mFlag = true;


    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        paintG = new Paint();
        paintT = new Paint();
        paintL = new Paint();
        paintOk = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectf = new RectF();
        path = new Path();
        initColor();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mFlag) {
            canvas.drawCircle(x, y, r, paint);

            canvas.drawArc(rectf, 270, sector, true, paintG);

            canvas.drawText(String.valueOf(time), xPos, yPos, paintT);
        } else {
            canvas.drawColor(Color.WHITE);

            canvas.drawPath(path, paintOk);
            canvas.drawText("отправлено", xPos, yPosOk, paintT);
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
        r = min / 5;


        if (Y > X) {
            path.moveTo(45 * (X / 100), 50 * (Y / 100));
            path.lineTo(55 * (X / 100), 60 * (Y / 100));
            path.lineTo(90 * (X / 100), 30 * (Y / 100));
        } else {
            path.moveTo(47 * (X / 100), 50 * (Y / 100));
            path.lineTo(50 * (X / 100), 60 * (Y / 100));
            path.lineTo(60 * (X / 100), 30 * (Y / 100));
        }

        yPosOk = (int) ((70 * (Y / 100)) - ((paintT.descent() + paintT.ascent()) / 2));

        rectf.set(x - r, y - r, x + r, y + r);
        paintOk.setStrokeWidth(r / 4);
        paintOk.setStyle(Paint.Style.STROKE);


        paintT.setTextSize(r / 2);
        xPos = (X / 2);
        yPos = (int) ((Y / 2) - ((paintT.descent() + paintT.ascent()) / 2));
    }

    public void setTimerView(Integer timer) {

        final ValueAnimator animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(timer * 1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                sector = (int) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }
    public void tim(Integer a){
        time = String.valueOf(a);
        invalidate();
    }

    public void mFlag(boolean mFlag) {
        this.mFlag = mFlag;
        invalidate();
    }

    private void initColor() {
        paintT.setTextAlign(Paint.Align.CENTER);
        paintT.setAntiAlias(true);
        paintT.setColor(ContextCompat.getColor(context, R.color.black));
        paint.setColor(ContextCompat.getColor(context, R.color.green_100));
        paintG.setColor(ContextCompat.getColor(context, R.color.green_600));
        paintOk.setColor(ContextCompat.getColor(context, R.color.green_600));
        paintOk.setPathEffect(new CornerPathEffect(25));
    }
}
