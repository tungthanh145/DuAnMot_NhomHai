package com.example.duanmot_nhomhai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.duanmot_nhomhai.R;

public class QuestionActivity extends AppCompatActivity {
    TextView tvScore, tvQuestionCount, tvLevel, tvTime, tvQuestion;
    RadioGroup rdgAnswer;
    RadioButton rdoAnswer1, rdoAnswer2, rdoAnswer3, rdoAnswer4;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }
}