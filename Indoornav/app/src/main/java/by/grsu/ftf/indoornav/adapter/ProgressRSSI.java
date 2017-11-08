package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Вадим on 04.11.2017.
 */

public class ProgressRSSI extends View {

    private Float progressRSSI;
    private String RSSI;

    Paint paint = new Paint();

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ProgressRSSI, 0, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = this.getMeasuredWidth();
        float y = this.getMeasuredHeight();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, x, y, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, progressRSSI * x, y, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("RSSI " + this.RSSI, x / 2, 90 - ((y - 40) / 2), paint);
    }

    public void setRSSI(Float progressRSSI, String RSSI) {
        this.progressRSSI = progressRSSI;
        this.RSSI = RSSI;
        invalidate();
        requestLayout();
    }
}
