package com.example.aris.congklakgame2;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends Activity implements View.OnClickListener {

    int[] arrLubang = new int[16];
    int gilPemail = 1;
    Button[] arrBtn = new Button[16];
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

        btnBack.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        for(int i = 0; i < arrLubang.length; i++){
            arrBtn[i].setOnClickListener(this);
        }
        reset();
    }

    ///////////////////////////////////////////////////////////
    //Reset Game
    public void reset(){
        for(int i = 0; i < arrLubang.length; i++){
            arrLubang[i] = 7;
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
                    lubangDipilih(i, gilPemail);
                    if (gilPemail == 1)
                        this.gilPemail = 0;
                    else
                        this.gilPemail = 1;
                }
            }
        }
    }

    public void lubangDipilih(int index, int pemain){
        if(index != 7 || index != 15){
            int jmlBiji = arrLubang[index];
            arrLubang[index] = 0;
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
                    } else {
                        continue;
                    }
                } else if(i == 15){ //jika di lubang ke-16
                    if(pemain == 1){
                        continue;
                    } else {
                        arrLubang[i] += 1;
                        jmlBiji -= 1;
                    }
                } else { //lainnya
                    arrLubang[i] += 1;
                    jmlBiji -= 1;
                    if(jmlBiji == 0 && arrLubang[i] != 1){
                        jmlBiji = arrLubang[i];
                        arrLubang[i] = 0;
                    }
                }
            }

            for(i = 0; i < arrLubang.length; i++){
                arrBtn[i].setText(""+arrLubang[i]);
            }
        }
    }
}
