package com.example.thefrothybikecobookingsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_in extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_in);
        final ImageView bike1;

        bike1 = (ImageView) findViewById(R.id.bike1);

        bike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation = AnimationUtils.loadAnimation(Splash_in.this, R.anim.lefttoright);

                bike1.startAnimation(animation);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                bike1.setEnabled(true);

                                Intent intent = new Intent(Splash_in.this, ScrollingActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                },3000);
            }
        });
    }

}
