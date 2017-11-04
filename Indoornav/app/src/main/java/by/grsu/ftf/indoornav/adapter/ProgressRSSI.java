package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Вадим on 04.11.2017.
 */

public class ProgressRSSI extends View {
    private String max;
    private String min;
    private String RSSI;

    Paint paint = new Paint();

    public ProgressRSSI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ProgressRSSI, 0, 0);
        max = typedArray.getString(R.styleable.ProgressRSSI_max);
        min = typedArray.getString(R.styleable.ProgressRSSI_min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = this.getMeasuredWidth();
        float y = this.getMeasuredHeight();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, x, y, paint);
        x = Math.abs((Integer.valueOf(min) - Integer.valueOf(RSSI)) * x) / (Integer.valueOf(max) - Integer.valueOf(min));
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, x, y, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(RSSI, 0, 0, paint);
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }
}
