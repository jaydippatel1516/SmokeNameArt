package com.sainjeeapps.smokenameart.activities;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.sainjeeapps.smokenameart.R;

public class SplashScreen extends AppCompatActivity {
    
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            /* class com.sainjeeapps.smokenameart.activities.SplashScreen.AnonymousClass1 */

            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, 4000);
    }
}
