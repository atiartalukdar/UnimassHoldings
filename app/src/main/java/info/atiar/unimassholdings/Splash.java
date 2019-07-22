package info.atiar.unimassholdings;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

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
