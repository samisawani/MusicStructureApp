package com.weezee.musicstructureapp.custom_viewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/*
this class is also used in conjunction with ScrollCustomDuration class to enable us to change the animation speed
this is desired because when swiping through pagers programmatically(i.e using the setCurrentItem), the animation is much faster
than what would've been if swiped with fingers instead
 */

public class ViewPagerWithCustomDuration extends ViewPager implements ViewPager.OnPageChangeListener {
    private ScrollerCustomDuration mScroller = null;
    private int currentPlayingSongIndex =0;

    public int getCurrentPlayingSongIndex() {
        return currentPlayingSongIndex;
    }

    public void setCurrentPlayingSongIndex(int currentPlayingSongIndex) {
        this.currentPlayingSongIndex = currentPlayingSongIndex;
    }

    public ViewPagerWithCustomDuration(Context context) {
        super(context);
        postInitViewPager();
    }

    public ViewPagerWithCustomDuration(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    //this two methods below are overridden because we want to disable default swiping behaviour and only
    //allow swiping through button clicks in the MainActivity
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return false;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        return false;
//}

    //this where we will basically change the viewPager mScroller instance using reflection
    private void postInitViewPager() {
        try {
            //getting reflection Fields from base class because interpolator and scroller are private and can't be changed normally
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);//turn off access check
            interpolator.setAccessible(true);//turn off access check
            mScroller = new ScrollerCustomDuration(getContext(), (Interpolator) interpolator.get(null));//passing null coz interpolator is static
            scroller.set(this, mScroller);//first arg is merely an object instance of the class that owns the field.
            // and second one is the value we wanna set

        }

        catch (Exception e) {

        }
    }

    //this control the duration of the animation when ViewPager is changing pages
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }


    @Override
    public void onPageScrolled(int i, float positionOffset, int i1) {
        super.onPageScrolled(i,positionOffset ,i1 );
        currentPlayingSongIndex=getCurrentItem();


    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void onPageSelected(int position) {
        currentPlayingSongIndex = position + 1;
    }
}