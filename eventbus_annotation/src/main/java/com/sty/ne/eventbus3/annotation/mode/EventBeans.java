package com.sty.ne.eventbus3.annotation.mode;

/**
 * 所有事件集合
 * @Author: tian
 * @UpdateDate: 2020/9/14 8:06 PM
 */
public class EventBeans implements SubscriberInfo {
    //订阅对象Class，如：MainActivity.class
    private final Class subscriberClass;
    //订阅方法数组，参考SimpleSubscriberInfo.java 行
    private final SubscriberMethod[] methodInfos;

    public EventBeans(Class subscriberClass, SubscriberMethod[] methodInfos) {
        this.subscriberClass = subscriberClass;
        this.methodInfos = methodInfos;
    }

    @Override
    public Class<?> getSubscriberClass() {
        return subscriberClass;
    }

    @Override
    public SubscriberMethod[] getSubscriberMethods() {
        return methodInfos;
    }
}
