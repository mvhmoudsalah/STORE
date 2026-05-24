
package javaproject;


public class FlatDiscount extends Discount {
    protected double amount;

    public FlatDiscount(double amount) {
        this.amount = amount;
    }
    
    @Override
    public double applyDiscount(double price) {
        return (price-amount);
    }
    
}
