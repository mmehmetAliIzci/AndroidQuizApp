package com.quizapp.sinan.quizapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.quizapp.sinan.quizapplication.Model.Question;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends ArrayAdapter<Question> {

    //construstor method It should consist the Type of Array (Question) that we extend (answer_list)
    ItemAdapter(Context context, List<Question> answer_list){
        super(context,R.layout.custom_row_result,answer_list);

    }

    //returns one row according to position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row_result, parent, false);

        //we bind the answer_list. So the corresponding question for each row
        //have to bind to the view like this ->

        //we've got the question
        Question singleQuestion = getItem(position);
        //we've got the answer
        String answer = QuizActivity.answers.get(position);

        //initialize the Custom_Row's view items..
        TextView question_id = (TextView) customView.findViewById(R.id.question_id_txt);
        TextView given_answer = (TextView) customView.findViewById(R.id.given_answer_txt);
        TextView correct_answer = (TextView) customView.findViewById(R.id.correct_answer_txt);

        //Bind the data with views
        question_id.setText(String.valueOf(singleQuestion.getId()));
        given_answer.setText(answer.toString());
        correct_answer.setText(singleQuestion.getCorrect_answer());

        //return The Row.. Only one.. This process will repeated until
        //all the rows printed to screen
        return  customView;
    }
}
