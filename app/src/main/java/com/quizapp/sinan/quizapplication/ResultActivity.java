package com.quizapp.sinan.quizapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListAdapter myAdapter = new ItemAdapter(this,QuizActivity.q_list);
        ListView resultView = (ListView) findViewById(R.id.resultView);
        resultView.setAdapter(myAdapter);
    }
}
