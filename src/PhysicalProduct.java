
package javaproject;


public abstract class PhysicalProduct extends Product implements Taxable,Shippable {
    
    protected double shippingCost;
    protected String brand;
    
 

    public PhysicalProduct( double shippingCost, String brand,
            int id, String name, double price, Discount discount,
            String description, int quantity ,int rating ,int reviews) {
        super(id, name, price, discount, description, quantity ,rating,reviews);
        this.shippingCost = shippingCost;
        this.brand = brand;
    }

     @Override
    public double getFinalPrice() {

        return (applyDiscount()+ calculateTax()+ getShippableCost());
    }
    @Override
    public double getShippableCost() {
    return shippingCost;
}
    
    }
        
