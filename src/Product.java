
package javaproject;

public abstract class Product {
    protected int id;
    protected String name;
    protected double price;
    protected Discount discount;
    protected String description;
    protected int quantity;
    protected int rating;
    protected int reviews;
   
    public Product(){}
    
    public Product(int id, String name, double price, Discount discount,
            String description, int quantity ,int rating ,int reviews) {
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.quantity = quantity;
        this.rating = rating;
        this.reviews = reviews;
       
    }
    public double applyDiscount() {

        if(discount == null)
            return price;

        return discount.applyDiscount(price);
    }

    public abstract double getFinalPrice();
}
    