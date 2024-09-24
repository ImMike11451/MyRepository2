package com.example.GridViewRepeat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class GridviewRe extends GridView {
    public GridviewRe(Context context) {
        super(context);
    }

    public GridviewRe(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height =MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
