package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 11.11.2017.
 */

public class RSSIspeedometer extends View {

    Paint paint;
    BitmapFactory.Options options;
    Bitmap bitmap;
    Path path;
    int angleRSSI;

    public RSSIspeedometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RSSIspeedometer, 0, 0);
        typedArray.recycle();

        path = new Path();
        paint = new Paint();
        options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imgonline12, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        int x = (canvas.getWidth() / 2) - (bitmap.getWidth() / 2);
        int y = (canvas.getHeight() / 2) - (bitmap.getHeight() / 2);

        canvas.drawBitmap(bitmap, x, y, null);


        canvas.rotate(angleRSSI + 150, x + (bitmap.getWidth() / 2), y + (bitmap.getHeight() / 2));
        canvas.drawLine(x + (bitmap.getWidth() / 2), y + (bitmap.getHeight() / 2), x + (5 * bitmap.getWidth() / 6), y + (bitmap.getHeight() / 2), paint);
        canvas.drawCircle(x + (bitmap.getWidth() / 2), y + (bitmap.getHeight() / 2), bitmap.getWidth() / 16, paint);


        path.moveTo(x + (5 * bitmap.getWidth() / 6) + 10, y + (bitmap.getHeight() / 2));
        path.lineTo(x + (5 * bitmap.getWidth() / 6) - bitmap.getWidth() / 20, y + ((bitmap.getHeight() / 2 - bitmap.getWidth() / 30)));
        path.lineTo(x + (5 * bitmap.getWidth() / 6) - bitmap.getWidth() / 20, y + ((bitmap.getHeight() / 2 + bitmap.getWidth() / 30)));
        path.close();
        canvas.drawPath(path, paint);
    }

    public void setAngleRSSI(int angleRSSI) {
        this.angleRSSI = angleRSSI;
        invalidate();
    }
}
