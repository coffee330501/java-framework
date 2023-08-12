package io.github.coffee330501.common.util;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    public static StringRedisTemplate template;

    @Autowired
    public RedisUtil(StringRedisTemplate template) {
        RedisUtil.template = template;
    }

    /**
     * set
     *
     * @param key   key
     * @param value value
     */
    public static void set(String key, Object value) {
        template.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    /**
     * redis set with expire
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     * @param unit    unit
     */
    public static void set(String key, Object value, long timeout, TimeUnit unit) {
        template.opsForValue().set(key, JSONObject.toJSONString(value), timeout, unit);
    }

    /**
     * 若key不存在则set
     *
     * @param key   key
     * @param value value
     * @return 是否set成功
     */
    public static boolean setIfAbsent(String key, Object value) {
        return Boolean.TRUE.equals(template.opsForValue().setIfAbsent(key, JSONObject.toJSONString(value)));
    }

    /**
     * 若key不存在则set with expire
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     * @param unit    unit
     * @return
     */
    public static boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        return Boolean.TRUE.equals(template.opsForValue().setIfAbsent(key, JSONObject.toJSONString(value), timeout, unit));
    }

    /**
     * 根据key获取value
     *
     * @param key key
     * @return value
     */
    public static String get(String key) {
        return template.opsForValue().get(key);
    }
}
