import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isUsing = true;
        printOptionScreen();

        while (isUsing) {
            System.out.print("\n(10 to View Menu) Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    bank.printBranchList();
                    break;
                case "2":
                    addBranch();
                    break;
                case "3":
                    removeBranch();
                    break;
                case "4":
                    findBranch();
                    break;
                case "5":
                    addCustomer();
                    break;
                case "6":
                    removeCustomer();
                    break;
                case "7":
                    findCustomer();
                    break;
                case "8":
                    addTransaction();
                    break;
                case "9":
                    printTransactionList();
                    break;
                case "10":
                    printOptionScreen();
                    break;
                case "11":
                    isUsing = false;
                    System.out.println("Program ended");
                    break;
                default:
                    try{
                       int num = Integer.parseInt(choice);
                       System.out.println("Please select a valid option");
                    } catch (Exception e) {
                        System.out.println("Please select a valid option");
                    }
            }
        }
    }

    //PRINTING METHODS
    private static void printOptionScreen() {
        System.out.println("------ BANK APPLICATION -----");
        System.out.println(
                "1. Print branch List\n" +
                        "2. Add a branch\n" +
                        "3. Remove a branch\n" +
                        "4. Search for a branch\n" +
                        "5. Add a customer\n" +
                        "6. Remove a customer\n" +
                        "7. Search for a customer\n" +
                        "8. Add a transaction\n" +
                        "9. View a customer's transactions\n" +
                        "10. View Option Menu\n" +
                        "11. Exit Program");
    }

    //METHODS TO MODIFY THE BRANCH LIST WITHIN BANK
    private static void addBranch() {
        System.out.print("Enter new branch name: ");
        String name = scanner.nextLine().toLowerCase(Locale.ROOT);
        Branch branch = Branch.createBranch(name);
        if(bank.addBranch(branch)) {
            System.out.println("Branch " + branch.getName() + " has been added");
        } else {
            System.out.println("Error adding branch " + branch.getName());
        }
    }

    private static void removeBranch() {
        System.out.println("Enter the branch to delete: ");
        String name = scanner.nextLine().toLowerCase(Locale.ROOT);
        Branch branch = bank.getBranch(name);

        if (branch == null) {
            System.out.println("Branch " + name + " doesn't exist in the system");
            return;
        }

        if (bank.removeBranch(branch)) {
            System.out.println("Branch " + name + " successfully removed");
        } else {
            System.out.println("Error removing branch " + name);
        }
    }

    //METHODS TO MODIFY THE CUSTOMER LIST WITHIN A BRANCH
    private static void addCustomer() {
        System.out.print("Enter the branch to add new customer to: ");
        String name = scanner.nextLine().toLowerCase(Locale.ROOT);
        Branch branch = bank.getBranch(name);
        if (branch == null) {
            System.out.println("Branch " + name + " doesn't exist in the system");
            return;
        }
        System.out.print("Enter new customer name: ");
        String customerName = scanner.nextLine().toLowerCase(Locale.ROOT);

        System.out.print("Enter new customer initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Customer customer = Customer.createCustomer(customerName, balance);
        bank.addCustomer(branch, customer);
        System.out.println("Customer " + customer.accountInfo() + " successfully added");
    }

    private static void removeCustomer() {
        System.out.println("Enter the branch the customer is in: ");
        String name = scanner.nextLine().toLowerCase(Locale.ROOT);

        Branch branch = bank.getBranch(name);
        if (branch == null) {
            System.out.println("Branch " + name + " doesn't exist in the system");
            return;
        }

        System.out.print("Enter customer to remove: ");
        String customerName = scanner.nextLine().toLowerCase(Locale.ROOT);
        Customer customer = branch.getCustomer(customerName);

        if (customer == null) {
            System.out.println("Customer " + customerName + " doesn't exist or is not in this branch");
            return;
        }

        if (branch.removeCustomer(customer)) {
            System.out.println("Customer " + customerName + " successfully removed");
        } else {
            System.out.println("Error removing customer " + customerName);
        }

    }

    //METHODS FOR TRANSACTION LISTS FOR EACH CUSTOMER
    private static void addTransaction() {
        Customer customer = findCustomer();
        if(customer == null) {
            return;
        }
        System.out.print("Enter transaction amount: ");
        Double transactionAmount = scanner.nextDouble();
        scanner.nextLine();
        customer.addTransaction(transactionAmount);
        System.out.println("Transaction amount of: " + transactionAmount + " added");
    }

    private static void printTransactionList() {
        Branch b = findBranch();
        if(b == null) {
            return;
        }

        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        Customer c = b.getCustomer(name);

        if(c == null) {
            System.out.println("Cannot find customer");
        } else {
            c.getTransactionsList();
        }
    }

    //METHODS TO SEARCH
    private static Branch findBranch() {
        System.out.print("Enter branch name: ");
        String name = scanner.nextLine().toLowerCase(Locale.ROOT);
        Branch existingBranch = bank.getBranch(name);
        if (existingBranch == null) {
            System.out.println("Branch " + name + " doesn't exist in the system");
            return null;
        }
        System.out.println("Branch: " + existingBranch.getName());
        existingBranch.printCustomerList();
        return existingBranch;
    }

    private static Customer findCustomer() {
        Branch currentBranch = findBranch();
        if (currentBranch == null) {
            return null;
        } else if (currentBranch.getCustomerList().isEmpty()) {
            return null;
        }

        boolean searchCustomer = false;
        while (!searchCustomer) {
            System.out.print("\n(Type exit to return to menu) Enter customer name: ");
            String name = scanner.nextLine().toLowerCase(Locale.ROOT);
            Customer customer = currentBranch.getCustomer(name);
            if (name.equals("exit")) {
                searchCustomer = true;
                printOptionScreen();
            } else if (customer == null) {
                System.out.println("Customer not found");
            } else {
                System.out.println(customer.accountInfo());
                return customer;
            }
        }
        return null;
    }
}
