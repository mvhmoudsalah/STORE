
package javaproject;

public class Clothing extends PhysicalProduct{
    
    protected String[] sizes;
    protected String[] colors;

    public Clothing(String[] sizes, String[] colors,
            double shippingCost, String brand, int id, String name, double price,
            Discount discount, String description, int quantity ,int rating ,int reviews) {
        super( shippingCost, brand, id, name, price, discount, description, quantity,rating,reviews);
        this.sizes = sizes;
        this.colors = colors;
    }
 

    @Override
    public double calculateTax() {
    
        return applyDiscount()*0.05;
    
    }

   
}
