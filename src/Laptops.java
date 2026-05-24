
package javaproject;


public class Laptops extends Electronics  {
    
    protected String[] storages;
protected String[] rams;

    public Laptops(
            double shippingCost, String brand, int id, String name, double price
            , Discount discount, String description, int quantity,int rating ,int reviews,
            String[] rams,String[] storages )
    {
        super( shippingCost, brand, id, name,
                price, discount, description, quantity ,rating,reviews);
        this.rams=rams;
        this.storages=storages;
    }



    @Override
    public double calculateTax() {
    return applyDiscount()*0.05;
    }

   
}
