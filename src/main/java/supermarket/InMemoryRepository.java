package supermarket;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements ProductRepository {
    private final Map<Character, Product> productMap = new HashMap<>();
    public void save(Product product) {
        productMap.put(product.getSKU(), product);
    }

    @Override
    public Product findBySKU(Character productSKU) {
       return productMap.get(productSKU);
    }
}
