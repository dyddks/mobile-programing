package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 메모장");

        ListView list_memo = (ListView) findViewById(R.id.list_memo);
        ArrayList<String> memoList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, memoList);
        list_memo.setAdapter(adapter);

        Button btn_write = (Button) findViewById(R.id.btn_write);

        File file = new File("/data/data/com.example.test/files");
        File fileList[] = file.listFiles();

        for(int i=0; i<fileList.length; i++){
            memoList.add(fileList[i].getName());
        }
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                //startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity
                startActivity(intent); // 메모를 작성하고 결과값을 받기 위해

            }
        });

        list_memo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object ob = (Object) adapterView.getAdapter().getItem(i);
                String t = ob.toString();

                Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                //startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity
                intent.putExtra("title", t);
                startActivity(intent);


            }
        });
    }
}
