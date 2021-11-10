package com.example.duanmot_nhomhai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.QuestionDAO;
import com.example.duanmot_nhomhai.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {
    TextView tvScore, tvQuestionCount, tvLevel, tvTime, tvQuestion;
    RadioGroup rdgAnswer;
    RadioButton rdoAnswer1, rdoAnswer2, rdoAnswer3, rdoAnswer4;
    Button btnNext;
    CountDownTimer countDownTimer;
    public List<Question> questionList;
    private long timeLeft;
    private int questionCount;
    private int questionSize;
    private Question thisQuestion;
    private boolean answer;
    private int score;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvScore = findViewById(R.id.tvScore);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvLevel = findViewById(R.id.tvLevel);
        tvTime = findViewById(R.id.tvTime);
        rdgAnswer = findViewById(R.id.rdgAnswer);
        rdoAnswer1 = findViewById(R.id.rdoAnswer1);
        rdoAnswer2 = findViewById(R.id.rdoAnswer2);
        rdoAnswer3 = findViewById(R.id.rdoAnswer3);
        rdoAnswer4 = findViewById(R.id.rdoAnswer4);
        btnNext = findViewById(R.id.btnNext);

        //Nhận dữ liệu level
        Intent i = getIntent();
        int _topicID = i.getIntExtra("topicID",0);
        String name = i.getStringExtra("option_topic");
        tvLevel.setText("Level: "+name);
        //Danh sách câu hỏi
        QuestionDAO questionDAO = new QuestionDAO(this);
        questionList = questionDAO.getQuestion(_topicID);
        //Tổng danh sách câu hỏi
        questionSize = questionList.size();
        //Vị trí câu hỏi hiện tại
        Collections.shuffle(questionList);
        showQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answer){
                    if(rdoAnswer1.isChecked()||rdoAnswer2.isChecked()||rdoAnswer3.isChecked()||rdoAnswer4.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(QuestionActivity.this, "Hãy chọn một đáp án", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showQuestion();
                }
            }
        });
    }
    //Hiện câu hỏi
    private void showQuestion(){
        rdoAnswer1.setTextColor(Color.BLACK);
        rdoAnswer2.setTextColor(Color.BLACK);
        rdoAnswer3.setTextColor(Color.BLACK);
        rdoAnswer4.setTextColor(Color.BLACK);

        rdgAnswer.clearCheck();
        if(questionCount<questionSize){
            thisQuestion = questionList.get(questionCount);
            tvQuestion.setText(thisQuestion.getQuestion());
            rdoAnswer1.setText(thisQuestion.getAnswer1());
            rdoAnswer2.setText(thisQuestion.getAnswer2());
            rdoAnswer3.setText(thisQuestion.getAnswer3());
            rdoAnswer4.setText(thisQuestion.getAnswer4());

            questionCount++;

            tvQuestionCount.setText("Question: "+questionCount+"/"+questionSize);
            answer = false;
            btnNext.setText("Xác nhận");
            //thời gian đếm ngược
            timeLeft = 10000;
            startTimeLeft();
        }else {
            finishQuestion();
        }
    }

    //Đồng hồ đếm ngược
    private void startTimeLeft() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDown();
                checkAnswer();
            }
        }.start();
    }

    //Kiểm tra đáp án
    private void checkAnswer(){
        answer = true;
        RadioButton rdoSelect = findViewById(rdgAnswer.getCheckedRadioButtonId());
        int _answer = rdgAnswer.indexOfChild(rdoSelect) + 1;
        if(_answer == thisQuestion.getAnswer()){
            score = score + 1;
            tvScore.setText("Score: "+score);
        }
        showAnswer();
    }

    //Hiện đáp án
    private void showAnswer() {
        rdoAnswer1.setTextColor(Color.RED);
        rdoAnswer2.setTextColor(Color.RED);
        rdoAnswer3.setTextColor(Color.RED);
        rdoAnswer4.setTextColor(Color.RED);
        switch (thisQuestion.getAnswer()){
            case 1:
                rdoAnswer1.setTextColor(Color.GREEN);
                tvQuestion.setText("Đáp án là A");
                break;
            case 2:
                rdoAnswer2.setTextColor(Color.GREEN);
                tvQuestion.setText("Đáp án là B");
                break;
            case 3:
                rdoAnswer3.setTextColor(Color.GREEN);
                tvQuestion.setText("Đáp án là C");
                break;
            case 4:
                rdoAnswer4.setTextColor(Color.GREEN);
                tvQuestion.setText("Đáp án là D");
                break;
        }
        if(questionCount<questionSize){
            btnNext.setText("NEXT");
        }else {
            btnNext.setText("COMPLETE");
        }
        countDownTimer.cancel();
    }

    //Cập nhật thời gian
    private void updateCountDown(){
        int minutes = (int) ((timeLeft/1000)/60);
        int seconds =  (int) ((timeLeft/1000)%60);
        String time = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tvTime.setText(time);
        if(timeLeft<5000){
            tvTime.setTextColor(Color.RED);
        }else {
            tvTime.setTextColor(Color.BLACK);
        }
    }

    private void finishQuestion(){
        Intent i = new Intent();
        i.putExtra("score",score);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    public void onBackPressed() {
        count++;
        if(count>=1){
            finishQuestion();
        }
        count=0;
    }
}