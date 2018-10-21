package com.greenlab.hackme.swipebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.greenlab.hackme.slidebutton.SlideButton;

public class MainActivity extends AppCompatActivity implements SlideButton.SlideListener {

    SlideButton slideButton1,slideButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideButton1=findViewById(R.id.btnUp);
        slideButton2=findViewById(R.id.btnDown);

        slideButton1.setOnSlideListener(this);
        slideButton2.setOnSlideListener(this);
    }


    @Override
    public void onClick(SlideButton mSlideButton, boolean active) {

        if(mSlideButton.getId()==R.id.btnUp) {

            if(active){
                Toast.makeText(this, "Active Up", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Deactivate Up", Toast.LENGTH_SHORT).show();
            }

        }else if(mSlideButton.getId()==R.id.btnDown){

            if(active){
                Toast.makeText(this, "Active Down", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Deactivate Down", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
