package com.example.aris.congklakgame2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
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
    int jmlBiji;
    int indexBiji;
    int defaultAngka = 1;
    boolean game;
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
        for(int i = 0; i < arrLubang.length; i++) {
            if (i == 7 || i == 15) {
                arrLubang[i] = 0;
                arrBtn[i].setText("0");
            } else {
                arrLubang[i] = this.defaultAngka;
                arrBtn[i].setText("" + arrLubang[i]);
            }
        }

        this.game = true;

//        arrLubang[0] = 1;
//        arrLubang[1] = 1;
//        arrLubang[2] = 1;
//        arrLubang[3] = 1;
//        arrLubang[4] = 1;
//        arrLubang[5] = 1;
//        arrLubang[6] = 1;
//        arrLubang[7] = 0;
//        arrLubang[8] = 1;
//        arrLubang[9] = 1;
//        arrLubang[10] = 1;
//        arrLubang[11] = 1;
//        arrLubang[12] = 1;
//        arrLubang[13] = 1;
//        arrLubang[14] = 1;
//        arrLubang[15] = 0;
        for(int i = 0; i < arrLubang.length; i++){
            if(i == 7 || i == 15)
                arrBtn[i].setText("0");
            else
                arrBtn[i].setText(""+arrLubang[i]);
        }
        this.gilPemail = 1;
        ubahWarnaPemain();
    }


    @Override
    public void onClick(View v){
        if(v == btnBack){
            finish();
        } else if(v == btnReset){
            this.reset();
        } else if(this.game){
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
                        if (gilPemail == 1 && !isKosong(0))
                            this.gilPemail = 0;
                        else if(gilPemail == 0 && !isKosong(1))
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

    public boolean isKosong(int pemain){
        boolean kosong = true;
        if(pemain == 1){
            for(int i = 0; i < 7; i++){
                if(arrLubang[i] != 0){
                    kosong = false;
                    break;
                }
            }
        } else {
            for(int i = 8; i < 15; i++){
                if(arrLubang[i] != 0){
                    kosong = false;
                    break;
                }
            }
        }
        return kosong;
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
            this.jmlBiji = arrLubang[index];
            arrLubang[index] = 0;
            arrBtn[index].setText(""+arrLubang[index]);
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT);
            int i = index;
            while(this.jmlBiji > 0){
                //mengulang i dari 15 ke 0
                if(i < 15)
                    i += 1;
                else
                    i = 0;

                //mengecek i ada di lubang 8 atau 16
                if(i == 7){ //jika di lubang ke-8
                    if (pemain == 1) {
                        Log.d("biji di tangan sebelum lubang 7", ""+this.jmlBiji);
                        arrLubang[i] += 1;
                        this.jmlBiji -= 1;
                        //delay(i);
                        arrBtn[i].setText(""+arrLubang[i]);
                        Log.d("biji di tangan sesudah lubang 7", ""+this.jmlBiji);
                        break;
                    } else {
                        continue;
                    }
                } else if(i == 15){ //jika di lubang ke-16
                    if(pemain == 1){
                        continue;
                    } else {
                        Log.d("biji di tangan sebelum lubang 15", ""+this.jmlBiji);
                        arrLubang[i] += 1;
                        this.jmlBiji -= 1;
                        //delay(i);
                        arrBtn[i].setText(""+arrLubang[i]);
                        Log.d("biji di tangan sesudah lubang 15", ""+this.jmlBiji);
                        break;
                    }
                } else { //lainnya
                    Log.d("biji di tangan sesudah lubang selain 7 dan 15", ""+this.jmlBiji);
                    arrLubang[i] += 1;
                    this.jmlBiji -= 1;
                    //delay(i);
                    arrBtn[i].setText(""+arrLubang[i]);
                    if(this.jmlBiji == 0 && arrLubang[i] != 1){
                        this.jmlBiji = arrLubang[i];
                        arrLubang[i] = 0;
                        arrBtn[i].setText(""+arrLubang[i]);
                    }
                    if(this.jmlBiji == 0){
                        if(pemain == 1){
                            //Log.v("coba", "test");
                            for(int j = 0; j <= 6; j++){
                                //Log.v("coba", "j => "+j);
                                if(i == j){
                                    arrLubang[7] += arrLubang[14-i];
                                    arrBtn[7].setText(""+arrLubang[7]);
                                    arrLubang[14-i] = 0;
                                    arrBtn[14-i].setText(""+arrLubang[14-i]);
                                    break;
                                }
                            }
                        } else {
                            for (int j = 8; j <= 14; j++) {
                                if (i == j) {
                                    arrLubang[15] += arrLubang[14 - i];
                                    arrBtn[15].setText("" + arrLubang[15]);
                                    arrLubang[14 - i] = 0;
                                    arrBtn[14 - i].setText("" + arrLubang[14 - i]);
                                    break;
                                }
                            }
                        }
                    }
                }
                Log.d("biji di tangan fix selesai", ""+this.jmlBiji);
            } //end while
            if(arrLubang[7] + arrLubang[15] == this.defaultAngka*14){
                this.game = false;
                String hasil = "Seri";
                if(arrLubang[7] > arrLubang[15]){
                    hasil = "Pemain 1 Menang";
                } else if(arrLubang[7] < arrLubang[15]){
                    hasil = "Pemain 2 Menang";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasil)
                        .setCancelable(false)
                        .setPositiveButton("Ulang", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                reset();
                                dialogInterface.cancel();
                            }
                        }).setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Game Over");
                alert.show();

            }
//
//            for(int k = 0; k < 16; k++){
//                Log.d("k-"+k, ""+arrLubang[k]);
//            }

        }
    }
}
