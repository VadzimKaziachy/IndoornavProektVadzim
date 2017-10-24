package by.grsu.ftf.indoornav.adapter;

import android.view.View;

/**
 * Created by Вадим on 24.10.2017.
 */

public interface ClickListener<T> {
    void onClick(View view, T t);
}
