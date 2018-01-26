package com.jp;

import com.jp.base.AdjustementType;
import com.jp.base.ProductType;
import com.jp.base.Sale;
import com.jp.util.MessageGenerator;
import com.jp.util.SalesDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class SalesDBTest {
    SalesDB db;
    MessageGenerator mg = new MessageGenerator();

    @BeforeEach
    public void setUp() {
        db = new SalesDB();
    }

    @Test
    public void testInit(){
        assertTrue(db.getSaleCount() == 0);
    }

    @Test
    public void testAddOne(){
       Sale sale = new Sale(ProductType.ALMOND, 10);
       db.addSale(sale);
       assertEquals(1, db.getSaleCount());
    }

    @Test
    public void testAddMore(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        sale = new Sale(ProductType.APPLE, 20);
        db.addSale(sale);
        sale = new Sale(ProductType.ALMOND, 30);
        db.addSale(sale);
        assertEquals(3, db.getSaleCount());
        System.out.println(db.toString());
    }

    @Test
    public void testApplyAddAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        assertEquals(20, db.getTotal());
        System.out.println(db.getAdjustementsAsString());
    }

    @Test
    public void testApplyTwoAdjustements(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        db.applyAdjustement(AdjustementType.SUBSTRACT, 2, sale.getProductType());
        assertEquals(18, db.getTotal());
        System.out.println(db.getAdjustementsAsString());
    }

    @Test
    public void testApplyAdjustementsToMore(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        sale = new Sale(ProductType.APPLE, 20);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.SUBSTRACT, 5, sale.getProductType());
        db.applyAdjustement(AdjustementType.SUBSTRACT, 5, sale.getProductType());
        assertEquals(30, db.getTotal());
        assertEquals(2, db.getAdjustements().get(ProductType.APPLE).size());
        assertEquals(1, db.getAdjustements().get(ProductType.ALMOND).size());
        assertEquals("+10", db.getAdjustements().get(ProductType.ALMOND).get(0));
        System.out.println(db.getAdjustementsAsString());
    }

    @Test
    public void testApplySubstractToZeroAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.SUBSTRACT, 10, sale.getProductType());
        assertEquals(10, db.getTotal());
    }

    @Test
    public void testApplyDivideToZeroAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.DIVIDE, 11, sale.getProductType());
        assertEquals(10, db.getTotal());
    }

    @Test
    public void testApplyMultiplyToMaxAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        db.addSale(sale);
        db.applyAdjustement(AdjustementType.MULTIPLY, Integer.MAX_VALUE, sale.getProductType());
        assertEquals(10, db.getTotal());
    }


}
