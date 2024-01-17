package supermarket;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final ProductRepository productRepository;
    private List<CheckoutItem> items;

    public Checkout(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.items = new ArrayList<>();
    }

    public void scan(Character sku) {
        var product = productRepository.findBySKU(sku);
        var checkoutItem = CheckoutItem.fromDomain(product);
        var indexOfCurrentItem = items.indexOf(checkoutItem);
        if(indexOfCurrentItem < 0) {
            items.add(checkoutItem);
        } else {
            items.get(indexOfCurrentItem).increaseQuantity();
        }
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(checkoutItem -> checkoutItem.getItemTotal()).sum();
    }

    public List<CheckoutItem> getCurrentItems() {
        return this.items;
    }
}
