package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity  {

    Handler handler = new Handler();

    ImageView ivPokeballSpl, ivGengarS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivPokeballSpl = findViewById(R.id.ivPokeballSpl);
        ivGengarS = findViewById(R.id.ivGengarS);

        //Animaciones
        Animation a = AnimationUtils.loadAnimation (getApplicationContext(), R.anim.rotation);
        Animation b = AnimationUtils.loadAnimation (getApplicationContext(), R.anim.fade_i_o);

        //Animación movimiento pokeball
        final int[] timeRand = {(int) (Math.random() * 1000) + 100};

        handler.postDelayed(new Runnable() {

            public void run() {
                timeRand[0] = (int) (Math.random() * 1000) + 100;

                ivPokeballSpl.startAnimation(a);

                handler.postDelayed(this, timeRand[0]);
            }

        }, timeRand[0]);

        //Animación parpadeo random luz

        final int[] timeRand2 = {(int) (Math.random() * 1000) + 1};

        handler.postDelayed(new Runnable() {

            public void run() {
                timeRand2[0] = (int) (Math.random() * 500) + 1;

                ivGengarS.startAnimation(b);

                handler.postDelayed(this, timeRand2[0]);
            }

        }, timeRand2[0]);

        ivPokeballSpl.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

    }
}