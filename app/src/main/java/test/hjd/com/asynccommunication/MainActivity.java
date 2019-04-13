package test.hjd.com.asynccommunication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/*
用于讲解Handler的相关知识
 */
public class MainActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        handler的基本使用(1)
         */
//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//
//                System.out.println("--------->收到了消息: " + msg);
//
//            }
//        };
//
//
//        handler.sendEmptyMessage(1);


        /*
        handler的基本使用(2)
         */
//        Handler handler = new Handler(new Handler.Callback(){
//            @Override
//            public boolean handleMessage(Message msg) {
//                System.out.println("--------->收到了消息: " + msg);
//
//
//                return true;
//            }
//        });
//
//
//        handler.sendEmptyMessage(2);


        /*
        handler的基本使用(3)
         */
//        Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("---------->这是一个要要处理的消息");
//            }
//        });

        System.out.println("------------->onCreate thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());


        /*
        在子线程的环境之下使用handler

        之前handler最常见的使用场景是子线程往主线程发送消息
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);


                        //在子线程里面处理消息
                        System.out.println("------------->thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());
                    }
                };


                Looper.loop();
            }
        }).start();


        findViewById(R.id.send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick还是在主线程里面
                System.out.println("------------->onClick thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());

                //主线程往子线程里面发送消息
                handler.sendEmptyMessage(1);
            }
        });



        findViewById(R.id.send_msg1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("------------->thread2: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());

                        //子线程往子线程里面发送消息
                        handler.sendEmptyMessage(2);
                    }
                }).start();
            }
        });



    }
}
