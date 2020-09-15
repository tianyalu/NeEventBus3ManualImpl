package com.sty.ne.eventbus3.manualimpl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sty.ne.eventbus3.annotation.Subscribe;
import com.sty.ne.eventbus3.annotation.mode.ThreadMode;
import com.sty.ne.eventbus3.library.EventBus;
import com.sty.ne.eventbus3.manualimpl.model.UserInfo;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    private Button btnPostEvent;
    private Button btnActiveSticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
    }

    private void initView() {
        btnPostEvent = findViewById(R.id.btn_post_event);
        btnActiveSticky = findViewById(R.id.btn_active_sticky);

        btnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new UserInfo("啦啦啦", 77));
                finish();
            }
        });

        btnActiveSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(SecondActivity.this);
                EventBus.getDefault().removeStickyEvent(UserInfo.class);
            }
        });
    }

    //订阅方法
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventSticky(UserInfo userInfo) {
        Log.d(TAG, "onEventSticky userInfo --> " + userInfo.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserInfo userInfo = EventBus.getDefault().getStickyEvent(UserInfo.class);
        if(userInfo != null) {
            UserInfo info = EventBus.getDefault().removeStickyEvent(UserInfo.class);
            if(info != null) {
                EventBus.getDefault().removeAllStickyEvents();
            }
        }
        EventBus.getDefault().unregister(this);
    }
}