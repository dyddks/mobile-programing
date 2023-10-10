package com.example.problem03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어");



        RadioButton rb1 = (RadioButton) findViewById(R.id.rb1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rb2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.rb3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.rb4);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);

        Button btn_select = (Button) findViewById(R.id.btn_select);
        Button btn_pause = (Button) findViewById(R.id.btn_pause);
        Button btn_stop = (Button) findViewById(R.id.btn_stop);

        boolean check = getIntent().getBooleanExtra("check" ,false);

        if(check == true){
            btn_pause.setVisibility(View.VISIBLE);
            btn_stop.setVisibility(View.VISIBLE);
        }else{

        }

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);

                if(rg.getCheckedRadioButtonId()==R.id.rb1){
                    i=1;
                }else if(rg.getCheckedRadioButtonId()==R.id.rb2){
                    i=2;
                }else if(rg.getCheckedRadioButtonId()==R.id.rb3){
                    i=3;
                }else if(rg.getCheckedRadioButtonId()==R.id.rb4){
                    i=4;
                }

                if(i!=0){
                    intent.putExtra("i", i);
                    startActivity(intent);
                }
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((PlayActivity)PlayActivity.context).b == true){
                    ((PlayActivity)PlayActivity.context).mPlayer.pause();
                    btn_pause.setText("재생");
                    ((PlayActivity)PlayActivity.context).b = false;
                }else if(((PlayActivity)PlayActivity.context).b == false){
                    ((PlayActivity)PlayActivity.context).mPlayer.start();
                    btn_pause.setText("일시정지");
                    ((PlayActivity)PlayActivity.context).b = true;
                }

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayActivity)PlayActivity.context).mPlayer.stop();
                btn_pause.setVisibility(View.INVISIBLE);
                btn_stop.setVisibility(View.INVISIBLE);
            }
        });
    }
}