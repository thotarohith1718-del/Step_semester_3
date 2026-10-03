package week8practice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private int roomNumber;
    private double basePrice;

    Room(int roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    StandardRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    public double calculatePrice(long days) {
        return getBasePrice() * days;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    public double calculatePrice(long days) {
        return getBasePrice() * days * 1.2;
    }
}

class Suite extends Room {
    Suite(int roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    public double calculatePrice(long days) {
        return getBasePrice() * days * 1.5;
    }
}

class customer {
    private String name;

    customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean cancelled;

    Reservation(Customer customer, Room room, LocalDate startDate,
                LocalDate endDate, LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        this.cancelled = false;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return !cancelled &&
                startDate.isBefore(end) &&
                endDate.isAfter(start);
    }

    public double calculatePrice() {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return room.calculatePrice(days);
    }

    public void cancel(LocalDate date) {
        if (date.isBefore(cancellationDeadline) || date.isEqual(cancellationDeadline)) {
            cancelled = true;
            System.out.println("Reservation for " + customer.getName() +
                    ", " + room.getClass().getSimpleName() +
                    " Room " + room.getRoomNumber() +
                    " (" + startDate + " to " + endDate +
                    ") cancelled successfully.");
        } else {
            System.out.println("Cancellation deadline has passed.");
        }
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room &&
                    reservation.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public Reservation makeReservation(Customer customer, Room room,
                                       LocalDate start, LocalDate end,
                                       LocalDate cancellationDeadline) {

        if (!isAvailable(room, start, end)) {
            System.out.println(room.getClass().getSimpleName() +
                    " Room " + room.getRoomNumber() +
                    " is not available from " + start + " to " + end + ".");
            return null;
        }

        Reservation reservation = new Reservation(
                customer, room, start, end, cancellationDeadline
        );

        reservations.add(reservation);

        System.out.println("Reservation confirmed for " +
                customer.getName() + ", " +
                room.getClass().getSimpleName() +
                " Room " + room.getRoomNumber() +
                " (" + start + " to " + end + ").");

        System.out.println("Price: $" +
                reservation.calculatePrice());

        return reservation;
    }
}

public class q4 {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Room standard101 = new StandardRoom(101, 100);
        Room deluxe201 = new DeluxeRoom(201, 150);

        hotel.addRoom(standard101);
        hotel.addRoom(deluxe201);

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);

        if (hotel.isAvailable(standard101, start1, end1)) {
            System.out.println("Standard Room 101 is available from " +
                    start1 + " to " + end1 + ".");
        }

        Reservation reservationA = hotel.makeReservation(
                customerA,
                standard101,
                start1,
                end1,
                LocalDate.of(2025, 12, 30)
        );

        hotel.makeReservation(
                customerB,
                standard101,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7),
                LocalDate.of(2026, 1, 1)
        );

        if (reservationA != null) {
            reservationA.cancel(LocalDate.of(2025, 12, 29));
        }

        hotel.makeReservation(
                customerC,
                deluxe201,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                LocalDate.of(2026, 2, 5)
        );
    }
}
