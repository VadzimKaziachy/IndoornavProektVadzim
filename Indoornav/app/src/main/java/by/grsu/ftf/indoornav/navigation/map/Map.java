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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.indoornav.R;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.Beacon.Beacon;

/**
 * Created by Вадим on 25.07.2017.
 * This class will be responsible for the cards, namely this is will be make a request to the server
 */

public class Map extends View {

    private Bitmap bluetooth_point;
    private List<Beacon> beacons = new ArrayList<>();

    public Map(Context context) {
        this(context, null);
    }

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bluetooth_point = BitmapFactory.decodeResource(getResources(), R.drawable.bluetooth_point);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Beacon beacon : beacons) {
            if (beacon.getY().equals("")) {
                break;
            } else {
                Integer x = Integer.parseInt(beacon.getX()) + 200;
                Integer y = Integer.parseInt(beacon.getY()) + 200;
                canvas.drawBitmap(bluetooth_point, x, y, null);
            }
        }
    }

    public void provider(List<Beacon> beacons) {
        this.beacons = beacons;
        invalidate();
    }
}

