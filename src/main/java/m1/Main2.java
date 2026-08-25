package m1;

public class Main2 {
    public static void main(String[] args) {

        m2.MessWallet wallet = new m2.MessWallet(500);

        wallet.topUp(200);

        System.out.println("Balance after top-up: " + wallet.getBalance());

        wallet.deduct(1000);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}