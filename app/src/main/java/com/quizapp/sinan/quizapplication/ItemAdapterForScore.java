package com.quizapp.sinan.quizapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quizapp.sinan.quizapplication.Model.Score;

import java.util.List;

public class ItemAdapterForScore extends ArrayAdapter<Score> {

       ItemAdapterForScore(Context context, List<Score> scoreList){
            super(context,R.layout.custom_row_score,scoreList);
        }

    //returns one row according to position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row_score, parent, false);

        //we bind the answer_list. So the corresponding question for each row
        //have to bind to the view like this ->

        //we've got the score
        Score singleScore = getItem(position);


        //initialize the Custom_Row's view items..
        TextView user_name = (TextView) customView.findViewById(R.id.user_name_txt);
        TextView user_score = (TextView) customView.findViewById(R.id.user_score_txt);


        //Bind the data with views
        user_name.setText(singleScore.getName());
        user_score.setText(String.valueOf(singleScore.getScore()));

        //return The Row.. Only one.. This process will repeated until
        //all the rows printed to screen
        return  customView;
    }

}
