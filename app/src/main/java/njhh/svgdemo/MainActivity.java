package njhh.svgdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LoveLayout mIv;
    private boolean isStarting=false;
    private static final int Number = 1;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == Number) {
                // 每隔10s响一次
                mIv.addlove();
                handler.sendEmptyMessageDelayed(Number, 1000);
//                sp.play(music, 1, 1, 0, 0, 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = (LoveLayout) findViewById(R.id.love_id);
    }

    public void addlove(View view) {
        if (isStarting) {
            //如果动画正在运行就停止，否则就继续执行
            //结束进程
            handler.removeMessages(Number);
        } else {
            // 执行动画
            isStarting = true;
            handler.sendEmptyMessage(Number);
        }
    }
}
