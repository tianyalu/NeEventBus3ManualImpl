package com.sty.ne.eventbus3.library;

import com.sty.ne.eventbus3.annotation.mode.SubscriberMethod;

import androidx.annotation.Nullable;

/**
 * 临时JavaBean对象，也可以直接写在Event作为变量
 * @Author: tian
 * @UpdateDate: 2020/9/14 9:50 PM
 */
public class Subscription {
    final Object subscriber; //订阅者MainActivity.class
    final SubscriberMethod subscriberMethod; //订阅的方法

    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        //必须重写方法，检测激活粘性事件重复调用（同一对象注册多个）
        if(other instanceof Subscription) {
            Subscription otherSubscription = (Subscription) other;
            //删除官方：subscriber == otherSubscription.subscriber判断条件
            //原因：粘性事件BUG，多次调用和移除时重现，参考Subscription.java 37行
            return subscriberMethod.equals(otherSubscription.subscriberMethod);
        }else {
            return false;
        }
    }
}
