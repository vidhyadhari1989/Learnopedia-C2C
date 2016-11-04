package com.c2c.learnopedia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.c2c.learnopedia.other.AppConstance;

import info.androidhive.navigationdrawer.R;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);


        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent=null;

//                                          if (SharedPreferenceManager.isLoggedIn(SplashActivity.this))
//                                          {
                                              intent=new Intent(SplashActivity.this,MainActivity.class);
                                             // intent=new Intent(SplashActivity.this,HomeActivity.class);
                                              startActivity(intent);
                                              finish();

//                                          }
                                          /*else {

                                              intent = new Intent(SplashActivity.this, MainActivity.class);
                                              startActivity(intent);
                                              finish();
                                          }*/

                                      }
                                  }, AppConstance.SPLASH_DISPLAY_TIME
        );

    }


}
