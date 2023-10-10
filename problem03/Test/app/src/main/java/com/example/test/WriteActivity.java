package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        EditText edit_title = (EditText) findViewById(R.id.edit_title);
        EditText edit1 = (EditText) findViewById(R.id.edit1);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        Button btn_back = (Button) findViewById(R.id.btn_back);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = edit_title.getText().toString();
                String c = edit1.getText().toString();
                try {
                    FileOutputStream out = openFileOutput(t + ".txt", Context.MODE_PRIVATE);
                    out.write(c.getBytes());
                    out.close();
                    Toast.makeText(getApplicationContext(), t+"가 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity
                startActivity(intent); // 메모를 작성하고 결과값을 받기 위해
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity

            }
        });
    }

}