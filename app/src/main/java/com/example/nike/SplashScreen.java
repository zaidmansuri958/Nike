package com.example.nike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    ImageView top,bottom,jordan;
    TextView title;
    Animation slideImage,slideTitle,slideBottom,slideOnClick;
    LinearLayout bottomBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top=findViewById(R.id.top_shoe);
        bottom=findViewById(R.id.bottom_shoe);
        jordan=findViewById(R.id.jordan);
        title=findViewById(R.id.title);
        bottomBar=findViewById(R.id.bottom_bar);
        mAuth=FirebaseAuth.getInstance();

        slideImage= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideimage);
        slideTitle=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slidetitle);
        slideBottom=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slidebottom);
        slideOnClick=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideonclick);
        top.startAnimation(slideImage);
        bottom.startAnimation(slideImage);
        jordan.startAnimation(slideImage);
        title.startAnimation(slideTitle);
        bottomBar.startAnimation(slideBottom);

        bottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomBar.startAnimation(slideOnClick);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mAuth.getCurrentUser()!=null){
                            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(SplashScreen.this,Signup.class);
                            startActivity(intent);
                        }
                        finish();
                    }
                },1800);
            }
        });
    }
}