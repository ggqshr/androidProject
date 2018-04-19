package com.example.ggq.myapplication.View;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.ggq.myapplication.R;

/**
 * Created by ggq on 2017/5/24.
 */

public class SlideView extends HorizontalScrollView {

    private boolean once;
    private boolean isOpen;
    //固定的
    private LinearLayout mWapper;
    //菜单栏
    private ViewGroup mMenu;
    //内容的区域
    private ViewGroup mContent;
    //屏幕的宽度
    private int mScreenWidth;

    //menu与屏幕右侧的边距 dp
    private int mMenuRightPadding = 250;
    //菜单栏的宽度
    private int mMenuWidth;

    /**
     * 未使用指定属性时调用
     *
     * @param context
     * @param attrs
     */
    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SlideView(Context context) {
        this(context, null);
    }

    /*
    当使用了自定义属性时，会调用此构造方法
     */
    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取我们自定义的属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlideView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlideView_rightPadding:
                    mMenuRightPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150,
                            context.getResources().getDisplayMetrics()));
                    break;
            }
        }

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetric);
        mScreenWidth = outMetric.widthPixels;
//        把dp转化成px
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*
    通过设置偏移量，将Menu隐藏
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int acton = ev.getAction();
        switch (acton) {
            case MotionEvent.ACTION_UP:
                //隐藏在屏幕左边的宽度
                int scrollX = getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void openMenu() {
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    public void closeMenu() {
        if (!isOpen)
            return;
        this.smoothScrollTo(mMenuWidth, 0);
        isOpen = false;
    }

    /**
     * 切换菜单
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    /*
    滚动发生时
     */

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //实现抽屉式侧滑
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenu, "translationX", oldl, l);
        animator.setDuration(0);
        animator.start();
    }
}
