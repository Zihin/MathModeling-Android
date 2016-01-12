package com.neu.coder.mathmodeling.Quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2016/1/1
 */
public class DBService {
    private SQLiteDatabase db;

    public DBService()
    {
        db = SQLiteDatabase.openDatabase("/data/data/com.neu.coder.mathmodeling/databases/question.db",
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public List<QuizQuestion> getQuestions(String whichQuiz, int numberOfQuestion)
    {

        int startNumber = (Integer.parseInt(whichQuiz) - 1) * numberOfQuestion;
        int count = (Integer.parseInt(whichQuiz)) * numberOfQuestion;
        Log.i("喂喂喂喂喂", String.valueOf(startNumber));

        List<QuizQuestion> list = new ArrayList<QuizQuestion>();
        Log.d("QuizActivity", db.getPath());
        Cursor cursor = db.rawQuery("select * from question", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
//            int count = cursor.getCount();
            for(int i = startNumber; i < count; i++) {
                cursor.moveToPosition(i);
                QuizQuestion question = new QuizQuestion();
                question.question = cursor.getString(cursor.getColumnIndex("question"));
                question.answerA = cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB = cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC = cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD = cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer = cursor.getInt(cursor.getColumnIndex("answer"));
                question.ID = cursor.getInt(cursor.getColumnIndex("ID"));
                question.explaination = cursor.getString(cursor.getColumnIndex("explaination"));
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;
    }

}
