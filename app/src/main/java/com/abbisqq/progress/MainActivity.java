package com.abbisqq.progress;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    Thread thread;
    Handler handler = new Handler();
    ProgressBar bar;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        bar = (ProgressBar)findViewById(R.id.bar);
    }

    public void buttonClicked(View view) {

        if(thread==null){
            btn.setEnabled(false);
            btn.setText("Loading");
            thread = new Thread(new someMajorTask());
            thread.start();


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                bar.setProgress(msg.arg1);
                if(msg.arg1==100){
                    bar.setProgress(0);
                    btn.setText("START THE PROGRESS");
                    btn.setEnabled(true);
                    thread=null;

                }

            }
            };
        }


    }




    public class someMajorTask implements Runnable{

        @Override
        public void run() {

            for(int i =1;i<=100;i++){
                Message message =  Message.obtain();
                message.arg1=i;
                handler.sendMessage(message);
                try {
                    thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }


}

