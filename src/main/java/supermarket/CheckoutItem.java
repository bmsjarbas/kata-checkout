package supermarket;

public class CheckoutItem {

    private final Product product;
    private int quantity;

    public CheckoutItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckoutItem)) {
            return false;
        }
        CheckoutItem checkoutItem = (CheckoutItem) o;
        return this.getSKU() == checkoutItem.getSKU();
    }

    public static CheckoutItem fromDomain(Product product) {
        return new CheckoutItem(product, 1);
    }

    public Character getSKU() {
        return this.product.getSKU();
    }

    public double getItemTotal() {
        return this.product.calculateTotalOfProducts(this.quantity);
    }

    public void increaseQuantity() {
        quantity++;
    }
}
