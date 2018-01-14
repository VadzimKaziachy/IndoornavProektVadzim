package by.grsu.ftf.indoornav.navigation.map;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 25.07.2017.
 * This class will be responsible for the cards, namely this is will be make a request to the server
 */

public class Map extends View {

    private Bitmap bluetooth_point;

    private List<Beacon> beacons;

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.mapView, 0, 0);
        typedArray.recycle();
        bluetooth_point = BitmapFactory.decodeResource(getResources(), R.drawable.bluetooth_point);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Beacon beacon : beacons) {
            if (beacon.getX().equals("")) {
                break;
            } else {
                int x = Integer.parseInt(beacon.getX()) +100;
                int y = Integer.parseInt(beacon.getY()) + 100;
                canvas.drawBitmap(bluetooth_point, x, y, null);
            }
        }
    }

    public void provaider(List<Beacon> beacons) {
        this.beacons = beacons;
        invalidate();
    }
}

