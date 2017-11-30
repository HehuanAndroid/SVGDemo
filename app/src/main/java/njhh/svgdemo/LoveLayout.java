package njhh.svgdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by njzoff on 2017/11/24.
 */

public class LoveLayout extends RelativeLayout {
    private LayoutParams params;
    private Drawable bubble;
    private int mDrawableHeight, mDrawableWidth;
    private Interpolator mInterpolator;
    private int mWidth, mHeight;
    public LoveLayout(Context context) {
        this(context,null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        initDrawable();
        initInterpolator();
        // 初始化params
        params = new LayoutParams(mDrawableWidth, mDrawableHeight);
        // 父容器水平居中
        params.addRule(CENTER_HORIZONTAL, TRUE);
        // 父容器的底部
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
    }
    private void initDrawable() {
        bubble = getResources().getDrawable(R.mipmap.ic_launcher);
        // 得到图片的实际宽高
        mDrawableWidth = bubble.getIntrinsicWidth();
        mDrawableHeight = bubble.getIntrinsicHeight();
    }
    public void addlove(){
            final ImageView loveIv=new ImageView(getContext());
            loveIv.setImageDrawable(bubble);
            loveIv.setLayoutParams(params);
            addView(loveIv);
            //最终的属性动画
            AnimatorSet finalSet = getAnimatorSet(loveIv);
        finalSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                removeView(loveIv);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
            finalSet.start();
    }

    private AnimatorSet getAnimatorSet(ImageView loveIv) {
        // 1.缩放动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(loveIv, "scaleX", 0.2f,
                1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(loveIv, "scaleY", 0.2f,
                1f);
        ValueAnimator animator = getBezierValueAnimator(loveIv);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(5000);
        set.playTogether(scaleX,scaleY,animator);
        set.setTarget(loveIv);
        return set;
    }

    private void initInterpolator() {
        mInterpolator= new DecelerateInterpolator();// 先加速后减速
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
    private ValueAnimator getBezierValueAnimator(final ImageView loveIv) {
        // 起点位置
        PointF pointF0 = new PointF((mWidth - mDrawableWidth) / 2, mHeight
                - mDrawableHeight);
        // 结束的位置
        PointF pointF3 = new PointF(0, 0);
        // 估值器Evaluator,来控制view的行驶路径（不断的修改point.x,point.y）
        BezierEvaluator evaluator = new BezierEvaluator();
        // 属性动画不仅仅改变View的属性，还可以改变自定义的属性
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0,
                pointF3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 不断改变ImageView的x,y的值
                PointF pointF = (PointF) animation.getAnimatedValue();
                loveIv.setX(pointF.x);
                loveIv.setY(pointF.y);
                loveIv.setAlpha(1 - animation.getAnimatedFraction());// 得到百分比
            }
        });
        animator.setTarget(loveIv);
        animator.setDuration(4000);
        return animator;
    }
}
