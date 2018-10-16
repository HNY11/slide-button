package com.greenlab.hackme.swipebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.greenlab.hackme.slidebutton.SlideButton;

public class MainActivity extends AppCompatActivity implements SlideButton.SlideListener {

    SlideButton slideButton1,slideButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideButton1=new SlideButton(this);
        slideButton2=new SlideButton(this);
        slideButton1.setOnSlideListener(this);
        slideButton2.setOnSlideListener(this);
    }


    @Override
    public void onClick(boolean active, View v) {

         if(v.getId()==R.id.btnUp) {

             if(active){
                 Toast.makeText(this, "Active Up", Toast.LENGTH_SHORT).show();
             }
             else {
                 Toast.makeText(this, "Deactivate Up", Toast.LENGTH_SHORT).show();
             }

         }else if(v.getId()==R.id.btnDown){

             if(active){
                 Toast.makeText(this, "Active Down", Toast.LENGTH_SHORT).show();
             }else {
                 Toast.makeText(this, "Deactivate Down", Toast.LENGTH_SHORT).show();
             }
         }
    }
}
