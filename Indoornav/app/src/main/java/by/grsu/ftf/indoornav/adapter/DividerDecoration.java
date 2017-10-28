package by.grsu.ftf.indoornav.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Вадим on 28.10.2017.
 */

public class DividerDecoration extends RecyclerView.ItemDecoration {
    private final Drawable drawable;

    public DividerDecoration(Context context) {
        int[] atts = {android.R.attr.listDivider};
        drawable = context.obtainStyledAttributes(atts).getDrawable(0);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            View item = parent.getChildAt(i);
            int top = item.getBottom() + ((RecyclerView.LayoutParams) item.getLayoutParams()).bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);

        }
    }
}
