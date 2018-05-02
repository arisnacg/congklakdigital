package com.example.aris.congklakgame2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class RuleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
