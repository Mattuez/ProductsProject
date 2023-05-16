package application;

import model.entities.ImportedProduct;
import model.entities.Product;
import model.entities.UsedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numProducts = getNumProducts(scanner);
        ArrayList<Product> products = new ArrayList<>();

        for (int i = 0; i < numProducts; i++) {
            char productType = getProductType(scanner);
            System.out.print("Name: ");
            String name = scanner.nextLine();
            double price = getPrice(scanner);

            if (productType == 'c') {
                Product product = new Product(name, price);
                products.add(product);
            } else if (productType == 'i') {
                double customFee = getCustomFee(scanner);
                Product product = new ImportedProduct(name, price, customFee);
                products.add(product);
            } else {
                LocalDate date = getDate(scanner);
                Product product = new UsedProduct(name, price, date);
                products.add(product);
            }
        }

        for (Product product : products) {
            System.out.println(product.priceTag());
        }

        scanner.close();
    }

    private static int getNumProducts(Scanner scanner) {
        int numProduct;

        do {
            System.out.print("Enter the number of products: ");
            try {
                numProduct = scanner.nextInt();
                if (isPositiveInt(numProduct)) {
                    break;
                }
                System.out.println("Number of products can't be less than 1");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a positive integer.");
                scanner.nextLine();
            }
        } while (true);

        //cleaning scanner before returning
        scanner.nextLine();
        return numProduct;
    }

    private static boolean isPositiveInt(int numProduct) {
        return numProduct > 0;
    }

    private static char getProductType(Scanner scanner) {
        char productType;

        do {
            System.out.print("Common, used or imported (c/u/i)? ");
            productType = scanner.nextLine().charAt(0);
            if (isValidProduct(productType)) {
                break;
            }
            System.out.println("Enter a valid type");
        } while (true);

        return productType;
    }

    public static boolean isValidProduct(char productType) {
        String options = "CcUuIi";

        return options.contains(String.valueOf(productType));
    }

    private static double getPrice(Scanner scanner) {
        double price;

        do {
            System.out.print("Price: ");
            try {
                price = scanner.nextDouble();
                if (isNonNegativeDouble(price)) {
                    break;
                }
                System.out.println("Enter a non negative double");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Enter a non negative double");
            }
        } while (true);

        //cleaning scanner before returning
        scanner.nextLine();
        return price;
    }

    public static boolean isNonNegativeDouble(double price) {
        return price >= 0;
    }

    private static double getCustomFee(Scanner scanner) {
        double customFee;

        do {
            System.out.print("Custom fee: ");
            try {
                customFee = scanner.nextDouble();
                if (isNonNegativeDouble(customFee)) {
                    break;
                }
                System.out.println("Enter a non negative double");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Enter a non negative double");
            }
        } while (true);

        //cleaning scanner before returning
        scanner.nextLine();
        return customFee;
    }

    private static LocalDate getDate(Scanner scanner) {
        LocalDate date;

        do {
            System.out.print("Date: ");
            try {
                date = LocalDate.parse(scanner.nextLine(), UsedProduct.DATE_FORMAT);
                break;
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println("Enter a date in the format DD/MM/YYYY");
            }
        } while (true);

        return date;
    }
}
