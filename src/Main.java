import Entities.Customer;
import Entities.Order;
import Entities.Product;


import java.time.LocalDate;
import java.util.List;

public class Main {
  public static void main(String[] args) {



    Customer c1 = new Customer(1L, "Mario Rossi", 1);


    Customer c2 = new Customer(2L, "Luigi Verdi", 2);


    Customer c3 = new Customer(3L, "Anna Bianchi", 2);




    Product p1 = new Product(101L,"Il Signore degli anelli", "Books", 120.0);

    Product p2 = new Product( 02L, "Java Guida Completa", "Books", 5.5);

    Product p3 = new Product( 103L, "Passeggino Leggero", "Baby", 150.0);

    Product p4 = new Product( 104L, "Biberon Anticolica", "Baby", 15.0);

    Product p5 = new Product(105L, "T-Shirt Sportiva Boys", "Boys", 25.0);

    Product p6 = new Product(106L, "Scarpe da ginnastica", "Boys", 80.0);




    List<Product> globalProducts = List.of(p1, p2, p3, p4, p5, p6);



    Order o1 = new Order(1L, "CONSEGNATO", LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 20), List.of(p1, p3), c1);
    Order o2 = new Order(2L, "SPEDITO", LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 18), List.of(p2, p5), c2);
    Order o3 = new Order(3L, "CONSEGNATO", LocalDate.of(2021, 5, 10), LocalDate.of(2021, 5, 15), List.of(p6), c3);
    Order o4 = new Order(4L, "IN ELABORAZIONE", LocalDate.of(2021, 3, 22), LocalDate.of(2021, 3, 25), List.of(p4), c2);



    List<Order> globalOrders = List.of(o1, o2, o3, o4);

//ESERCIZIO 1
    List<Product> booksFilter = globalProducts.stream().filter(product -> product.getPrice() > 100 && product.getCategory().equals("Books")).toList();
    System.out.println("Prodotti della categoria Books");
    booksFilter.forEach(System.out::println);

//ESERCIZIO 2
    List<Order> babyOrder = globalOrders.stream()
        .filter(order -> order.getProducts().stream()
            .anyMatch(product -> product.getCategory().equals("Baby"))
    ).toList();

    System.out.println("\nOrdini contenenti un prodotto della categoria baby");
    babyOrder.forEach(System.out::println);

//ESERCIZIO 3
    List<Product> boysDiscount = globalProducts.stream().filter(product -> product.getCategory().equals("Boys"))
        .map(product -> {
          double prezzoDiscount = product.getPrice() * 0.9;
          product.setPrice(prezzoDiscount);
          return product;
        }).toList();

    System.out.println("\nProdotti della categoria boys con un prezzo scontato del 10%");
    boysDiscount.forEach(System.out::println);

//ESERCIZIO 4
    LocalDate dataInizio = LocalDate.of(2021, 2, 1);
    LocalDate dataFine = LocalDate.of(2021, 4, 1);

    List<Product> customerProductFilter = globalOrders.stream()
        .filter(order -> order.getCustomer().getTier().equals(2))
        .filter(order -> !order.getOrderDate().isBefore(dataInizio) && !order.getOrderDate().isAfter(dataFine))
        .flatMap(order -> order.getProducts().stream())
        .toList();

    System.out.println("\nProdotti ordinati dai clienti di tier 2 nella fascia di data specificata");
    customerProductFilter.forEach(System.out::println);




  }
}
