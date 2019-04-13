package test.hjd.com.asynccommunication;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by allen on 2019/4/12.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("myintentservice");
    }




    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("-------------> MyIntentService thread: " + Thread.currentThread().getName() + " , " + Thread.currentThread().getId());
    }
}
