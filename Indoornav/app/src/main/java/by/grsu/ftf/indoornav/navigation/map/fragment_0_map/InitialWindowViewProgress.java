package by.grsu.ftf.indoornav.navigation.map.fragment_0_map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

public class InitialWindowViewProgress extends View {

    private Paint rim;
    private Paint oval;
    private Context context;
    private int X;
    private int Y;
    private int radius;

    public InitialWindowViewProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        rim = new Paint();
        oval = new Paint();
        rim.setStyle(Paint.Style.STROKE);
        rim.setColor(ContextCompat.getColor(context, R.color.green_600));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int wight = right - left;
        int height = bottom - top;
        int min = Math.min(wight, height);
        X = wight / 2;
        Y = height / 2;
        radius = min / 10;

        rim.setStrokeWidth(min / 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(X, Y, radius, rim);
    }
}
