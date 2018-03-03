package by.grsu.ftf.indoornav.navigation.map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.db.beacon.Beacon;

/**
 * Created by Вадим on 25.07.2017.
 * This class will be responsible for the cards, namely this is will be make a request to the server
 */

public class Map extends View {

    private Boolean mCoordinate;
    private Drawable bluetooth_point;
    private Drawable map;
    private Drawable human;
    private Paint paint;
    private List<Beacon> beacons = new ArrayList<>();
    private float X;
    private float Y;

    public Map(Context context) {
        this(context, null);
    }

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bluetooth_point = getResources().getDrawable(R.drawable.bluetooth_point);
        map = getResources().getDrawable(R.drawable.map);
        human = getResources().getDrawable(R.drawable.ic_android);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0x400000ff);
        paint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        map.draw(canvas);

        if (mCoordinate) {
            Integer x = Math.round(X * canvas.getWidth());
            Integer y = Math.round(Y * canvas.getHeight());
            human.setBounds(x - 50, y - 50, x + 50, y + 50);
            human.draw(canvas);
        }
        for (Beacon beacon : beacons) {
            if (beacon.getX() != null) {
                Integer x = Math.round(beacon.getX() * canvas.getWidth());
                Integer y = Math.round(beacon.getY() * canvas.getHeight());
                bluetooth_point.setBounds(x - 50, y - 50, x + 50, y + 50);
                bluetooth_point.draw(canvas);
                if(canvas.getWidth()<canvas.getHeight()){
                    canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getWidth(), paint);
                } else {
                    canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getHeight(), paint);
                }

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        map.setBounds(0, 0, width, height);

    }

    public void provider(List<Beacon> beacons, PointF coordinate) {
        mCoordinate = false;
        if (coordinate != null) {
            X = coordinate.x;
            Y = coordinate.y;
            mCoordinate = true;
            invalidate();
        }
        if (beacons != null) {
            this.beacons = beacons;
            invalidate();
        }
    }

    public void coordinateDevice(List<Beacon> beacons, PointF start, PointF stop) {
        if (beacons != null) {
            this.beacons = beacons;
            invalidate();
        }

        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("x", start.x, stop.x);
        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("y", start.y, stop.y);
        ValueAnimator xy = new ValueAnimator();
        xy.setValues(x, y);
        xy.setDuration(1000);
        xy.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                X = (float) valueAnimator.getAnimatedValue("x");
                Y = (float) valueAnimator.getAnimatedValue("y");
                mCoordinate = true;
                invalidate();
            }
        });
        xy.start();
    }
}

