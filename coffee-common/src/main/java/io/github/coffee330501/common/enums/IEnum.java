package io.github.coffee330501.common.enums;

import java.io.Serializable;

public interface IEnum<T extends Serializable> {
    T getValue();
}
