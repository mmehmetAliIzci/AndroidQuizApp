package com.quizapp.sinan.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView splash;
    Button start,score;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash = (TextView) findViewById(R.id.splash_txt);
        start = (Button) findViewById(R.id.start_btn);
        score = (Button) findViewById(R.id.score_btn);

        splash.setText(LoginActivity.current_user.getName());

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QuizActivity.class));
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScoreActivity.class));
            }
        });


    }
}
