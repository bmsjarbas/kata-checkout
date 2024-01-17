package supermarket;

public interface ProductRepository {
    Product findBySKU(Character productSKU);
}
