package com.example.a1;

import static android.graphics.Color.BLACK;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //메뉴 선택에서 모양 고르는 변수
    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3, CURVE=4;
    static int curShape = LINE;
    static int color = BLACK;
    static int tcolor = 0;
    static int width = 10;
    static boolean emoboss=false, blur=false;
    MyGraphicView myView;
    Button btn[];
    EmbossMaskFilter embossMaskFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = new MyGraphicView(this);
        ((LinearLayout) findViewById(R.id.linear)).addView(myView);

        btn = new Button[10];
        btn[0] = findViewById(R.id.btn1);
        btn[1] = findViewById(R.id.btn2);
        btn[2] = findViewById(R.id.btn3);
        btn[3] = findViewById(R.id.btn4);
        btn[4] = findViewById(R.id.btn5);
        btn[5] = findViewById(R.id.btn6);
        btn[6] = findViewById(R.id.btn7);
        btn[7] = findViewById(R.id.btn8);

        for(int i=0; i<8; i++){
            btn[i].setTextColor(BLACK);
        }
        btn[0].setTextColor(Color.RED);

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                width=10;
                curShape = LINE;
                color = BLACK;
                for(int i=0;i<5;i++){
                    btn[i].setTextColor(BLACK);
                }
                btn[0].setTextColor(Color.RED);
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                width=10;
                curShape = RECTANGLE;
                color = BLACK;
                for(int i=0;i<5;i++){
                    btn[i].setTextColor(BLACK);
                }
                btn[1].setTextColor(Color.RED);
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                width=10;
                curShape = CIRCLE;
                color = BLACK;
                for(int i=0;i<5;i++){
                    btn[i].setTextColor(BLACK);
                }
                btn[2].setTextColor(Color.RED);
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                width=10;
                curShape = CURVE;
                color = BLACK;
                for(int i=0;i<5;i++){
                    btn[i].setTextColor(BLACK);
                }
                btn[3].setTextColor(Color.RED);
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.WHITE;
                curShape = CURVE;
                width = 40;
                for(int i=0;i<5;i++){
                    btn[i].setTextColor(BLACK);
                }
                btn[4].setTextColor(Color.RED);
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.clear();
            }
        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emoboss){
                    emoboss = false;
                    blur=false;
                    btn[6].setTextColor(BLACK);
                }
                else{
                    emoboss =true;
                    blur=false;
                    btn[6].setTextColor(Color.RED);
                    btn[7].setTextColor(BLACK);
                }
            }
        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blur){
                    emoboss = false;
                    blur = false;
                    btn[7].setTextColor(BLACK);
                }
                else{
                    emoboss =false;
                    blur=true;
                    btn[7].setTextColor(Color.RED);
                    btn[6].setTextColor(BLACK);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("색상 변경 >> ");
        subMenu.add(0,1,0, "빨강");
        subMenu.add(0,2,0, "초록");
        subMenu.add(0,3,0, "파랑");
        subMenu.add(0,4,0, "검정");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                if(color == Color.WHITE){}
                else{color = Color.RED;}
                return true;
            case 2:
                if(color == Color.WHITE){}
                else{color = Color.GREEN;}
                return true;
            case 3:
                if(color == Color.WHITE){}
                else{color = Color.BLUE;}
                return true;
            case 4:
                if(color == Color.WHITE){}
                else{color = Color.BLACK;}
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        MyGraphicView.Myshape Shape = null;
        ArrayList<MyGraphicView.Myshape> MyshapeArrayList = new ArrayList<>();

        public MyGraphicView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            for (Myshape currentShape : MyshapeArrayList) {
                paint.setColor(currentShape.color);
                drawShape(currentShape, canvas, paint);
            }

            if (Shape != null) {
                drawShape(Shape, canvas, paint);
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Shape = new Myshape(curShape);
                    Shape.color = color;
                    Shape.width = width;
                    Shape.blur = blur;
                    Shape.emboss = emoboss;
                    if(emoboss == true){
                        new EmbossMaskFilter(new float[]{1, 1, 1}, 2, 5, 3);

                    }else if(blur == true){
                        new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL);
                    }
                    Shape.startX = (int)event.getX();
                    Shape.startY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Shape.stopX = (int)event.getX();
                    Shape.stopY = (int)event.getY();
                    float tmp1 = Shape.stopX;
                    float tmp2 = Shape.stopY;
                    if(curShape == CURVE){
                        MyshapeArrayList.add(Shape);
                        Shape = null;
                        Shape = new Myshape(curShape);
                        Shape.width = width;
                        Shape.color = color;
                        Shape.blur = blur;
                        Shape.emboss = emoboss;
                        Shape.startX = (int)tmp1;
                        Shape.startY = (int)tmp2;
                        Shape.stopX = (int)event.getX();
                        Shape.stopY = (int)event.getY();
                        //System.out.println(tmp1+" "+tmp2+" "+MyshapeArrayList.size());
                    }
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if(curShape != CURVE){
                        Shape.stopX = (int)event.getX();
                        Shape.stopY = (int)event.getY();
                        MyshapeArrayList.add(Shape);
                    }
                    Shape = null;
                    this.invalidate();
                    break;
            }
            return true;
        }
        private void drawShape(Myshape currentShape, Canvas canvas, Paint paint) {
            switch (currentShape.shape) {
                case LINE:
                    canvas.drawLine(currentShape.startX, currentShape.startY,
                            currentShape.stopX, currentShape.stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(currentShape.stopX - currentShape.startX, 2) +
                            Math.pow(currentShape.stopY - currentShape.startY, 2));
                    canvas.drawCircle(currentShape.startX, currentShape.startY, radius, paint);
                    break;
                case RECTANGLE:
                    Rect rect = new Rect(currentShape.startX, currentShape.startY,
                            currentShape.stopX, currentShape.stopY);
                    canvas.drawRect(rect, paint);
                    break;
                case CURVE:
                    System.out.println(currentShape.startX+" "+ currentShape.startY+" "+
                            currentShape.stopX+" "+ currentShape.stopY);
                    canvas.drawLine(currentShape.startX, currentShape.startY,
                            currentShape.stopX, currentShape.stopY, paint);
                    break;
            }
        }
        //그린 도형들을 저장해 줄 때 필요한 함수를 만들어줬다.
        private static class Myshape {
            int shape, startX, startY, stopX, stopY, color, width;
            boolean blur, emboss;
            public Myshape(int shape) {
                this.shape = shape;
            }
        }
        public void clear(){
            MyshapeArrayList.clear();
            this.invalidate();
        }
    }
}