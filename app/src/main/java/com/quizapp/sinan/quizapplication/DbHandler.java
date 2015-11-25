package com.quizapp.sinan.quizapplication;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;
import android.content.Context;
import android.content.ContentValues;

import com.quizapp.sinan.quizapplication.Model.Person;
import com.quizapp.sinan.quizapplication.Model.Question;
import com.quizapp.sinan.quizapplication.Model.Score;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends  SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quiz.db";

    public static final String TABLE_PEOPLE = "people";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_BIRTHDAY = "birthday";

    public static final String TABLE_QUESTION = "question";
    public static final String COLUMN_QUESTION_BODY = "question_body";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";
    public static final String COLUMN_FIRST_ANSWER = "first_answer";
    public static final String COLUMN_SECOND_ANSWER= "second_answer";
    public static final String COLUMN_THIRD_ANSWER = "third_answer";
    public static final String COLUMN_FORTH_ANSWER = "forth_answer";

    public static final String TABLE_SCORE = "score";
    public static final String COLUMN_PERSON_ID= "person_id";
    public static final String COLUMN_SCORE = "score";
    //public static final String COLUMN_NAME = "name";
    public static  List<Question> returnable;


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PEOPLE + "("
                +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME +" TEXT, "
                + COLUMN_SURNAME +" TEXT, "
                + COLUMN_USERNAME +" TEXT, "
                + COLUMN_PASSWORD +" TEXT, "
                + COLUMN_BIRTHDAY +" TEXT "+ ");";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_QUESTION + "("
                +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTION_BODY +" TEXT, "
                + COLUMN_CORRECT_ANSWER +" TEXT, "
                + COLUMN_FIRST_ANSWER +" TEXT, "
                + COLUMN_SECOND_ANSWER +" TEXT, "
                + COLUMN_THIRD_ANSWER +" TEXT, "
                + COLUMN_FORTH_ANSWER +" TEXT "+ ");";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_SCORE + "("
                + COLUMN_SCORE +" INTEGER, "
                + COLUMN_NAME +" TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }

    public boolean insertPerson(Person person){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,person.getName());
        values.put(COLUMN_SURNAME,person.getSurname());
        values.put(COLUMN_USERNAME, person.getUsername());
        values.put(COLUMN_PASSWORD, person.getPassword());
        values.put(COLUMN_BIRTHDAY, person.getBirthday());
        SQLiteDatabase db = getWritableDatabase();
        if (db.insert(TABLE_PEOPLE, null, values)!= -1){
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    public void insertScore(Score score){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());
        values.put(COLUMN_NAME, score.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SCORE, null, values);
        db.close();

    }

    public void insertQuestion(Question question){
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,question.getQuestion_body());
        values.put(COLUMN_CORRECT_ANSWER,question.getCorrect_answer());
        values.put(COLUMN_FIRST_ANSWER,question.getFirst_answer());
        values.put(COLUMN_SECOND_ANSWER,question.getSecond_answer());
        values.put(COLUMN_THIRD_ANSWER,question.getThird_answer());
        values.put(COLUMN_FORTH_ANSWER,question.getForth_answer());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    public List<Question> returnQuestions(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_QUESTION + " WHERE 1";
        returnable = new ArrayList<Question>();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Question temp_question = new Question();
            if (c.getString(c.getColumnIndex(COLUMN_QUESTION_BODY))!= null){
                temp_question.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                temp_question.setQuestion_body(c.getString(c.getColumnIndex(COLUMN_QUESTION_BODY)));
                temp_question.setCorrect_answer(c.getString(c.getColumnIndex(COLUMN_CORRECT_ANSWER)));
                temp_question.setFirst_answer(c.getString(c.getColumnIndex(COLUMN_FIRST_ANSWER)));
                temp_question.setSecond_answer(c.getString(c.getColumnIndex(COLUMN_SECOND_ANSWER)));
                temp_question.setThird_answer(c.getString(c.getColumnIndex(COLUMN_THIRD_ANSWER)));
                temp_question.setForth_answer(c.getString(c.getColumnIndex(COLUMN_FORTH_ANSWER)));
            }
            returnable.add(temp_question);
            c.moveToNext();
        }
        db.close();
        return returnable;
    }

    public List<Score> returnScores(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCORE ;
        ArrayList<Score> returnable = new ArrayList<>();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            Score temp_score = new Score();
            if (c.getString(c.getColumnIndexOrThrow(COLUMN_SCORE))!= null){
                temp_score.setScore(c.getInt(c.getColumnIndex(COLUMN_SCORE)));
                temp_score.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
            }
            returnable.add(temp_score);
            c.moveToNext();
        }
        db.close();
        return returnable;

    }

    public Person checkCredits(String username , String password){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PEOPLE + " WHERE " + COLUMN_USERNAME + "=\"" + username
                + "\"" + " AND " + COLUMN_PASSWORD + " =\"" + password + "\";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex(COLUMN_NAME))!= null){
                Person conn_user = new Person();
                conn_user.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                conn_user.setSurname(c.getString(c.getColumnIndex(COLUMN_SURNAME)));
                conn_user.setUsername(c.getString(c.getColumnIndex(COLUMN_USERNAME)));
                conn_user.setBirthday(c.getString(c.getColumnIndex(COLUMN_BIRTHDAY)));
                return conn_user;
            }
        }

        return null;
    }
    public void createQuestions(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"1.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"2.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER, "D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"3.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);

        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"4.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);

        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"5.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"6.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"7.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"8.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);

        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"9.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);


        values = new ContentValues();
        values.put(COLUMN_QUESTION_BODY,"10.question");
        values.put(COLUMN_CORRECT_ANSWER,"A");
        values.put(COLUMN_FIRST_ANSWER,"A");
        values.put(COLUMN_SECOND_ANSWER,"B");
        values.put(COLUMN_THIRD_ANSWER,"C");
        values.put(COLUMN_FORTH_ANSWER,"D");
        db = getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }
}
