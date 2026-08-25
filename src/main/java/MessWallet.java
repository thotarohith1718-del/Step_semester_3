package m2;

public class MessWallet {
    private double balance;

    public MessWallet(double balance) {
        if (balance < 0) {
            this.balance = 0;
            System.out.println("Invalid opening balance. Starting at 0.");
        } else {
            this.balance = balance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid top-up amount");
        } else {
            balance += amount;
        }
    }

    public void deduct(double amount) {
        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}