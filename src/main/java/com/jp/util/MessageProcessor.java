package com.jp.util;

import com.jp.base.Message;
import com.jp.base.Sale;
import com.jp.ex.MessageException;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class MessageProcessor {

    SalesDao db;

    public MessageProcessor(SalesDao db) {
        this.db = db;
    }

    public int processMessage(Message message) throws MessageException {
        if (message.getSales() == null || message.getSales().isEmpty()) {
            throw new MessageException("Message must contain at least one sale entry. Invalid message has been discarded.");
        }
         switch (message.getMessageType()) {
            case SINGLE:
                db.addSale(message.getSales().get(0));
                checkAndLog();
                break;
            case MULTIPLE:
                message.getSales().forEach(s -> {
                    db.addSale(s);
                    checkAndLog();
                });
                break;
            case ADJUSTEMENT:
                Sale s = message.getSales().get(0);
                db.addSale(s);
                db.applyAdjustement(message.getAdjustementType(), message.getAdjustement(), s.getProductType());
                checkAndLog();
                break;
        }
        return 0;
    }

    private int checkAndLog() {
        if (db.getSaleCount() % 50 == 0) {
            System.out.println("[50]... Pausing application, messages will not be processed, for 5 seconds.");
            System.out.println(db.getAdjustementsAsString() + "\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 50;
        }
        if (db.getSaleCount() % 10 == 0) {
            System.out.println("[10]" + db.toString() +"\n");
            return 10;
        }
        return 0;
    }
}
