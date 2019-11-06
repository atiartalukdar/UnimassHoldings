package info.atiar.unimassholdings;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import session.Session;

public class Splash extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                decide();

            }
        }, 1000);
    }


    public void decide() {
        if (Session.isLoggedIn()){
            startActivity(new Intent(Splash.this,HomePage.class));
            finish();
        }else {
            startActivity(new Intent(Splash.this,MainActivity.class));
            finish();
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        decide();
    }


}
