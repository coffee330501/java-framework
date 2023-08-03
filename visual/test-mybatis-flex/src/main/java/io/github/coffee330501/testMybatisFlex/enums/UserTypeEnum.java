package io.github.coffee330501.testMybatisFlex.enums;

import com.mybatisflex.annotation.EnumValue;

public enum UserTypeEnum {
    TerminalNode(1, "门店"),
    Pedlar(2, "运力"),
    Recovery(3, "回收公司"),
    ;
    @EnumValue
    private int code;
    private String desc;

    UserTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
