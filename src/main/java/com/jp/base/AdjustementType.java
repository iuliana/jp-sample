package com.jp.base;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public enum AdjustementType {
    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String operator;

    AdjustementType(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
