package by.grsu.ftf.indoornav.navigation.map.fragment_0_map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.indoornav.R;

/**
 * Created by Vadzim on 20.03.2018.
 */

public class InitialWindowView extends View {

    private String e = "e";
    private String ex = "x";
    private String posit = "posit";
    private Paint paintText;
    private Paint paintX;
    private int x;
    private int y;
    private float widthE;
    private float widthEx;
    private float widthPosit;
    private float widthtExposit;
    private Context context;

    public InitialWindowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        paintX = new Paint();
        paintX.setColor(ContextCompat.getColor(context, R.color.green_600));
        paintX.setAntiAlias(true);
        paintX.setTextAlign(Paint.Align.CENTER);
        paintX.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(e, x - widthtExposit / 2 + widthE / 2, y, paintText);
        canvas.drawText(ex, x - widthtExposit / 2 + widthE  + widthEx / 2, y, paintX);
        canvas.drawText(posit, x - widthtExposit / 2 + widthE + widthEx  + widthPosit / 2, y, paintText);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int X = right - left;
        int Y = bottom - top;
        x = X / 2;
        y = Y / 2;

        int min = Math.min(x, y);

        paintText.setStrokeWidth(min / 5);
        paintText.setTextSize(min / 5);
        widthE = paintText.measureText(e);
        widthPosit = paintText.measureText(posit);

        paintX.setStrokeWidth(min / 5);
        paintX.setTextSize(min / 5);
        widthEx = paintX.measureText(ex);

        widthtExposit = paintText.measureText(e + ex + posit);
    }
}
