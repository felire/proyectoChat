package com.example.felipe.chatvuelveacompilar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_main);
        AccessTokenTracker access = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                AccessToken.setCurrentAccessToken(currentAccessToken);
            }
        };
        if(AccessToken.getCurrentAccessToken() == null){
            Intent intent = new Intent(this, LoginFacebook.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
            finish();
        }
    }



}
