package by.grsu.ftf.indoornav.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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
    int angleRSSI;

    public RSSIspeedometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RSSIspeedometer, 0, 0);
        typedArray.recycle();

        path = new Path();
        paint = new Paint();
        rect = new Rect();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imgonline12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = Math.min( canvas.getWidth(),canvas.getHeight());
        int y = Math.min( canvas.getWidth(),canvas.getHeight());

        paint.setStrokeWidth(x / 50);
        paint.setColor(Color.BLACK);

        canvas.drawBitmap(bitmap1, 0, 0, paint);

        canvas.rotate(angleRSSI + 150, x / 2, y / 2);
        canvas.drawLine(x / 3, y / 2, 5 * x / 6, y / 2, paint);
        canvas.drawCircle(x / 2, y / 2, x / 16, paint);

        path.moveTo(6 * x / 7, y / 2);
        path.lineTo((6 * x / 7) - x / 20, y / 2 - x / 30);
        path.lineTo((6 * x / 7) - x / 20, y / 2 + x / 30);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void setAngleRSSI(int angleRSSI) {
        this.angleRSSI = angleRSSI;
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
    }
}