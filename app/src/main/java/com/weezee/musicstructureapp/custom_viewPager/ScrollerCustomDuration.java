package com.weezee.musicstructureapp.custom_viewPager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/*
   * the animation of a view pager when applying PageTransformer only works if the user swipes with fingers.
        since this disabled in our ViewPagerWithCustomDuration class, we need to make swiping caused by setCurrentMethod
        shows the same animation with the same speed as if swiping with fingers

   * when swiping is cause by a call to setCurrentMethod instead, and even though the PageTransformer is applied,
        the animation happens much quicker compared to fingers swiping

   * this custom class allow us to change the default
     animation (when setCurrentMethod is called) of a ViewPager in the ViewPagerWithCustomDuration class

   *Read ViewPagerWithCustomDuration comments for more info
*/

public class ScrollerCustomDuration extends Scroller {
    private double mScrollFactor = 1;


    public ScrollerCustomDuration(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    //this method will change the duration a certain factor
    public void setScrollDurationFactor(double scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    //merely changing the duration with a factor of mScrollFactor
    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
    }


}