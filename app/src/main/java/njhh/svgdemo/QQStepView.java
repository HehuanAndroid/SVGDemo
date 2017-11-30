package njhh.svgdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by njzoff on 2017/11/30.
 */

public class QQStepView extends View {
    private int mOuterColor;
    private int mInnerColor;
    private int mBorderWidth;
    private int mStepTextsize;
    private int mStepTextColor;
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
        //5.onmeasure
        //6.画外圆弧，内圆弧,文字
    }
}
