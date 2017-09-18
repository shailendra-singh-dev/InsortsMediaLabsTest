package inshorts.com.inshortsapp.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import inshorts.com.inshortsapp.interfaces.RecyclerViewOnItemClickListener;

/**
 * Created by Shailendra on 9/17/2017.
 */

public class RecycleViewItemTouchListener implements RecyclerView.OnItemTouchListener {

    private static final String TAG = RecycleViewItemTouchListener.class.getSimpleName();
    private final RecyclerViewOnItemClickListener mListener;
    private long mLastClickElapsedTime;

    private final GestureDetector mGestureDetector;

    public RecycleViewItemTouchListener(Context context, RecyclerViewOnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        int position = view.getChildPosition(childView);
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            if ((System.currentTimeMillis() - mLastClickElapsedTime) < 100)
                return true;

            mLastClickElapsedTime = System.currentTimeMillis();
            mListener.onItemClick(findChildViewLeaf(childView, e), position);
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private View findChildViewLeaf(View childView, MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        View v = childView;
        while (childView instanceof ViewGroup && v != null) {
            x -= childView.getLeft();
            y -= childView.getTop();
            v = findChildViewUnder(((ViewGroup) childView), x, y);
            if (v != null) {
                childView = v;
            }
        }
        return childView;
    }

    private View findChildViewUnder(ViewGroup viewGroup, float x, float y) {
        final int count = viewGroup.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = viewGroup.getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            final float translationX = ViewCompat.getTranslationX(child);
            final float translationY = ViewCompat.getTranslationY(child);
            if (x >= child.getLeft() + translationX &&
                    x <= child.getRight() + translationX &&
                    y >= child.getTop() + translationY &&
                    y <= child.getBottom() + translationY) {
                return child;
            }
        }
        return null;
    }


}
