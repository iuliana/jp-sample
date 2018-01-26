package com.jp;

import com.jp.base.AdjustementType;
import com.jp.base.ProductType;
import com.jp.base.Sale;
import com.jp.util.MessageGenerator;
import com.jp.util.SalesDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class SalesDaoTest {
    SalesDao salesDao;
    MessageGenerator mg = new MessageGenerator();

    @BeforeEach
    public void setUp() {
        salesDao = new SalesDao();
    }

    @Test
    public void testInit(){
        assertTrue(salesDao.getSaleCount() == 0);
    }

    @Test
    public void testAddOne(){
       Sale sale = new Sale(ProductType.ALMOND, 10);
       salesDao.addSale(sale);
       assertEquals(1, salesDao.getSaleCount());
    }

    @Test
    public void testAddMore(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        sale = new Sale(ProductType.APPLE, 20);
        salesDao.addSale(sale);
        sale = new Sale(ProductType.ALMOND, 30);
        salesDao.addSale(sale);
        assertEquals(3, salesDao.getSaleCount());
        System.out.println(salesDao.toString());
    }

    @Test
    public void testApplyAddAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        assertEquals(20, salesDao.getTotal());
        System.out.println(salesDao.getAdjustementsAsString());
    }

    @Test
    public void testApplyTwoAdjustements(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        salesDao.applyAdjustement(AdjustementType.SUBSTRACT, 2, sale.getProductType());
        assertEquals(18, salesDao.getTotal());
        System.out.println(salesDao.getAdjustementsAsString());
    }

    @Test
    public void testApplyAdjustementsToMore(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.ADD, 10, sale.getProductType());
        sale = new Sale(ProductType.APPLE, 20);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.SUBSTRACT, 5, sale.getProductType());
        salesDao.applyAdjustement(AdjustementType.SUBSTRACT, 5, sale.getProductType());
        assertEquals(30, salesDao.getTotal());
        assertEquals(2, salesDao.getAdjustements().get(ProductType.APPLE).size());
        assertEquals(1, salesDao.getAdjustements().get(ProductType.ALMOND).size());
        assertEquals("+10", salesDao.getAdjustements().get(ProductType.ALMOND).get(0));
        System.out.println(salesDao.getAdjustementsAsString());
    }

    @Test
    public void testApplySubstractToZeroAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.SUBSTRACT, 10, sale.getProductType());
        assertEquals(10, salesDao.getTotal());
    }

    @Test
    public void testApplyDivideToZeroAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.DIVIDE, 11, sale.getProductType());
        assertEquals(10, salesDao.getTotal());
    }

    @Test
    public void testApplyMultiplyToMaxAdjustement(){
        Sale sale = new Sale(ProductType.ALMOND, 10);
        salesDao.addSale(sale);
        salesDao.applyAdjustement(AdjustementType.MULTIPLY, Integer.MAX_VALUE, sale.getProductType());
        assertEquals(10, salesDao.getTotal());
    }


}
