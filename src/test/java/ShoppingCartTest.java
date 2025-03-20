import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void testGetLocaleByChoice() {
        assertEquals(new Locale("fi", "FI"), ShoppingCart.getLocaleByChoice(1));
        assertEquals(new Locale("sv", "SE"), ShoppingCart.getLocaleByChoice(2));
        assertEquals(new Locale("ja", "JP"), ShoppingCart.getLocaleByChoice(3));
        assertEquals(new Locale("en", "US"), ShoppingCart.getLocaleByChoice(999)); // Default case
    }

    @Test
    void testCalculateTotalCost() {
        ShoppingCart cart = new ShoppingCart(Locale.ENGLISH);
        double[] prices = {10.0, 20.5, 5.0};
        int[] quantities = {2, 1, 4};

        double expectedTotal = (10.0 * 2) + (20.5 * 1) + (5.0 * 4);
        assertEquals(expectedTotal, cart.calculateTotalCost(prices, quantities));
    }

    @Test
    void testCalculateTotalCostWithMismatchedArrays() {
        ShoppingCart cart = new ShoppingCart(Locale.ENGLISH);
        double[] prices = {10.0, 20.5};
        int[] quantities = {2}; // Mismatched length

        assertThrows(IllegalArgumentException.class, () -> cart.calculateTotalCost(prices, quantities));
    }

    @Test
    void testGetMessageForValidKey() {
        ShoppingCart cart = new ShoppingCart(new Locale("en", "US"));
        assertNotNull(cart.getMessage("enter_items")); // Ensure the key exists
    }

    @Test
    void testTotalCostCalculationPersists() {
        ShoppingCart cart = new ShoppingCart(Locale.ENGLISH);
        double[] prices = {10.0, 15.0};
        int[] quantities = {2, 3};

        cart.calculateTotalCost(prices, quantities);
        assertEquals((10.0 * 2) + (15.0 * 3), cart.getTotalCost());
    }
}
