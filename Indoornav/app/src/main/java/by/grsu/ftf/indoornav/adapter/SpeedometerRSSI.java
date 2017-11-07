package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 07.11.2017.
 */

public class SpeedometerRSSI extends View {

    Paint paint = new Paint();
    RectF rect;
    RectF rect1;
    int angleRSSI;

    public SpeedometerRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SpeedometerRSSI,0,0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = canvas.getWidth();
        int y = canvas.getHeight();
        int r = 200;
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);

        rect = new RectF(x / 2 - r, y / 2 - r, x / 2 + r, y / 2 + r);
        canvas.drawArc(rect, 180, 360, false, paint);
        paint.setColor(Color.WHITE);
        rect1 = new RectF(x / 2 - r + 10, y / 2 - r + 10, x / 2 + r - 10, y / 2 + r - 10);
        canvas.drawArc(rect1, 180, 360, false, paint);
        canvas.drawRect(0, y / 2, x, y, paint);
        paint.setColor(Color.BLACK);
        canvas.rotate(180 + angleRSSI, x / 2, y / 2);
        canvas.drawLine(x / 2, y / 2, x / 2 + r, y / 2, paint);
        canvas.drawLine(x / 2 + r, y / 2, x / 2 + r - 40, y / 2 - 20, paint);
        canvas.drawLine(x / 2 + r, y / 2, x / 2 + r - 40, y / 2 + 20, paint);
    }

    public void setAngleRSSI(int angleRSSI) {
        this.angleRSSI = angleRSSI;
        invalidate();
        requestLayout();
    }
}
