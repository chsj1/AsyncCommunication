package test.hjd.com.asynccommunication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by allen on 2019/4/12.
 */
public class HandlerThreadActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        /*
        handlerThread的基本使用.


         */
        HandlerThread thread = new HandlerThread("my_handler_thread");
        thread.start();
        final Handler handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {

                //那么这时候handler会在子线程里面处理消息

                System.out.println("------------->handleMessage thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());


                super.handleMessage(msg);
            }
        };

        findViewById(R.id.send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick还是在主线程里面
                System.out.println("------------->onClick thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());

                //主线程往子线程里面发送消息
                handler.sendEmptyMessage(1);
            }
        });


        /*
        使用handlerThread实现的,
        子线程往子线程发送消息
         */
        findViewById(R.id.send_msg1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("------------->onClick2 thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());

                        //主线程往子线程里面发送消息
                        handler.sendEmptyMessage(2);

                    }
                }).start();
            }
        });


    }
}
