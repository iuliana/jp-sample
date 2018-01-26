package com.jp.base;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class Sale {
    private ProductType productType;
    private int value;

    public Sale(ProductType productType, int value) {
        this.productType = productType;
        this.value = value;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Apply adjustement to sale
     * If any of the operations makes the value <=0, the adjustement is not applied and an error message is printed
     *
     * @param adjustement
     * @param at
     */
    public void adjust(int adjustement, AdjustementType at) {
        int initialvalue = this.value;
        switch (at) {
            case ADD:
                this.value += adjustement;
                break;
            case SUBSTRACT:
                this.value -= adjustement;
                break;
            case MULTIPLY:
                this.value *= adjustement;
                break;
            case DIVIDE:
                this.value /= adjustement;
                break;
            default:
                // do nothing;
        }
        if (value <= 0) {
            this.value = initialvalue;
            System.err.println("Operation " + at.name() + "(" + adjustement + ") caused sale value to be out of range. " +
                    "Sale value will be reset to previous value: " + toString());
        }
    }

    @Override
    public String toString() {
        return "{" + productType + ", " + value + "}";
    }
}
