package supermarket;

import java.util.Objects;

public class Product {
    private final Character sku;
    private final String name;
    private final PricingTable pricingTable;

    public Product(Character sku, String name, PricingTable pricingTable) {
        this.sku = sku;
        this.name = name;
        this.pricingTable = pricingTable;
    }

    public Character getSKU() {
        return this.sku;
    }

    public String getName() {
        return this.name;
    }

    public double calculateTotalOfProducts(int quantity) {
        int specialOffers = quantity / this.pricingTable.getSpecialQuantity();
        int remainingItems = quantity % this.pricingTable.getSpecialQuantity();

        return (specialOffers * this.pricingTable.getSpecialPrice()) + (remainingItems * this.pricingTable.getRegularPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }
}
