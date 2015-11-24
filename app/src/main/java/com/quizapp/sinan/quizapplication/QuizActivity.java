package com.quizapp.sinan.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.quizapp.sinan.quizapplication.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView question_body;
    Button next_btn;
    DbHandler dbHandler;
    public static List<Question> q_list = null;
    public static ArrayList<String> answers = new ArrayList<>();
    String selected_answer = "";
    RadioButton fst,scnd,thrd,forth;
    int iterator = 0;
    int question_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question_body = (TextView) findViewById(R.id.question_body_text);
        dbHandler = new DbHandler(this,null,null,1);
        q_list = dbHandler.returnQuestions();
        question_count = q_list.size();

        fst = (RadioButton) findViewById(R.id.first_answer);
        scnd = (RadioButton) findViewById(R.id.sec_answer);
        thrd = (RadioButton) findViewById(R.id.third_answer);
        forth = (RadioButton) findViewById(R.id.forth_answer);

        next_btn = (Button) findViewById(R.id.next_btn);

        fst.setVisibility(View.INVISIBLE);
        scnd.setVisibility(View.INVISIBLE);
        thrd.setVisibility(View.INVISIBLE);
        forth.setVisibility(View.INVISIBLE);


        next_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(iterator < question_count){
                    answers.add(selected_answer);
                    LoadQuestion(iterator);
                    iterator++;
                }
                else {
                    startActivity(new Intent(QuizActivity.this,ResultActivity.class));
                }

            }
        });

    }

    public void onRadioButtonClicked (View view){
        switch (view.getId()) {
            case R.id.first_answer:
                Toast.makeText(this,"Clicked 1",Toast.LENGTH_SHORT).show();
                selected_answer = "A";

                break;
            case R.id.sec_answer:
                Toast.makeText(this,"Clicked 2",Toast.LENGTH_SHORT).show();
                selected_answer = "B";

                break;
            case R.id.third_answer:
                Toast.makeText(this,"Clicked 3",Toast.LENGTH_SHORT).show();
                selected_answer = "C";

                break;
            case R.id.forth_answer:
                Toast.makeText(this,"Clicked 4",Toast.LENGTH_SHORT).show();
                selected_answer = "D";

                break;
        }
    }

    public void LoadQuestion(int it){
        fst.setVisibility(View.VISIBLE);
        scnd.setVisibility(View.VISIBLE);
        thrd.setVisibility(View.VISIBLE);
        forth.setVisibility(View.VISIBLE);
        Question temp_question = new Question();
        temp_question = q_list.get(it);
        question_body.setText(temp_question.getQuestion_body());
        fst.setText(temp_question.getFirst_answer());
        fst.setChecked(false);
        scnd.setText(temp_question.getSecond_answer());
        scnd.setChecked(false);
        thrd.setText(temp_question.getThird_answer());
        thrd.setChecked(false);
        forth.setText(temp_question.getForth_answer());
        forth.setChecked(false);

    }
}
