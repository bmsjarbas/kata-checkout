package supermarket;

public class PricingTable {
    private final double regularPrice;
    private final int specialQuantity;
    private final double specialPrice;

    public PricingTable(double regularPrice, int specialQuantity, double packPrice) {
        this.regularPrice = regularPrice;
        this.specialQuantity = specialQuantity;
        this.specialPrice = packPrice;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public int getSpecialQuantity() {
        return specialQuantity;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }
}

