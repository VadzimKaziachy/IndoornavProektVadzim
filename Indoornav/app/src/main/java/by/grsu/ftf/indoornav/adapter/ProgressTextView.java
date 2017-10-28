package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Вадим on 28.10.2017.
 */

public class ProgressTextView extends TextView {
    private int mMaxValue = 100;

    public ProgressTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ProgressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressTextView(Context context) {
        super(context);
    }

    public synchronized void setValue(float value) {
        int rssi = (int) -(value + 50) * 2;
        this.setText(String.valueOf(value));

        LayerDrawable background = (LayerDrawable) this.getBackground();
        ClipDrawable barValue = (ClipDrawable) background.getDrawable(1);

        int newClipLevel = rssi * 10000 / mMaxValue;
        barValue.setLevel(newClipLevel);
        drawableStateChanged();
    }


}
