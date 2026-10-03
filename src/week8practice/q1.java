package week8practice;

abstract class Vehicle {
    private String name;
    private boolean available;

    public Vehicle(String name) {
        this.name = name;
        available = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    public SUV(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    public Truck(String name) {
        super(name);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public void start() {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return;
        }

        vehicle.setAvailable(false);

        System.out.println(vehicle.getName()
                + " rented successfully by "
                + customer.getName() + ".");

        System.out.printf("Rental charge: $%.2f.%n",
                vehicle.calculateCharge(days));
    }

    public void returnVehicle() {
        vehicle.setAvailable(true);

        System.out.println(vehicle.getName()
                + " returned by "
                + customer.getName() + ".");
    }
}

public class q1 {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Rental rental1 = new Rental(customer1, sedan, 3);
        rental1.start();

        Rental rental2 = new Rental(customer2, sedan, 2);
        rental2.start();

        rental1.returnVehicle();

        Rental rental3 = new Rental(customer3, suv, 5);
        rental3.start();
    }
}