package com.neu.coder.mathmodeling.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        final TextView[] textViews = new TextView[7];
        textViews[0] = (TextView)findViewById(R.id.textView1);
        textViews[1] = (TextView)findViewById(R.id.textView2);
        textViews[2] = (TextView)findViewById(R.id.textView3);
        textViews[3] = (TextView)findViewById(R.id.textView4);
        textViews[4] = (TextView)findViewById(R.id.textView5);
        textViews[5] = (TextView)findViewById(R.id.textView6);
        textViews[6] = (TextView)findViewById(R.id.textView7);

        final Button[] buttons = new Button[1];
        buttons[0] = (Button)findViewById(R.id.button);

        final ImageButton[] imageButtons = new ImageButton[7];
        imageButtons[0] = (ImageButton)findViewById(R.id.imageButton1);
        imageButtons[1] = (ImageButton)findViewById(R.id.imageButton2);
        imageButtons[2] = (ImageButton)findViewById(R.id.imageButton3);
        imageButtons[3] = (ImageButton)findViewById(R.id.imageButton4);
        imageButtons[4] = (ImageButton)findViewById(R.id.imageButton5);
        imageButtons[5] = (ImageButton)findViewById(R.id.imageButton6);
        imageButtons[6] = (ImageButton)findViewById(R.id.imageButton7);

        for(int i = 0; i < 7; i++){
            textViews[i].setVisibility(View.INVISIBLE);
            imageButtons[i].setVisibility(View.INVISIBLE);
        }
        buttons[0].setClickable(true);

        String path = "/data/data/com.neu.coder.mathmodeling/databases/";
        String name = "question.db";

        //第一次运行程序时，将assets文件夹中的question.db文件读入到path+name组成的文件中
        if((new File(path + name).exists()) == false) {
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdir();

            try {
                InputStream is = getBaseContext().getAssets().open(name);
                OutputStream os = new FileOutputStream(path + name);
                byte[] buffer = new byte[1024];
                int length;

                while((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttons[0].setClickable(false);

                for(int i = 0; i < 7; i++){
                    textViews[i].setVisibility(View.VISIBLE);
                    imageButtons[i].setVisibility(View.VISIBLE);
                }
//                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
//                startActivity(intent);
            }
        });

        ImageButton imb1 = (ImageButton)findViewById(R.id.imageButton1);
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb2 = (ImageButton)findViewById(R.id.imageButton2);
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb3 = (ImageButton)findViewById(R.id.imageButton3);
        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb4 = (ImageButton)findViewById(R.id.imageButton4);
        imb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb5 = (ImageButton)findViewById(R.id.imageButton5);
        imb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "5");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb6 = (ImageButton)findViewById(R.id.imageButton6);
        imb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "6");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton imb7 = (ImageButton)findViewById(R.id.imageButton7);
        imb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, DoQuizActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("whichQuiz", "7");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
