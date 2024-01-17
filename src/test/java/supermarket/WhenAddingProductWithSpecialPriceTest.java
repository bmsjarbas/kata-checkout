package supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WhenAddingProductWithSpecialPriceTest {
    @Mock
    ProductRepository productRepository;
    private Checkout checkout;
    private Character productSKU;

    @BeforeEach
    void setUp() {
        checkout = new Checkout(productRepository);
        productSKU = 'A';
        when(productRepository.findBySKU(productSKU)).thenReturn(new Product(productSKU, "potato", new PricingTable(50,3, 130)));
    }

    @Test
    void andSpecialOfferIsNotFulfilled_theUnityPriceDoesNotChange() {
        checkout.scan(productSKU);
        assertEquals(50.0, checkout.getTotal());
    }

    @Test
    void andSpecialOfferIsFulfilled_theUnityPriceChange() {
        checkout.scan(productSKU);
        checkout.scan(productSKU);
        checkout.scan(productSKU);
        assertEquals(130.0, checkout.getTotal());
    }
}
