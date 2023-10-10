package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Button btn_back = (Button) findViewById(R.id.btn_back);
        TextView text_title = (TextView) findViewById(R.id.text_title);
        TextView text1 = (TextView) findViewById(R.id.text1);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadActivity.this, MainActivity.class);
                startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity
            }
        });

        String title = getIntent().getStringExtra("title");
        try {
            FileInputStream in = openFileInput(title);
            byte[] txt = new byte[30];
            in.read(txt);
            String c = new String(txt);
            text_title.setText(title);
            text1.setText(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
