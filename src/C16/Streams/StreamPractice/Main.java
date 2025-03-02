package C16.Streams.StreamPractice;

import jdk.jfr.Category;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Customer customer1 = new Customer(1L, "Alice", 1);
        Customer customer2 = new Customer(2L, "Bob", 2);

        Product product1 = new Product(1L, "Laptop", "Electronics", 1000.00, null);
        Product product2 = new Product(2L, "Phone", "Electronics", 500.00, null);

        // Order 1
        Set<Product> order1Products = Set.of(product1, product2);
        Order order1 = new Order(1L, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5), "Shipped", customer1, order1Products);

        // Order 2
        Set<Product> order2Products = Set.of(product2);
        Order order2 = new Order(2L, LocalDate.of(2024, 12, 2), LocalDate.of(2024, 12, 6), "Delivered", customer2, order2Products);

        // Assign orders to products
        product1 = new Product(1L, "Laptop", "Electronics", 1000.00, Set.of(order1));
        product2 = new Product(2L, "Phone", "Electronics", 500.00, Set.of(order1, order2));
        Product product3 = new Product(3L, "Clean code", "Books", 500.00, null);
        Product product4 = new Product(4L, "Mouse", "Books", 50.00, null);

        List<Product> products = List.of(product1, product2, product3, product4);
        List<Customer> customers = List.of(customer1, customer2);
        List<Order> orders = List.of(order1, order2);

        // 1. Get list of products having category "Books" and price > 100.
        List<Product> res1 = products.stream().filter(p -> p.getCategory().equals("Books") && p.getPrice() > 100)
                .toList();

        // 2. Get orders containing products in "Baby" category
        List<Order> res2 = products.stream()
                .filter(p -> p.getCategory().equals("Baby"))
                .flatMap(p -> p.getOrders().stream())
                .toList();

        // 3. Get products in "Toys" category and -10% price.
        List<Product> res3 = products.stream()
                .filter(p -> p.getCategory().equals("Toys"))
                .map(p -> new Product(
                        p.getId(), p.getName(), p.getCategory(), p.getPrice()*0.9, p.getOrders()
                )).toList();

        // 4. Get products which were ordered by customers tier 2 from 1/2/2021 to 1/4/2021
        List<Product> res4 = orders.stream()
                .filter(o -> o.getCustomer().getTier().equals(2)
                && o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0
                && o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .toList();

        // 5. Get product having lowest price in "Books" category
        Product res5 = products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);

        // 6. Get 3 latest orders
        List<Order> res6 = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();

        // 7. Get all orders in 15/3/2021, write log and return all of their products.
        List<Product> res7 = orders.stream()
                .filter(o -> o.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .peek(o -> System.out.println(o.toString()))
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .toList();

        // 8. Total money of all orders in 2/2021.
        Double res8 = orders.stream()
                .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2021, 1, 31))
                && o.getOrderDate().isBefore(LocalDate.of(2021, 3, 1)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();

        // 9. Get average payment of all orders in 14/3/2021
        Double res9 = orders.stream()
                .filter(o -> o.getOrderDate().equals(LocalDate.of(2021, 3, 14)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average().getAsDouble();

        // 10. Get statistics for all products of "Books" category
        DoubleSummaryStatistics res10 = products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

        // 11. Get number of products of each order ID.
        Map<Long, Integer> res11 = orders.stream()
                .collect(Collectors.toMap(Order::getId, o -> o.getProducts().size()));

        // 12. Get list of orders of each customer.
        Map<Customer, List<Order>> res12 = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        // 13. Get total money of each order
        Map<Order, Double> res13 = orders.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()
                ));

        // 14. Get list of product name in each category
        Map<String, List<String>> res14 = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        // 15. Get the most expensive product in each category
        Map<String, Optional<Product>> res15 = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))));
    }
}
