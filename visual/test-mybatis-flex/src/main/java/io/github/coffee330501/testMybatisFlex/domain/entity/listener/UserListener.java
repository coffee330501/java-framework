package io.github.coffee330501.testMybatisFlex.domain.entity.listener;

import com.mybatisflex.annotation.SetListener;

import java.util.Objects;


public class UserListener implements SetListener {
    @Override
    public Object onSet(Object entity, String property, Object value) {
        if (Objects.equals(property, "password")) {
            return null;
        }
        return value;
    }
}
