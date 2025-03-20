import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    private ResourceBundle messages;
    private double totalCost = 0;

    public ShoppingCart(Locale locale) {
        this.messages = ResourceBundle.getBundle("messages", locale);
    }

    public static Locale getLocaleByChoice(int langChoice) {
        switch (langChoice) {
            case 1:
                return new Locale("fi", "FI");
            case 2:
                return new Locale("sv", "SE");
            case 3:
                return new Locale("ja", "JP");
            default:
                return new Locale("en", "US");
        }
    }

    public double calculateTotalCost(double[] prices, int[] quantities) {
        if (prices.length != quantities.length) {
            throw new IllegalArgumentException("Prices and quantities must have the same length");
        }

        totalCost = 0;
        for (int i = 0; i < prices.length; i++) {
            totalCost += prices[i] * quantities[i];
        }
        return totalCost;
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a language: ");
        System.out.println("1. Finnish");
        System.out.println("2. Swedish");
        System.out.println("3. Japanese");

        int langChoice = scanner.nextInt();
        Locale locale = getLocaleByChoice(langChoice);
        ShoppingCart cart = new ShoppingCart(locale);

        System.out.println(cart.getMessage("enter_items"));
        int numItems = scanner.nextInt();
        scanner.nextLine();

        double[] prices = new double[numItems];
        int[] quantities = new int[numItems];

        for (int i = 0; i < numItems; i++) {
            System.out.printf(cart.getMessage("enter_price"), (i + 1));
            prices[i] = scanner.nextDouble();

            System.out.printf(cart.getMessage("enter_quantity"), (i + 1));
            quantities[i] = scanner.nextInt();
        }

        double totalCost = cart.calculateTotalCost(prices, quantities);
        System.out.printf(cart.getMessage("total_cost") + " %.2f\n", totalCost);

        scanner.close();
    }
}
