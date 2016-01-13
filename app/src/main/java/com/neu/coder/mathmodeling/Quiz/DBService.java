package com.neu.coder.mathmodeling.Quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<QuizQuestion> getQuestions(String whichQuiz, int numberOfQuestion) {

        int startNumber = (Integer.parseInt(whichQuiz) - 1) * numberOfQuestion;
        int count = (Integer.parseInt(whichQuiz)) * numberOfQuestion;

        List<QuizQuestion> list = new ArrayList<QuizQuestion>();

        Cursor cursor = db.rawQuery("select * from question", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            if(whichQuiz.equals("0")){

                int random;

                for(int i = 0; i < 5; i++) {
                    random = (int)(Math.random() * cursor.getCount());
                    cursor.moveToPosition(random);
                    QuizQuestion question = new QuizQuestion();
                    question.question = cursor.getString(cursor.getColumnIndex("question"));
                    question.firstAnswer = cursor.getString(cursor.getColumnIndex("answerA"));
                    question.secondAnswer = cursor.getString(cursor.getColumnIndex("answerB"));
                    question.thirdAnswer = cursor.getString(cursor.getColumnIndex("answerC"));
                    question.forthAnswer = cursor.getString(cursor.getColumnIndex("answerD"));
                    question.correctAnswer = cursor.getInt(cursor.getColumnIndex("answer"));
                    question.id = cursor.getInt(cursor.getColumnIndex("ID"));
                    question.expOfAnswer = cursor.getString(cursor.getColumnIndex("explaination"));
                    question.chosenAnswer = -1;
                    list.add(question);
                }
            }else{
                for(int i = startNumber; i < count; i++) {
                    cursor.moveToPosition(i);
                    QuizQuestion question = new QuizQuestion();
                    question.question = cursor.getString(cursor.getColumnIndex("question"));
                    question.firstAnswer = cursor.getString(cursor.getColumnIndex("answerA"));
                    question.secondAnswer = cursor.getString(cursor.getColumnIndex("answerB"));
                    question.thirdAnswer = cursor.getString(cursor.getColumnIndex("answerC"));
                    question.forthAnswer = cursor.getString(cursor.getColumnIndex("answerD"));
                    question.correctAnswer = cursor.getInt(cursor.getColumnIndex("answer"));
                    question.id = cursor.getInt(cursor.getColumnIndex("ID"));
                    question.expOfAnswer = cursor.getString(cursor.getColumnIndex("explaination"));
                    question.chosenAnswer = -1;
                    list.add(question);
                }
            }
        }
        return list;
    }

}
