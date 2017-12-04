package by.grsu.ftf.indoornav.adapter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 11.11.2017.
 */

public class RSSIspeedometer extends View {

    Paint paint;
    Bitmap bitmap;
    Path path;
    Rect rect;
    Bitmap bitmap1;
    Bitmap bitmap3;
    Bitmap bitmap2;
    Matrix matrix;
    int angleRSSI;

    public RSSIspeedometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RSSIspeedometer, 0, 0);
        typedArray.recycle();

        path = new Path();
        paint = new Paint();
        rect = new Rect();
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tachometer);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = (Math.min(canvas.getWidth(), canvas.getHeight()) / 2) - bitmap3.getWidth() / 2;
        int y = (Math.min(canvas.getWidth(), canvas.getHeight()) / 2) - bitmap3.getHeight() / 2;

        canvas.drawBitmap(bitmap1, 0, 0, null);

        matrix.setTranslate(x, y);
        matrix.preRotate(angleRSSI + 150, bitmap3.getWidth() / 2, bitmap3.getHeight() / 2);
        canvas.drawBitmap(bitmap3, matrix, null);

    }

    public void setAngleRSSI(Float angleRSSI) {
        this.angleRSSI = Math.round(240 * angleRSSI);
        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        bitmap1 = Bitmap.createScaledBitmap(bitmap,
                Math.min(width, height), Math.min(width, height), true);
        bitmap3 = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth() / 2,
                bitmap2.getHeight() / 2, false);
    }
}