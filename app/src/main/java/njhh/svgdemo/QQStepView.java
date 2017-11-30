package njhh.svgdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by njzoff on 2017/11/30.
 */

public class QQStepView extends View {
    private int mOuterColor= Color.RED;
    private int mInnerColor=Color.BLUE;
    private int mBorderWidth=20;//单位为px 布局中的单位是dp
    private int mStepTextsize;
    private int mStepTextColor=Color.RED;
    public QQStepView(Context context) {
        this(context,null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1.分析效果
        //2.确定自定义属性,编写attrs.xml
        //3.在布局中使用
        //4.在自定义属性中获取
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        //第二个属性为默认值
        mOuterColor=array.getColor(R.styleable.QQStepView_outerColor,mOuterColor);
        mInnerColor=array.getColor(R.styleable.QQStepView_innerColor,mInnerColor);
        mBorderWidth=(int)array.getDimension(R.styleable.QQStepView_borderWidth,mBorderWidth);
        mStepTextsize=array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize,mStepTextsize);
        mStepTextColor=array.getColor(R.styleable.QQStepView_stepTextColor,mStepTextColor);
        array.recycle();
        //5.onmeasure
        //6.画外圆弧，内圆弧,文字
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 宽高不一致 取最小的值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height ? height:width,width>height ? height:width);

    }
}
