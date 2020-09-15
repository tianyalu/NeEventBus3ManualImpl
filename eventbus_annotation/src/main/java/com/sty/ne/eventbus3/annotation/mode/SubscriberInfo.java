package com.sty.ne.eventbus3.annotation.mode;

/**
 * @Author: tian
 * @UpdateDate: 2020/9/14 8:04 PM
 */
public interface SubscriberInfo {
    //订阅所属类，比如：MainActivity
    Class<?> getSubscriberClass();

    //获取订阅所属类中所有订阅事件的方法（此处不使用List是因为注解处理器每次都要list.clear(),麻烦）
    SubscriberMethod[] getSubscriberMethods();
}
