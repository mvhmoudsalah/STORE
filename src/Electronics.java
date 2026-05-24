
package javaproject;


public abstract class Electronics extends PhysicalProduct {

    public Electronics( 
            double shippingCost, String brand, int id, String name, double price,
            Discount discount, String description, int quantity ,int rating ,int reviews) {
        super( shippingCost, brand, id, name, price, discount, description, quantity,rating,reviews);
       
    }
}