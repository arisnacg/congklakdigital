package com.example.aris.congklakgame2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity implements View.OnClickListener {

    int[] arrLubang = new int[16];
    int gilPemail = 1;
    Button[] arrBtn = new Button[16];
    Button btnPemain1, btnPemain2;
    ImageButton btnBack, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        //Init
        arrBtn[0] = findViewById(R.id.lubang1);
        arrBtn[1] = findViewById(R.id.lubang2);
        arrBtn[2] = findViewById(R.id.lubang3);
        arrBtn[3] = findViewById(R.id.lubang4);
        arrBtn[4] = findViewById(R.id.lubang5);
        arrBtn[5] = findViewById(R.id.lubang6);
        arrBtn[6] = findViewById(R.id.lubang7);
        arrBtn[7] = findViewById(R.id.lubang8);
        arrBtn[8] = findViewById(R.id.lubang9);
        arrBtn[9] = findViewById(R.id.lubang10);
        arrBtn[10] = findViewById(R.id.lubang11);
        arrBtn[11] = findViewById(R.id.lubang12);
        arrBtn[12] = findViewById(R.id.lubang13);
        arrBtn[13] = findViewById(R.id.lubang14);
        arrBtn[14] = findViewById(R.id.lubang15);
        arrBtn[15] = findViewById(R.id.lubang16);

        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnRestart);

        btnPemain1 = findViewById(R.id.btnPemain1);
        btnPemain2 = findViewById(R.id.btnPemain2);

        btnBack.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        for(int i = 0; i < arrLubang.length; i++){
            arrBtn[i].setOnClickListener(this);
        }
        reset();
        ubahWarnaPemain();
    }

    ///////////////////////////////////////////////////////////
    //Reset Game
    public void reset(){
//        for(int i = 0; i < arrLubang.length; i++){
//            arrLubang[i] = 7;
//            if(i == 7 || i == 15)
//                arrBtn[i].setText("0");
//            else
//                arrBtn[i].setText(""+arrLubang[i]);
//        }
        arrLubang[0] = 1;
        arrLubang[1] = 1;
        arrLubang[2] = 1;
        arrLubang[3] = 1;
        arrLubang[4] = 1;
        arrLubang[5] = 1;
        arrLubang[6] = 1;
        arrLubang[7] = 0;
        arrLubang[8] = 1;
        arrLubang[9] = 1;
        arrLubang[10] = 1;
        arrLubang[11] = 1;
        arrLubang[12] = 1;
        arrLubang[13] = 1;
        arrLubang[14] = 1;
        arrLubang[15] = 0;
        for(int i = 0; i < arrLubang.length; i++){
            if(i == 7 || i == 15)
                arrBtn[i].setText("0");
            else
                arrBtn[i].setText(""+arrLubang[i]);
        }
    }


    @Override
    public void onClick(View v){
        if(v == btnBack){
            finish();
        } else if(v == btnReset){
            this.reset();
        } else {
            for (int i = 0; i < arrBtn.length; i++) {
                if (v == arrBtn[i] && i != 7 && i != 15) {
                    boolean valid = false;
                    //pemain 1
                    if(this.gilPemail == 1){
                        for(int j = 0; j <= 6; j++) {
                            if(v == arrBtn[j]){
                                valid = true;
                                break;
                            }
                        }
                    //pemain 2
                    } else {
                        for(int j = 8; j <= 14; j++) {
                            if(v == arrBtn[j]){
                                valid = true;
                                break;
                            }
                        }
                    }
                    if(valid) {
                        lubangDipilih(i, gilPemail);
                        if (gilPemail == 1)
                            this.gilPemail = 0;
                        else
                            this.gilPemail = 1;
                        ubahWarnaPemain();
                    }
                }
            }
        }
    }

    public void ubahWarnaPemain(){
        if(this.gilPemail == 1){
            btnPemain1.setBackgroundColor(Color.parseColor("#FF18D15F"));
            btnPemain1.setTextColor(Color.parseColor("#FFFFFF"));
            btnPemain2.setBackgroundColor(Color.parseColor("#CCCCCC"));
            btnPemain2.setTextColor(Color.parseColor("#888888"));
        } else {
            btnPemain2.setBackgroundColor(Color.parseColor("#FF18D15F"));
            btnPemain2.setTextColor(Color.parseColor("#FFFFFF"));
            btnPemain1.setBackgroundColor(Color.parseColor("#CCCCCC"));
            btnPemain1.setTextColor(Color.parseColor("#888888"));
        }
    }

    public void delay(final int index){

    }

    public void ubahWarnaButton(int i){
        arrBtn[i].setBackgroundColor(Color.parseColor("#FF18D15F"));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
            }
        }, 1000);
        arrBtn[i].setBackgroundColor(Color.parseColor("FF3F51B4"));
    }

    public void lubangDipilih(int index, int pemain){
        if(index != 7 || index != 15){
            int jmlBiji = arrLubang[index];
            arrLubang[index] = 0;
            arrBtn[index].setText(""+arrLubang[index]);
            int i = index;
            while(jmlBiji > 0){
                //mengulang i dari 15 ke 0
                if(i < 15)
                    i += 1;
                else
                    i = 0;

                //mengecek i ada di lubang 8 atau 16
                if(i == 7){ //jika di lubang ke-8
                    if (pemain == 1) {
                        arrLubang[i] += 1;
                        jmlBiji -= 1;
                        arrBtn[i].setText(""+arrLubang[i]);
                    } else {
                        continue;
                    }
                } else if(i == 15){ //jika di lubang ke-16
                    if(pemain == 1){
                        continue;
                    } else {
                        arrLubang[i] += 1;
                        jmlBiji -= 1;
                        arrBtn[i].setText(""+arrLubang[i]);
                    }
                } else { //lainnya
                    arrLubang[i] += 1;
                    jmlBiji -= 1;
                    delay(i);
                    //arrBtn[i].setText(""+arrLubang[i]);
                    if(jmlBiji == 0 && arrLubang[i] != 1){
                        jmlBiji = arrLubang[i];
                        arrLubang[i] = 0;
                    } else {
                        if(this.gilPemail == 1){
                            for(int j = 0; j <= 6; j++){
                                if(i == j){
                                    arrLubang[7] += arrLubang[14-i];
                                    arrLubang[14-i] = 0;
                                    break;
                                }
                            }
                        } else {
                            for(int j = 8; j <= 14; j++){
                                if(i == j){
                                    arrLubang[14] += arrLubang[14-i];
                                    arrLubang[14-i] = 0;
                                    break;
                                }



                            }
                        }
                    }
                }
            } //end while
//            for(int k = 0; k < arrLubang.length; k++){
//                arrBtn[k].setText(""+ arrLubang[k]);
//            }
        }
    }
}
