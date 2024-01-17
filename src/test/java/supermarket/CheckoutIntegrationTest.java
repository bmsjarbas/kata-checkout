package supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutIntegrationTest {
    Checkout checkout;
    private InMemoryRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new InMemoryRepository();
        registerProducts();

        checkout = new Checkout(productRepository);

    }

    private void registerProducts() {
        productRepository.save(new Product('A', "potato", new PricingTable(50, 3, 130)));
        productRepository.save(new Product('B', "tomato", new PricingTable(30, 2, 45)));
        productRepository.save(new Product('C', "rice", new PricingTable(20, 1, 20)));
        productRepository.save(new Product('D', "dice", new PricingTable(15, 1, 15)));
    }

    @Test
    void addSingleItem() {
        checkout.scan('A');
        assertEquals(50, checkout.getTotal());
    }

    @Test
    void addSpecialPricePack() {
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        assertEquals(130, checkout.getTotal());
    }

    @Test
    void addSpecialPricePackAndRegularPrice() {
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        assertEquals(180, checkout.getTotal());
    }

    @ParameterizedTest
    @MethodSource("testInputFromKata")
    void allKataInputs(String goods, int expectedTotal) {
        for(int i = 0; i < goods.length(); i++){
            checkout.scan(goods.charAt(i));
        }
        assertEquals(expectedTotal, checkout.getTotal());
    }

    private static Stream<Arguments> testInputFromKata() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("A", 50),
                Arguments.of("AB", 80),
                Arguments.of("CDBA", 115),
                Arguments.of("AA", 100),
                Arguments.of("AAA", 130),
                Arguments.of("AAAA", 180),
                Arguments.of("AAAAA", 230),
                Arguments.of("AAAAAA", 260),
                Arguments.of("AAAB", 160),
                Arguments.of("AAABB", 175),
                Arguments.of("AAABBD", 190),
                Arguments.of("DABABA", 190)
        );
    }

}
