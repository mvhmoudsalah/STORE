
package javaproject;



public class Phones extends Electronics {

    protected String[] storages;
    protected String[] colors;
    
    public Phones(String[] storages,String[] colors,
            double shippingCost, String brand, int id, String name,
            double price, Discount discount, String description,
            int quantity,int rating ,int reviews ) {

        super( shippingCost, brand,
                id, name, price, discount, description, quantity,rating,reviews);
        
        this.storages = storages;
        this.colors = colors;

    }

   

    @Override
    public double calculateTax() {
    
        return applyDiscount()*0.04;
    
    }
}
