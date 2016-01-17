package com.atguigu.circlebar;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends Activity {

    private CircleBar cb_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb_main = (CircleBar)findViewById(R.id.cb_main);
    }

    private boolean downloading = false;//标识是否正在下载
    public void startDownload(View v) {
        if(downloading)
            return;
        new Thread(){
            public void run(){
                downloading = true;
                cb_main.setProgress(0);
                int count = 55;
                //设置最大进度
                cb_main.setMax(count);
                for(int i = 0; i < count; i++) {
                    SystemClock.sleep(100);
                    cb_main.setProgress(cb_main.getProgress()+1);
                }
                downloading = false;
            }
        }.start();
    }
}
