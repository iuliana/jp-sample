package com.jp.base;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public enum MessageType {
    /**
     * message contains one sale
     */
    SINGLE(1),
    /**
     * message contains multiple sales
     */
    MULTIPLE(2),
    /**
     * message contains one sale + one adjustement for all  exiting sales;
     */
    ADJUSTEMENT(3);

    private int val;

    MessageType(int val) {
        this.val = val;
    }

}
