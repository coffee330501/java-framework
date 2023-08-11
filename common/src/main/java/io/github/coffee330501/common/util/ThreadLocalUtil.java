package io.github.coffee330501.common.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static Object get(String key) {
        Map<String, Object> map = CONTEXT.get();
        if (map == null) return null;
        return map.get(key);
    }

    public static synchronized void set(String key, Object value) {
        Map<String, Object> map = CONTEXT.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        CONTEXT.set(map);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
