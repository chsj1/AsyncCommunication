package test.hjd.com.asynccommunication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by allen on 2019/4/12.
 */
public class IntentServiceActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
//        startService(intent);

//        new MyAsyncTask().execute("hahaha");


        SharedPreferences sp = getSharedPreferences("hello", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("salary", 45000);



        /*
        返回值表示 插入磁盘缓存 是否成功
         */
        editor.commit();
        /*
        没有返回值
         */
        editor.apply();


        int salary = sp.getInt("salary", 40000);

        System.out.println("--------->salary: " + salary);



    }

    /*
    AsyncTask的基本使用
     */
    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            System.out.println("--------->onPreExecute");
        }


        @Override
        protected String doInBackground(String... strings) {


            System.out.println("--------->doInBackground execute方法传进来的参数: " + strings[0]);

            for (int i = 0; i < 10; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                publishProgress(i * 10);

            }
            return "处理完成了";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            System.out.println("-------->onProgressUpdate 进度更新了: " + values[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("------>onPostExecute : " + s);
        }
    }

}
