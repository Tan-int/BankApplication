import java.util.Random;

public class Account {
    private String accountNumber = "";
    private double balance;

    public Account(double balance) {
        Random random = new Random();
        for(int i = 0; i < 9; i++) {
            this.accountNumber += Integer.toString(random.nextInt(9));
        }
        this.balance = balance;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public double balance() {
        return balance;
    }
}
