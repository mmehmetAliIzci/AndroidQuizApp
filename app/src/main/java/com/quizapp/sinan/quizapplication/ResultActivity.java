package com.quizapp.sinan.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.quizapp.sinan.quizapplication.Model.Question;
import com.quizapp.sinan.quizapplication.Model.Score;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    Button confirmation;
    TextView scoreText;
    DbHandler db;
    int i,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = new DbHandler(this,null,null,1);

        scoreText = (TextView) findViewById(R.id.score_txt);
        ListAdapter myAdapter = new ItemAdapter(this,QuizActivity.q_list);
        ListView resultView = (ListView) findViewById(R.id.resultView);
        resultView.setAdapter(myAdapter);
        confirmation = (Button) findViewById(R.id.confirm_btn);



        count = 0;
        for( i = 0; i < QuizActivity.q_list.size(); i++ )
        {
            if( QuizActivity.q_list.get(i).getCorrect_answer().toString().compareToIgnoreCase(QuizActivity.answers.get(i).toString() ) == 0 )
                {
               count++;
            }
        }

        scoreText.setText(String.valueOf(count*10));

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Score temp_score = new Score();
                temp_score.setName(LoginActivity.current_user.getName());
                temp_score.setScore(count * 10);
                db.insertScore(temp_score);
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

    }
}
