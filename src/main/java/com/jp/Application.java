package com.jp;

import com.jp.base.Message;
import com.jp.base.MessageType;
import com.jp.ex.MessageException;
import com.jp.util.MessageGenerator;
import com.jp.util.MessageProcessor;
import com.jp.util.SalesDao;


/**
 * Created by iuliana.grajdeanu on 1/26/18.
 * Description: run this class check the functionality with random inputs
 */
public class Application {

    public static void main(String... args) {

        MessageGenerator mg = new MessageGenerator();
        MessageProcessor mp = new MessageProcessor(new SalesDao());

        int mtVal = 12;
        for (int i = 0; i < mtVal; i++) {
            try {
                Message message = mg.getMessage();
                System.out.println(" .... processing message of type: " + message.getMessageType() +
                        (message.getMessageType() == MessageType.ADJUSTEMENT ? " " + message.getAdjustementType().getOperator() +
                                message.getAdjustement() + " on " + message.getSales().get(0).getProductType() : "") + " ....");

                mp.processMessage(message);
            } catch (MessageException e) {
                e.printStackTrace();
            }
        }
    }
}
