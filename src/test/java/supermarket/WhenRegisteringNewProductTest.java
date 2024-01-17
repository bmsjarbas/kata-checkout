package supermarket;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhenRegisteringNewProductTest {

    @Test
    void TheProductIsAvailableToBeRetrivied() {
        var product = new Product('A', "potato", new PricingTable(50, 3, 130));
        var inMemoryRepository = new InMemoryRepository();
        inMemoryRepository.save(product);
        var productFromRepo = inMemoryRepository.findBySKU('A');
        assertEquals(product, productFromRepo);
    }
}
