package com.neu.coder.mathmodeling.Quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

import java.util.ArrayList;
import java.util.List;


public class DoQuizActivity extends ActionBarActivity {

    private int count;
    private int current;
    private boolean checkWrong;//用来记录是否在查看错题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quiz);

        Bundle bundle = this.getIntent().getExtras();
        String whichQuiz = bundle.getString("whichQuiz");

        int numberOfQuestion = 5;//每套题的题目数量为5个

        //实例化DBService用来管理数据库事务
        DBService dbManager = new DBService();

        //得到当前试题所包含的题目，存入变量questions中
        final List<QuizQuestion> questions = dbManager.getQuestions(whichQuiz, numberOfQuestion);
        final List<QuizQuestion> wrongQuetions = new ArrayList<QuizQuestion>();

        count = numberOfQuestion;//count用来存这套题一共有多少题目
        current = 0;
        checkWrong = false;

        final TextView currentQuestion = (TextView)findViewById(R.id.question);
        final TextView currentExplaination = (TextView)findViewById(R.id.explaination);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final RadioButton[] radioButtons = new RadioButton[4];
        final LinearLayout linearLayoutForExp = (LinearLayout)findViewById(R.id.linearLayoutForExp);

        //四个选项按钮
        radioButtons[0] = (RadioButton)findViewById(R.id.firstAnswer);
        radioButtons[1] = (RadioButton)findViewById(R.id.secondAnswer);
        radioButtons[2] = (RadioButton)findViewById(R.id.thirdAnswer);
        radioButtons[3] = (RadioButton)findViewById(R.id.forthAnswer);

        //上一题与下一题按钮
        final Button nextButton = (Button)findViewById(R.id.nextButton);
        Button preciousButton = (Button)findViewById(R.id.preciousButton);

        //初始显示第一道题目
        QuizQuestion question = questions.get(0);
        currentQuestion.setText("      " + (current+1) + ". " + question.question);
        radioButtons[0].setText("  A. " + question.firstAnswer);
        radioButtons[1].setText("  B. " + question.secondAnswer);
        radioButtons[2].setText("  C. " + question.thirdAnswer);
        radioButtons[3].setText("  D. " + question.forthAnswer);
        currentExplaination.setText(question.expOfAnswer);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                for (int i = 0; i < 4; i++) {
                    radioButtons[i].setBackground(getResources().getDrawable(R.color.color_e8e8e8));
                }

                if (current < count - 1) {

                    if (current == count - 2)
                        nextButton.setText("交卷");

                    current++;
                    QuizQuestion question = questions.get(current);
                    currentQuestion.setText("      " + (current + 1) + ". " + question.question);
                    radioButtons[0].setText("  A. " + question.firstAnswer);
                    radioButtons[1].setText("  B. " + question.secondAnswer);
                    radioButtons[2].setText("  C. " + question.thirdAnswer);
                    radioButtons[3].setText("  D. " + question.forthAnswer);

                    Log.i("喂喂喂喂喂", String.valueOf(checkWrong));
                    Log.i("喂喂喂喂喂", String.valueOf(question.rightOrWrong && checkWrong == true));
                    if (question.rightOrWrong && checkWrong == true) {
                        if (question.chosenAnswer != -1)
                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                        currentExplaination.setText("      你做对了！！！\n      答案解释：" + question.expOfAnswer);
                    } else if (checkWrong == true) {
                        radioButtons[question.correctAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                        if (question.chosenAnswer != -1)
                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_error));
                        currentExplaination.setText("      你做错了！！！\n      答案解释：" + question.expOfAnswer);
                    }

                    radioGroup.clearCheck();

                    //若已回答过该问题，则显示选择的答案
                    if (question.chosenAnswer != -1)
                        radioButtons[question.chosenAnswer].setChecked(true);

                } else if (current == count - 1 && checkWrong == true) {

                    new AlertDialog.Builder(DoQuizActivity.this)
                            .setTitle("提示")
                            .setMessage("已经到达最后一题，是否退出？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DoQuizActivity.this.finish();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                } else {

                    DialogInterface.OnClickListener handIn;//交卷
                    DialogInterface.OnClickListener cancelHandIn = null;//我再检查一下

                    /*
                    交卷后需要对本次考试进行批改，返回分数、批改信息
                     */
                    handIn = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            nextButton.setText("下一题");

                            //得到错题列表
                            final List<Integer> wrongQuestionNumbers = getWrongQuestions(questions);
                            int correctNumber = questions.size() - wrongQuestionNumbers.size();
                            double grade = ((double) correctNumber / (double) questions.size()) * 100;
                            DialogInterface.OnClickListener checkExplaination;//查看错题

                            checkExplaination = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    checkWrong = true;
                                    for (int i = 0; i < wrongQuestionNumbers.size(); i++) {
                                        wrongQuetions.add(questions.get(wrongQuestionNumbers.get(i)));
                                        questions.get(wrongQuestionNumbers.get(i)).rightOrWrong = false;
                                    }

                                    current = 0;
                                    count = questions.size();

                                    QuizQuestion question = questions.get(current);
                                    currentQuestion.setText("      " + (current + 1) + ". " + question.question);
                                    radioButtons[0].setText("  A. " + question.firstAnswer);
                                    radioButtons[1].setText("  B. " + question.secondAnswer);
                                    radioButtons[2].setText("  C. " + question.thirdAnswer);
                                    radioButtons[3].setText("  D. " + question.forthAnswer);
                                    linearLayoutForExp.setVisibility(View.VISIBLE);

                                    if (question.rightOrWrong) {
                                        if (question.chosenAnswer != -1)
                                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                                        currentExplaination.setText("      你做对了！！！\n      答案解释：" + question.expOfAnswer);
                                    } else {
                                        radioButtons[question.correctAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                                        if (question.chosenAnswer != -1)
                                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_error));
                                        currentExplaination.setText("      你做错了！！！\n      答案解释：" + question.expOfAnswer);
                                    }

                                    currentExplaination.setVisibility(View.VISIBLE);
                                }
                            };

                            new AlertDialog.Builder(DoQuizActivity.this)
                                    .setTitle("得分为：" + (int) grade + "分")
                                    .setMessage("答对" + (questions.size() - wrongQuestionNumbers.size()) +
                                            "道，答错" + wrongQuestionNumbers.size() + "道。\n" +
                                            "点击<确定>查看问题解释，点击<取消>退出答题。")
                                    .setPositiveButton("确定", checkExplaination)
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            DoQuizActivity.this.finish();
                                        }
                                    })
                                    .show();

                        }
                    };

                    new AlertDialog.Builder(DoQuizActivity.this)
                            .setTitle("交卷")
                            .setMessage("已经是最后一题，您是否交卷？")
                            .setPositiveButton("交卷", handIn)
                            .setNegativeButton("我再检查一下", cancelHandIn)
                            .show();

                }
            }
        });

        preciousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextButton.setText("下一题");

                for(int i = 0; i < 4; i++){
                    radioButtons[i].setBackground(getResources().getDrawable(R.color.color_e8e8e8));
                }

                if (current > 0) {
                    current--;
                    QuizQuestion question = questions.get(current);
                    currentQuestion.setText("      " + (current + 1) + ". " + question.question);
                    radioButtons[0].setText("  A. " + question.firstAnswer);
                    radioButtons[1].setText("  B. " + question.secondAnswer);
                    radioButtons[2].setText("  C. " + question.thirdAnswer);
                    radioButtons[3].setText("  D. " + question.forthAnswer);
                    currentExplaination.setText(question.expOfAnswer);

                    radioGroup.clearCheck();
                    if (question.chosenAnswer != -1) {
                        radioButtons[question.chosenAnswer].setChecked(true);
                    }

                    if(question.rightOrWrong && checkWrong == true) {
                        if (question.chosenAnswer != -1)
                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                        currentExplaination.setText("      你做对了！！！\n      答案解释：" + question.expOfAnswer);
                    }else if(checkWrong == true){
                        radioButtons[question.correctAnswer].setBackground(getResources().getDrawable(R.color.select_right));
                        if (question.chosenAnswer != -1)
                            radioButtons[question.chosenAnswer].setBackground(getResources().getDrawable(R.color.select_error));
                        currentExplaination.setText("      你做错了！！！\n      答案解释：" + question.expOfAnswer);
                    }
                }else{
                    new AlertDialog.Builder(DoQuizActivity.this)
                                .setTitle("提示")
                                .setMessage("已经是第一题！")
                                .setPositiveButton("确定", null)
                                .show();
                }
            }
        });

        //记录用户所选答案，存入chosenAnswer中
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    if (radioButtons[i].isChecked() == true) {
                        questions.get(current).chosenAnswer = i;
                        break;
                    }
                }
            }
        });

    }

    private List<Integer> getWrongQuestions(List<QuizQuestion> questions){
        List<Integer> wrongQuestions = new ArrayList<Integer>();
        for(int i = 0; i < questions.size(); i++) {
            if(questions.get(i).correctAnswer != questions.get(i).chosenAnswer)
                wrongQuestions.add(i);
        }
        return wrongQuestions;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exam, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
