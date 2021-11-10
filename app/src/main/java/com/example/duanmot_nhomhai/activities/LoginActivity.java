package com.example.duanmot_nhomhai.activities;

import static com.example.duanmot_nhomhai.uitilities.Constants.ID_USER;
import static com.example.duanmot_nhomhai.uitilities.Constants.PASSWORD;
import static com.example.duanmot_nhomhai.uitilities.Constants.ROLE_ADMIN;
import static com.example.duanmot_nhomhai.uitilities.Constants.ROLE_ADMINS;
import static com.example.duanmot_nhomhai.uitilities.Constants.SCORE;
import static com.example.duanmot_nhomhai.uitilities.Constants.USER_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.UserDAO;
import com.example.duanmot_nhomhai.model.User;
import com.example.duanmot_nhomhai.services.UserService;

import java.util.List;

//Màn hình login
public class LoginActivity extends AppCompatActivity {
    private EditText edtUsernameLogin, edtPasswordLogin;
    private Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        //   readLoginStatus();

        edtUsernameLogin = findViewById(R.id.edtUserName);
        edtPasswordLogin = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtUsernameLogin.getText().toString().trim();
                String password = edtPasswordLogin.getText().toString().trim();
                User user = new User(-1,-1,username,password,"");
                if (user.getName().equals(username) && user.getPass().equals(password)){
                    Intent intent = new Intent(LoginActivity.this, UserService.class);
                    intent.setAction(UserService.ACTION_LOGIN);
                    intent.putExtra("user", user);
                    startService(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                } else {
                    Toast.makeText( LoginActivity.this, "Login Fails !", Toast.LENGTH_SHORT ).show();
                }
                return;
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(UserService.EVENT_COURSE_SERVICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String action = intent.getStringExtra("action");
                Log.e( "action", " "+action );
                if (UserService.ACTION_LOGIN.equals(action)){
                    Log.e( "ko action", "koaction" );
                    User user = (User) intent.getSerializableExtra("result");
                    if (user == null){
                        Log.e( "user", "null" );
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ID_USER = user.getId();
                    USER_NAME = user.getName();
                    PASSWORD = user.getPass();
                    SCORE = user.getScore();
                    Log.e( "username",""+user.getName() );
                    Log.e( "passs", ""+user.getPass() );
                    Log.e( "userId", ""+user.getId() );
                    Log.e( "score", user.getScore()+"" );

                    if (user.getScore() == ROLE_ADMIN){

                        startIntentActivity(ROLE_ADMIN, user);
                    } else {
                        startIntentActivity(ROLE_ADMINS, user);
                    }
                }
            }
        }
    };


    public void startIntentActivity(int type, User user){
        Log.e("start","sldfjs");
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (type == ROLE_ADMIN){
            intent.setClass(this, GameActivity.class);
        } else {
            intent.setClass(this, GameActivity.class);
        }
        intent.putExtra("user", user);
        writeLoginStatus(user);
        startActivity(intent);
        finish();
    }

    private void writeLoginStatus(User user){
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("remember", true);
        editor.putInt("score", user.getScore());
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("username", user.getName());
        editor.commit();

    }
}