package by.grsu.ftf.indoornav.navigation.map.fragment_2_map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PointF;
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
    private float pictXOffset;
    private float pictYOffset;
    private int width;
    private int height;
    private boolean flagPicture = false;
    private Context context;

    public Map(Context context) {
        this(context, null);
        this.context = context;
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
        int needSave = canvas.save();
        if (flagPicture) {
            canvas.scale((float) canvas.getWidth() / picture.getWidth(),
                    (float) canvas.getHeight() / picture.getHeight());
            mapBas.draw(canvas);
            canvas.restoreToCount(needSave);

            if (beacons != null) {
                for (Beacon beacon : beacons) {
                    if (beacon.getX() != null) {
                        Integer x = Math.round(beacon.getX() * canvas.getWidth());
                        Integer y = Math.round(beacon.getY() * canvas.getHeight());
                        bluetooth_point.setBounds(x - 50, y - 50, x + 50, y + 50);
                        bluetooth_point.draw(canvas);
                        if (canvas.getWidth() < canvas.getHeight()) {
                            canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getWidth(), paint);
                        } else {
                            canvas.drawCircle(x, y, beacon.getRSSIprogress() * canvas.getHeight(), paint);
                        }

                    }
                }
            }
            if (mCoordinate) {
                Integer x = Math.round(X * canvas.getWidth());
                Integer y = Math.round(Y * canvas.getHeight());
                human.setBounds(x - 50, y - 50, x + 50, y + 50);
                human.draw(canvas);
            }
        } else {
            map.draw(canvas);
        }
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        mapBas = new PictureDrawable(picture);
        mapBas.setBounds(0, 0, picture.getWidth(), picture.getHeight());
        flagPicture = true;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int x = (right - left)/2;
        int y = (bottom - top)/2;
        map.setBounds(x-50, y - 50, x + 50, y + 50);
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
}

