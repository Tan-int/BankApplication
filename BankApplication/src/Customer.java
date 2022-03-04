import java.util.ArrayList;

public class Customer {
    private String name;
    private Account account;
    private ArrayList<Double> transactionList;

    public Customer(String name, double initialBalance) {
        this.name = name;
        this.account = new Account(initialBalance);
        this.transactionList = new ArrayList<>();
    }

    public static Customer createCustomer(String name, double initialBalance) {
        return new Customer(name, initialBalance);
    }

    public void getTransactionsList() {
        if(transactionList.isEmpty()) {
            System.out.println("No transactions found for " + name);
        } else {
            System.out.println("\nName: " + name);
            for (int i = 0; i < transactionList.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": " + transactionList.get(i).toString());
            }
        }
    }

    public void addTransaction(Double transaction) {
        if(transaction == null) {
            System.out.println("Enter a valid amount");
            return;
        }
        transactionList.add(transaction);
    }

    public String accountInfo() {
        return "Name: " + name + ", Account number: "
                + account.accountNumber() + ", Balance: " + account.balance();
    }

    public String getAccountNumber() {
        return account.accountNumber();
    }

    public String getName() {
        return name;
    }

}
