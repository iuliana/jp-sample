package com.jp.base;

import java.util.List;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class Message {
    private MessageType messageType;

    private List<Sale> sales;

    private AdjustementType adjustementType;
    private int adjustement = 0;

    public Message(MessageType messageType, List<Sale> sales) {
        this.messageType = messageType;
        this.sales = sales;
    }

    public Message(MessageType messageType, List<Sale> sales, AdjustementType adjustementType, int adjustement) {
        this.messageType = messageType;
        this.sales = sales;
        this.adjustementType = adjustementType;
        this.adjustement = adjustement;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public int getAdjustement() {
        return adjustement;
    }

    public AdjustementType getAdjustementType() {
        return adjustementType;
    }
}
