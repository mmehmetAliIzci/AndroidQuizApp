package com.quizapp.sinan.quizapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    List myScore= new ArrayList();
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbHandler = new DbHandler(this,null,null,1);

        myScore = dbHandler.returnScores();

        ListAdapter myAdapter = new ItemAdapterForScore(this,myScore);
        ListView resultView = (ListView)findViewById(R.id.scoreView);
        resultView.setAdapter(myAdapter);


    }
}
