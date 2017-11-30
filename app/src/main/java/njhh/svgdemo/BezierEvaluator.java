package njhh.svgdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by njzoff on 2017/11/27.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float resultX = startValue.x + fraction * (endValue.x- startValue.x);
        float resultY = startValue.y + fraction * (endValue.y - startValue.x);
        return new PointF(resultX, resultY);
    }
}
