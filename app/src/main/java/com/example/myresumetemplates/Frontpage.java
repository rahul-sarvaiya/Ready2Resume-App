package com.example.myresumetemplates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Frontpage extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    //variables

    TextView logo;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_frontpage);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Hooks
        logo = findViewById(R.id.textView);
        new Handler().postDelayed(new Runnable(){
            @Override

            public void run() {
                if (firebaseUser != null) {
                    Intent intent = new Intent(Frontpage.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Frontpage.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_SCREEN);


    }
}


