package com.example.duanmot_nhomhai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.TopicDAO;
import com.example.duanmot_nhomhai.database.DbHelper;
import com.example.duanmot_nhomhai.model.Topic;

import java.util.ArrayList;
import java.util.List;

//Màn hình chơi game
public class GameActivity extends AppCompatActivity {
    Spinner spnLevel;
    Button btnPlay;
    public static final int REQUEST_CODE_QUESTION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        spnLevel = findViewById(R.id.spnLevel);
        btnPlay = findViewById(R.id.btnPlay);

        showLevel();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestion();
            }
        });
    }
    //Hiển thị các level
    public void showLevel(){
        TopicDAO topicDAO = new TopicDAO(this);
        List<Topic> topic = topicDAO.getTopic();

        ArrayAdapter<Topic> topicAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, topic);
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLevel.setAdapter(topicAdapter);
    }
    //Hàm bắt đầu câu hỏi
    public void startQuestion(){
        Topic topic = (Topic) spnLevel.getSelectedItem();
        int topicID = topic.getId();
        String option_topic = topic.getName();

        Intent i = new Intent(GameActivity.this, QuestionActivity.class);
        i.putExtra("topicID", topicID);
        i.putExtra("option_topic", option_topic);
        startActivityForResult(i, REQUEST_CODE_QUESTION);
    }
}