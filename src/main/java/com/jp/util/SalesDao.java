package com.jp.util;

import com.jp.base.AdjustementType;
import com.jp.base.ProductType;
import com.jp.base.Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 * Description: this is equivalent to a DAO class. The database is represented by the {code container} map.
 */
public class SalesDao {
    private int saleCount = 0;
    private Map<ProductType, List<Sale>> container = new HashMap<>();
    private Map<ProductType, List<String>> adjustements = new HashMap<>();

    /**
     * Adds a new sale to our mock database
     *
     * @param sale
     * @return {@code false} if a sale of this type already exists,  {@code true} otherwise
     */
    public boolean addSale(Sale sale) {
        boolean result = false;
        List<Sale> sales;
        if (container.containsKey(sale.getProductType())) {
            sales = container.get(sale.getProductType());
        } else {
            result = true;
            sales = new ArrayList<>();
        }

        sales.add(sale);
        container.put(sale.getProductType(), sales);
        saleCount++;
        return result;
    }

    /**
     * @return number of sales in the database
     */
    public int getSaleCount() {
        return saleCount;
    }

    /**
     * Method that applies a value adjustement to all sales in the dB
     *
     * @param at
     * @param atVal
     * @param productType
     */
    public void applyAdjustement(AdjustementType at, int atVal, ProductType productType) {
        List<Sale> sales = container.get(productType);
        for (Sale s : sales) {
            s.adjust(atVal, at);
        }
        List<String> adjs;
        if (adjustements.containsKey(productType)) {
            adjs = adjustements.get(productType);
        } else {
            adjs = new ArrayList<>();
        }
        adjs.add(at.getOperator() + atVal);
        adjustements.put(productType, adjs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Products and values:\n");
        int total[] = new int[1];
        container.forEach((k, v) -> {
            int[] value = new int[1];
            v.forEach(s -> value[0] += s.getValue());
            sb.append("\t" + k + ": " + v.size() + " " + v + "\n");
            total[0] += value[0];
        });
        sb.append("--------------------\n");
        sb.append("Total: " + total[0]);
        return sb.toString();
    }

    public String getAdjustementsAsString() {
        StringBuilder sb = new StringBuilder("Products and adjustements:\n");
        adjustements.forEach((k, v) -> {
            sb.append("\t" + k + ": " + v + "\n");
        });
        return sb.toString();
    }

    //needed only for testing the behaviour our our mock database to make store everything is stored correctly
    public Map<ProductType, List<String>> getAdjustements() {
        return adjustements;
    }

    /**
     * needed only for testing that the implementation of the mock database and DAO logic works as expected
     * @return the total values of sales in the {code container} map
     */
    public int getTotal() {
        int total[] = new int[1];
        container.values().forEach(v -> {
            int[] value = new int[1];
            v.forEach(s -> value[0] += s.getValue());
            total[0] += value[0];
        });
        return total[0];
    }


}
