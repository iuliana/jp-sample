package com.jp.util;


import com.jp.base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class MessageGenerator {
    private static final int MAX = 20;

    Random rand = new Random();

    public Message getMessage() {
        int mtVal = rand.nextInt(MessageType.values().length);
        MessageType mt = MessageType.values()[mtVal];

        int ptVal = rand.nextInt(ProductType.values().length);
        ProductType pt = ProductType.values()[ptVal];

        int prodVal = rand.nextInt(MAX) + 1;

        List<Sale> sales = new ArrayList<>();
        switch (mt) {
            case SINGLE:
                sales.add(new Sale(pt, prodVal));
                return new Message(mt, sales);
            case MULTIPLE:
                int saleCnt = rand.nextInt(MAX) + 1;
                for (int i = 0; i < saleCnt; i++) {
                    sales.add(new Sale(pt, prodVal));
                }
                return new Message(mt, sales);
            case ADJUSTEMENT:
                int adVal = rand.nextInt(AdjustementType.values().length);
                AdjustementType at = AdjustementType.values()[adVal];
                int adjustement = rand.nextInt(MAX);
                sales.add(new Sale(pt, prodVal));
                return new Message(mt, sales, at, adjustement);
        }
        return null;
    }

}
