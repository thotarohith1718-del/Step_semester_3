import java.util.*;

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentMethodName();
}

class CreditCardPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        return true;
    }

    public String getPaymentMethodName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        return false;
    }

    public String getPaymentMethodName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        return true;
    }

    public String getPaymentMethodName() {
        return "Bank Transfer";
    }
}

class Order {
    private Customer customer;
    private ArrayList<OrderItem> items;
    private String status;

    public Order(Customer customer) {
        this.customer = customer;
        items = new ArrayList<>();
        status = "Pending";
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via "
                + paymentMethod.getPaymentMethodName()
                + " for Order " + customer.getName() + ".");

        boolean success = paymentMethod.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment for Order "
                    + customer.getName() + " successful.");
        } else {
            System.out.println("Payment for Order "
                    + customer.getName() + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {

        Customer customerX = new Customer("X");
        Product productA = new Product("A", 100);
        Product productB = new Product("B", 200);

        Order orderX = new Order(customerX);
        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println("Order created for Customer X.");
        orderX.pay(new CreditCardPayment());

        System.out.println();

        Customer customerY = new Customer("Y");

        Order orderY = new Order(customerY);

        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment());

        System.out.println();

        Customer customerZ = new Customer("Z");
        Product productC = new Product("C", 300);

        Order orderZ = new Order(customerZ);
        orderZ.addProduct(productC, 1);

        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPalPayment());
    }
}