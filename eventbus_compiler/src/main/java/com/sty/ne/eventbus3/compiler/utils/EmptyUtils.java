package com.sty.ne.eventbus3.compiler.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串、集合判空工具类
 * @Author: tian
 * @UpdateDate: 2020/9/14 8:00 PM
 */
public class EmptyUtils {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
