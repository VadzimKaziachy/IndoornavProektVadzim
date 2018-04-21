package by.grsu.ftf.indoornav.navigation.map.fragment_2_map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.PointerIcon;
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

    private Boolean mCoordinate = false;
    private Drawable bluetooth_point;
    private Drawable map;
    private Drawable human;
    private Drawable mapBas;
    private Picture picture;
    private Paint paint;
    private List<Beacon> beacons = new ArrayList<>();
    private float X;
    private float Y;
    private float svgYOffset = 0;
    private float svgXOffset = 0;
    private boolean flagPicture = false;
    private boolean flagCalculate = true;
    private Canvas canvas;
    private float k;
    private int radius;
    private Rect rect;

    public Map(Context context) {
        this(context, null);
    }

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bluetooth_point = getResources().getDrawable(R.drawable.ic_bluetooth_point);
        map = getResources().getDrawable(R.drawable.ic_cards);
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
        this.canvas = canvas;
        if (flagPicture) {
            if (flagCalculate) {
                calculateScalingOptions();
            }
            canvas.drawPicture(picture, rect);
            mapBas.draw(canvas);

            if (beacons != null) {
                for (Beacon beacon : beacons) {
                    if (beacon.getX() != null) {
                        Integer x = Math.round(svgXOffset + k * beacon.getX() * picture.getWidth());
                        Integer y = Math.round(svgYOffset + k * beacon.getY() * picture.getHeight());
                        bluetooth_point.setBounds(x - radius, y - radius, x + radius, y + radius);
                        bluetooth_point.draw(canvas);
//                        if (canvas.getWidth() < canvas.getHeight()) {
//                            canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getWidth(), paint);
//                        } else {
//                            canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getHeight(), paint);
//                        }

                    }
                }
            }
            if (mCoordinate) {
                Integer x = Math.round(svgXOffset + k * X * picture.getWidth());
                Integer y = Math.round(svgYOffset + k * Y * picture.getHeight());
                human.setBounds(x - radius, y - radius, x + radius, y + radius);
                human.draw(canvas);
            }
        } else {
            map.draw(canvas);
        }
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        mapBas = new PictureDrawable(picture);
        flagPicture = true;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int x = (right - left) / 2;
        int y = (bottom - top) / 2;
        map.setBounds(x - 50, y - 50, x + 50, y + 50);
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
        xy.setDuration(190);
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

    private void calculateScalingOptions() {
        float svgWidth = picture.getWidth();
        float svgHeight = picture.getHeight();
        float viewWidth = canvas.getWidth();
        float viewHeight = canvas.getHeight();
        radius = Math.round(Math.min(picture.getHeight(), picture.getWidth()) / 20);


        if (viewWidth > svgWidth && viewHeight > svgHeight) {
            float x = viewWidth - svgWidth;
            float y = viewHeight - svgHeight;

            if (x < y) {
                k = viewWidth / svgWidth;
                svgYOffset = viewHeight / 2 - k * svgHeight / 2;
                rect = new Rect(0, (int) ((viewHeight / 2 - k * svgHeight / 2)),
                        (int) (k * svgWidth), (int) ((viewHeight / 2 + k * svgHeight / 2)));
            } else {
                k = viewHeight / svgHeight;
                svgXOffset = viewWidth / 2 - k * svgWidth / 2;
                rect = new Rect((int) (viewWidth / 2 - k * svgWidth / 2), 0,
                        (int) (viewWidth / 2 + k * svgWidth / 2), (int) (k * svgHeight));
            }
        }
        flagCalculate = false;
    }
}

