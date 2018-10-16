package com.greenlab.hackme.slidebutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class ShineEffect extends AppCompatActivity {


    ShineEffect(TextView textView){
        MyThread thread=new MyThread(textView);
        thread.start();
    }

    public class MyThread extends Thread
    {
        TextView txtView;
        int charGaps = 2;
        int startPosition = 0;
        int endPosition = charGaps;
        int lengthOfString;
        //used for stopping thread
        boolean flag;

        //init flag to true so that method run continuously
       MyThread(TextView txtView) {
            flag = true;
            this.txtView=txtView;
            lengthOfString=txtView.getText().length();
        }

        //set flag false, if want to stop this thread
        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            super.run();
            while (flag) {
                try {
                    Thread.sleep(100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Spannable spn = new SpannableString(txtView.getText().toString());
                                spn.setSpan(new ForegroundColorSpan(Color.WHITE), startPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                txtView.setText(spn);
                            }catch (IndexOutOfBoundsException e){
                                e.printStackTrace();
                            }

                            startPosition++;
                            endPosition++;
                            try {
                                endPosition %= (lengthOfString + charGaps);
                                startPosition %= lengthOfString;
                            }catch (ArithmeticException e){
                                e.printStackTrace();
                            }

                            if (startPosition == 0) {
                                endPosition = charGaps;
                                startPosition = 0;
                            }

                            if (endPosition > lengthOfString) {
                                endPosition = lengthOfString;
                            }

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }}
}
