package com.example.aris.congklakgame2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnStart, btnRule, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btnStart =  findViewById(R.id.btnStart);
        btnRule = findViewById(R.id.btnRule);
        btnExit =  findViewById(R.id.btnExit);

        btnStart.setOnClickListener(this);
        btnRule.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnStart){
            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        } else if(v == btnRule){
            Intent i = new Intent(this, RuleActivity.class);
            startActivity(i);
        } else if(v == btnExit) {
            finish();
            System.exit(0);
        }
    }

}
