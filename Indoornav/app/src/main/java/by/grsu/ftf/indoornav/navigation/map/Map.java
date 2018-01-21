package by.grsu.ftf.indoornav.navigation.map;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 25.07.2017.
 * This class will be responsible for the cards, namely this is will be make a request to the server
 */

public class Map extends View {

    private Drawable bluetooth_point;
    private Drawable map;
    private List<Beacon> beacons = new ArrayList<>();

    public Map(Context context) {
        this(context, null);
    }

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bluetooth_point = getResources().getDrawable(R.drawable.bluetooth_point);
        map = getResources().getDrawable(R.drawable.map);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        map.draw(canvas);

        for (Beacon beacon : beacons) {
            if (beacon.getX() != null) {
                Integer x = Math.round(beacon.getX() * canvas.getWidth());
                Integer y = Math.round(beacon.getY() * canvas.getHeight());
                bluetooth_point.setBounds(x, y, x + 100, y + 100);
                bluetooth_point.draw(canvas);
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

    public void provider(List<Beacon> beacons) {
        this.beacons = beacons;
        invalidate();
    }
}

