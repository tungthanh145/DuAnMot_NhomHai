package com.example.duanmot_nhomhai.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.duanmot_nhomhai.dao.UserDAO;
import com.example.duanmot_nhomhai.model.User;

import java.io.Serializable;

public class UserService extends IntentService {
    public static final String EVENT_COURSE_SERVICE = "UserIntentService";

    public static final String ACTION_LOGIN= "login";
    public static final String ACTION_REGISTER = "register";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_CHECK_USERNAME_EXIST = "checkUsernameExists";
    private UserDAO userDAO;
    public UserService() {
        super("UserService");
        userDAO = new UserDAO(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Intent i = new Intent(EVENT_COURSE_SERVICE);
            final String action = intent.getAction();
            boolean result = false;
            User user = null;
            switch (action) {

                case ACTION_LOGIN:
                    user = (User) intent.getSerializableExtra( "user" );
                    user = userDAO.login(user.getName(), user.getPass());
                    i.putExtra("result", user);
                    break;

                case ACTION_REGISTER:
                    user = (User) intent.getSerializableExtra("user");
                    boolean checkUsername = userDAO.checkUsernameExists(user.getName());
                    Log.e( "checkusername", " "+checkUsername );
                    if (checkUsername){
                        i.putExtra("result",(Serializable) null);
                    } else {
                        result = userDAO.register(user);
                        i.putExtra("result", result);
                    }
                    break;

                case ACTION_UPDATE:
                    user = intent.getParcelableExtra("user");
                    result = userDAO.update(user);
                    i.putExtra("result", result);
                    break;

                case ACTION_CHECK_USERNAME_EXIST:
                    String userName = intent.getParcelableExtra("user");
                    result = userDAO.checkUsernameExists(userName);
                    i.putExtra("result", result);
                    break;
                default:
                    break;
            }
            i.putExtra("action", action);
            i.putExtra("resultCode", Activity.RESULT_OK);
            LocalBroadcastManager.getInstance(this).sendBroadcast(i);
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}