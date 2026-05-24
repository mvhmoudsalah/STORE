package javaproject;

public class CartItem {
//7ooQa
    protected Product product;
    protected int quantity;
    protected double subTotal;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        calculateSubTotal();
    }

    public double getSubTotal() {
        calculateSubTotal();
        return subTotal;
    }

    public void increaseQuantity() {
        quantity++;
        calculateSubTotal();
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
        calculateSubTotal();
    }

    private void calculateSubTotal() {
        if (product != null) {
            subTotal = product.getFinalPrice() * quantity;
        }
    }

}