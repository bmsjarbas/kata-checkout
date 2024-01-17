package supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WhenAddingItemToCheckoutTest {
    @Mock
    private ProductRepository productRepository;

    private Checkout checkout;
    Product product;
    private Character productSKU;

    @BeforeEach
    public void setUp() {
        productSKU = 'A';

        product = new Product(productSKU, "potato", new PricingTable(50, 3, 130));
        checkout = new Checkout(productRepository);
        when(productRepository.findBySKU(product.getSKU())).thenReturn(product);
    }


    @Test
    void theTotalIsUpdated() {
        checkout.scan(productSKU);
        assertEquals(50.0, checkout.getTotal());
    }

    @Test
    void listOfItemsIsReturned() {
        checkout.scan(productSKU);
        List<CheckoutItem> checkoutItems = checkout.getCurrentItems();
        assertEquals(1, checkoutItems.size());
        assertEquals(CheckoutItem.fromDomain(product), checkoutItems.get(0));
    }
}
