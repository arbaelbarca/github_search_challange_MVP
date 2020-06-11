package com.arbaelbarca.githubsearchuser.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.githubsearchuser.R;

public class LineItemDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public LineItemDivider(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            //if (i != 0) {
            mDivider.setBounds(left, top, right, bottom);
            // } else {
//                int bot = (int) parent.getContext().getResources().getDimension(R.dimen.padding_five);
//                mDivider.setBounds(0, top, parent.getWidth(), top + bot);
            //}
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mDivider.getIntrinsicHeight();
//        outRect.right = mDivider.getIntrinsicWidth();
    }
}
