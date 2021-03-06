package com.keegan.kstyle;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    public void onFadeIn(View view) {
        AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(animation);
    }

    public void onFadeOut(View view) {
        AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.fade_out);
        view.startAnimation(animation);
    }

    public void onZoomIn(View view) {
        ScaleAnimation animation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        view.startAnimation(animation);
    }

    public void onZoomOut(View view) {
        ScaleAnimation animation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        view.startAnimation(animation);
    }

    public void onMoveLeft2Right(View view) {
        TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.move_left_to_right);
        view.startAnimation(animation);
    }

    public void onMoveInFromBottom(View view) {
        TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.move_in_from_bottom);
        view.startAnimation(animation);
    }

    public void onRotate(View view) {
        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate_one);
        view.startAnimation(animation);
    }

    public void onMoveAndScale(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_and_scale));
    }

    public void onValueAnimatorTest(final View view) {
        // 获取屏幕宽度
        final int maxWidth = getWindowManager().getDefaultDisplay().getWidth();
        // 获得动画对象的实例
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 当前动画值，即为当前宽度比例值
                int currentValue = (Integer) animator.getAnimatedValue();
                // 根据比例更改目标view的宽度
                view.getLayoutParams().width = maxWidth * currentValue / 100;
                view.requestLayout();
            }
        });
        // 开始动画
        valueAnimator.start();
    }

    public void onObjectAnimatorTest(View view) {
        // 获取屏幕宽度
        int maxWidth = getWindowManager().getDefaultDisplay().getWidth();
        ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
        objectAnimator.setTarget(wrapper);
        objectAnimator.start();
    }

    public void onAnimatorSetTest(View view) {
        // 获取屏幕宽度
        int maxWidth = getWindowManager().getDefaultDisplay().getWidth();
        ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        animatorSet.setTarget(wrapper);
        animatorSet.start();
    }

    private static class ViewWrapper {
        private View target;
        private int maxWidth;

        public ViewWrapper(View target, int maxWidth) {
            this.target = target;
            this.maxWidth = maxWidth;
        }

        public int getWidth() {
            return target.getLayoutParams().width;
        }

        public void setWidth(int widthValue) {
            target.getLayoutParams().width = maxWidth * widthValue / 100;
            target.requestLayout();
        }

        public void setMarginTop(int margin) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) target.getLayoutParams();
            layoutParams.setMargins(0, margin, 0, 0);
            target.setLayoutParams(layoutParams);
        }
    }

    public void startFrameAnimation(View view) {
        //start empty to fill and loop the animation
        ImageView mImageViewFilling = (ImageView) findViewById(R.id.imageview_animation_list_filling);
        ((AnimationDrawable) mImageViewFilling.getBackground()).start();
        //start fill to empty and the animation only one time
        ImageView mImageViewEmptying = (ImageView) findViewById(R.id.imageview_animation_list_emptying);
        ((AnimationDrawable) mImageViewEmptying.getBackground()).start();

        ImageView mImageAnimSele = (ImageView) findViewById(R.id.imageview_animated_selector);
        mImageAnimSele.setActivated(!mImageAnimSele.isActivated());
    }

    public void endFrameAnimation(View view) {
        ImageView mImageViewFilling = (ImageView) findViewById(R.id.imageview_animation_list_filling);
        ((AnimationDrawable) mImageViewFilling.getBackground()).stop();

        ImageView mImageViewEmptying = (ImageView) findViewById(R.id.imageview_animation_list_emptying);
        ((AnimationDrawable) mImageViewEmptying.getBackground()).stop();
    }
}
