package com.example.problem03;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class PlayActivity extends AppCompatActivity {
    static public Context context;
    MediaPlayer mPlayer;
    boolean b = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        context = this;

        ImageView img = (ImageView) findViewById(R.id.img);
        Button btn_pause = (Button) findViewById(R.id.btn_pause);
        Button btn_stop = (Button) findViewById(R.id.btn_stop);
        Button btn_back = (Button) findViewById(R.id.btn_back);

        TextView music_name = (TextView) findViewById(R.id.music_name);

        SeekBar sb_music = (SeekBar) findViewById(R.id.sb_music);
        TextView tv_music = (TextView) findViewById(R.id.tv_music);

        int i = getIntent().getIntExtra("i", 0);

        if (i == 1) {
            img.setImageResource(R.drawable.album1);
            mPlayer = MediaPlayer.create(this,R.raw.song1);
            music_name.setText("실행중인 음악 : song1.txt");
            mPlayer.start();
        }else if(i==2){
            img.setImageResource(R.drawable.album2);
            mPlayer = MediaPlayer.create(this,R.raw.song2);
            music_name.setText("실행중인 음악 : song2.txt");
            mPlayer.start();
        }else if(i==3){
            img.setImageResource(R.drawable.album3);
            mPlayer = MediaPlayer.create(this,R.raw.song3);
            music_name.setText("실행중인 음악 : song3.txt");
            mPlayer.start();
        }else if(i==4){
            img.setImageResource(R.drawable.album4);
            mPlayer = MediaPlayer.create(this,R.raw.song4);
            music_name.setText("실행중인 음악 : song4.txt");
            mPlayer.start();
        }

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b == true){
                    mPlayer.pause();
                    btn_pause.setText("재생");
                    b = false;
                }else if(b == false){
                    mPlayer.start();
                    btn_pause.setText("일시정지");
                    b = true;
                    new Thread(){
                        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                        public void run(){
                            if(mPlayer == null) return;
                            sb_music.setMax(mPlayer.getDuration());
                            while(mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        sb_music.setProgress(mPlayer.getCurrentPosition());
                                        tv_music.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }
                    }.start();
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                intent.putExtra("check", true);
                startActivity(intent);

            }
        });
        new Thread(){
            SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
            public void run(){
                if(mPlayer == null) return;
                sb_music.setMax(mPlayer.getDuration());
                while(mPlayer.isPlaying()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sb_music.setProgress(mPlayer.getCurrentPosition());
                            tv_music.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                        }
                    });
                    SystemClock.sleep(200);
                }
            }
        }.start();
    }
}
