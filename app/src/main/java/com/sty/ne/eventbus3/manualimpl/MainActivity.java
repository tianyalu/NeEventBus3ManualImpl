package com.sty.ne.eventbus3.manualimpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sty.ne.eventbus3.annotation.Subscribe;
import com.sty.ne.eventbus3.annotation.mode.ThreadMode;
import com.sty.ne.eventbus3.library.EventBus;
import com.sty.ne.eventbus3.manualimpl.apt.EventBusIndex;
import com.sty.ne.eventbus3.manualimpl.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnJump;
    private Button btnSticky;
    private TextView tvText;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what == 163) {
                UserInfo user = (UserInfo) msg.obj;
                tvText.setText(user.toString());
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addListeners();
    }

    private void initView() {
        btnJump = findViewById(R.id.btn_jump);
        btnSticky = findViewById(R.id.btn_sticky);
        tvText = findViewById(R.id.tv_text);

        EventBus.getDefault().addIndex(new EventBusIndex());
        EventBus.getDefault().register(this);
    }

    private void addListeners() {
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        btnSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new UserInfo("tian", 33));
            }
        });
    }

    //订阅方法
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(UserInfo userInfo) {
        Message msg = handler.obtainMessage();
        msg.obj = userInfo;
        msg.what = 163;
        handler.sendMessage(msg);
        Log.d(TAG, "onEvent userInfo --> " + userInfo.toString());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 1)
    public void onEvent2(UserInfo userInfo) {
        Log.d(TAG, "onEvent2 userInfo --> " + userInfo.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        EventBus.clearCaches();
    }
}