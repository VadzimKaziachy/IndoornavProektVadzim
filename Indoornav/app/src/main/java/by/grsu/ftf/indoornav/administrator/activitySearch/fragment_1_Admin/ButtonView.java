package by.grsu.ftf.indoornav.administrator.activitySearch.fragment_1_Admin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 10.03.2018.
 */

public class ButtonView extends View {

    private Paint rim;
    private Paint centerOval;
    private Paint paintT;
    private Context context;
    private int xCenter;
    private int yCenter;
    private int radius;
    private int radiusOval;
    private int xPos;
    private int yPos;

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        rim = new Paint();
        centerOval = new Paint();
        paintT = new Paint();
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(xCenter, yCenter, radius, rim);
        canvas.drawCircle(xCenter, yCenter, radiusOval, centerOval);
        canvas.drawText("поиск", xPos, yPos, paintT);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int X = right - left;
        int Y = bottom - top;
        int min = Math.min(X, Y);

        xCenter = X / 2;
        yCenter = Y / 2;

        radius = min / 5;
        rim.setStrokeWidth(radius / 10);
        paintT.setStrokeWidth(min / 10);
        paintT.setTextSize(min / 10);
        radiusOval = (min / 5);

        xPos = X / 2;
        yPos = (int) ((Y / 2) - ((paintT.descent() + paintT.ascent()) / 2));


    }

    private void init() {
        rim.setStyle(Paint.Style.STROKE);
        rim.setColor(ContextCompat.getColor(context, R.color.green_600));

        centerOval.setStyle(Paint.Style.FILL);
        centerOval.setColor(ContextCompat.getColor(context, R.color.green_100));

        paintT.setStyle(Paint.Style.FILL);
        paintT.setColor(ContextCompat.getColor(context, R.color.green_900));
        paintT.setTextAlign(Paint.Align.CENTER);
    }
}
