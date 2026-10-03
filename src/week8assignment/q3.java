package week8assignment;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 400;
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

class Show {
    private String showTime;
    private Seat[] bookedSeats;
    private int count;
    private boolean started;

    public Show(String showTime) {
        this.showTime = showTime;
        bookedSeats = new Seat[100];
        count = 0;
        started = false;
    }

    public boolean isAvailable(Seat seat) {
        for (int i = 0; i < count; i++) {
            if (bookedSeats[i].getSeatNumber()
                    .equals(seat.getSeatNumber())) {
                return false;
            }
        }
        return true;
    }

    public void bookSeat(Seat seat) {
        bookedSeats[count] = seat;
        count++;
    }

    public void releaseSeat(Seat seat) {
        for (int i = 0; i < count; i++) {
            if (bookedSeats[i].getSeatNumber()
                    .equals(seat.getSeatNumber())) {

                for (int j = i; j < count - 1; j++) {
                    bookedSeats[j] = bookedSeats[j + 1];
                }

                count--;
                return;
            }
        }
    }

    public boolean hasStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private Seat[] seats;
    private int count;
    private boolean cancelled;

    public Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
        seats = new Seat[6];
        count = 0;
        cancelled = false;
    }

    public boolean addSeat(Seat seat) {
        if (count >= 6) {
            System.out.println("Maximum 6 seats allowed per booking.");
            return false;
        }

        if (!show.isAvailable(seat)) {
            System.out.println("Seat " + seat.getSeatNumber()
                    + " is already booked for this show.");
            return false;
        }

        seats[count] = seat;
        count++;
        show.bookSeat(seat);
        return true;
    }

    public double getTotal() {
        double total = 0;

        for (int i = 0; i < count; i++) {
            total += seats[i].getPrice();
        }

        return total;
    }

    public void confirm() {
        System.out.print("Booking confirmed for " +
                customer.getName() + ": ");

        for (int i = 0; i < count; i++) {
            System.out.print(seats[i].getSeatNumber());

            if (i < count - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(". Total: ₹%.2f.%n", getTotal());
    }

    public void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        if (!cancelled) {
            for (int i = 0; i < count; i++) {
                show.releaseSeat(seats[i]);
            }

            cancelled = true;

            System.out.println(customer.getName()
                    + "'s booking cancelled.");

            System.out.print("Seats ");

            for (int i = 0; i < count; i++) {
                System.out.print(seats[i].getSeatNumber());

                if (i < count - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(" released.");
        }
    }
}

public class q3 {
    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show);

        b1.addSeat(new RegularSeat("A1"));
        b1.addSeat(new RegularSeat("A2"));
        b1.addSeat(new PremiumSeat("F5"));
        b1.confirm();

        Booking b2 = new Booking(ravi, show);
        b2.addSeat(new RegularSeat("A2"));
        b2.addSeat(new ReclinerSeat("R1"));
        b2.confirm();

        b1.cancel();

        Booking b3 = new Booking(neha, show);
        b3.addSeat(new RegularSeat("A2"));
        b3.confirm();
    }
}