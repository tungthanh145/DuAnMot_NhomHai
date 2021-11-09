package com.example.duanmot_nhomhai.activities;

import static com.example.duanmot_nhomhai.uitilities.Constants.ID_USER;
import static com.example.duanmot_nhomhai.uitilities.Constants.PASSWORD;
import static com.example.duanmot_nhomhai.uitilities.Constants.USER_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmot_nhomhai.R;
import com.example.duanmot_nhomhai.dao.UserDAO;
import com.example.duanmot_nhomhai.model.User;

import java.util.List;

//Màn hình login
public class LoginActivity extends AppCompatActivity {
    public EditText edtUsername, edtPassword;
    public Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                UserDAO userDAO = new UserDAO(LoginActivity.this);
                List<User> userList = userDAO.getAllUser();

                for (User user : userList){
                    if (user.getUsername().equals(userName) && user.getPassword().equals(password)){
                        ID_USER = user.getId();
                        PASSWORD = user.getPassword();
                        USER_NAME = user.getUsername();
                        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Successful !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(LoginActivity.this, "Login fails !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(LoginActivity.this, "Username or Password is incorrect !", Toast.LENGTH_SHORT).show();
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
}