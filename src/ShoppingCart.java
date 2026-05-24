package javaproject;

public class ShoppingCart {

    protected CartItem[] items;
    protected int size;

    public ShoppingCart(int capacity) {
        items = new CartItem[capacity];
        size = 0;
    }

    // ADD PRODUCT
    public boolean addProduct(Product product, int quantity) {

        // check if already exists
        for (int i = 0; i < size; i++) {
            if (items[i].product.id == product.id) {
                for (int j = 0; j < quantity; j++) {
                    items[i].increaseQuantity();
                }
                return true;
            }
        }

        // add new item
        if (size < items.length) {
            items[size++] = new CartItem(product, quantity);
            return true;
        }

        return false; // full cart
    }

    // REMOVE PRODUCT
    public boolean removeProduct(int productId) {

        for (int i = 0; i < size; i++) {

            if (items[i].product.id == productId) {

                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }

                items[--size] = null;
                return true;
            }
        }

        return false;
    }

    // TOTAL
    public double calculateTotal() {

        double total = 0;

        for (int i = 0; i < size; i++) {
            total += items[i].getSubTotal();
        }

        return total;
    }

    // CLEAR
    public void clearCart() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

 
}