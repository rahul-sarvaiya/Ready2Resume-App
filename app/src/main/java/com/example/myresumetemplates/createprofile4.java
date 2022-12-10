package com.example.myresumetemplates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class createprofile4 extends AppCompatActivity implements View.OnClickListener {

    public CardView card1,card2,card3,card4;
    Button view_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile4);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);

        view_res=(Button) findViewById(R.id.view_res);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        view_res.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.c1:
                i = new Intent(this, personaldata1.class);
                startActivity(i);
                break;
            case R.id.c2:
                i = new Intent(this, education1.class);
                startActivity(i);
                break;
            case R.id.c3:
                i = new Intent(this, skills1.class);
                startActivity(i);
                break;
            case R.id.c4:
                i = new Intent(this, experience1.class);
                startActivity(i);
                break;
            case R.id.view_res:
                i= new Intent(this, tamplate4.class);
                startActivity(i);
                break;


        }
    }

}
