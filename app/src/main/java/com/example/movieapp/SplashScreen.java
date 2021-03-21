package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.logging.LogRecord;

public class SplashScreen extends AppCompatActivity {
    ImageView text,logo;
    Animation t,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        },2000);
        t= AnimationUtils.loadAnimation(this,R.anim.bottom);
        i=AnimationUtils.loadAnimation(this,R.anim.top);
        logo=findViewById(R.id.imageView8);
        text=findViewById(R.id.imageView7);
        logo.setAnimation(t);
        text.setAnimation(i);






    }
}