import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Bank {
    private ArrayList<Branch> branchList;

    public Bank() {
        this.branchList = new ArrayList<Branch>();
    }

    //METHODS THAT PRINT INFORMATION ABOUT BRANCH LIST OR BANK

    public void printBranchList() {
        Scanner scanner = new Scanner(System.in);
        if(branchList.isEmpty()) {
            System.out.println("Branch list is currently empty");
            return;
        } else {
            boolean option = true;
            while(option) {
                System.out.println("Would you also like to print the transactions of each customer? (Y/N)");
                String printTransactions = scanner.nextLine();
                switch (printTransactions.toLowerCase(Locale.ROOT)) {
                    case "y":
                    case "yes":
                        for (int i = 0; i < branchList.size(); i++) {
                            System.out.println("\nBranch: " + branchList.get(i).getName());
                            branchList.get(i).printCustomerList();
                            for (int j = 0; j < branchList.get(i).getCustomerList().size(); j++) {
                                branchList.get(i).getCustomer(j).getTransactionsList();
                            }
                        }
                        option = false;
                        break;
                    case "n":
                    case "no":
                        for (int i = 0; i < branchList.size(); i++) {
                            System.out.println("\nBranch: " + branchList.get(i).getName());
                            branchList.get(i).printCustomerList();
                        }
                        option = false;
                        break;
                    default:
                        System.out.println("Please select a valid option");
                }
            }
        }
    }

    // METHODS TO MODIFY BRANCH LIST
    public void addCustomer(Branch branch, Customer customer) {
        branch.addCustomer(customer);
    }

    public boolean addBranch(Branch branch) {
        if(findBranch(branch.getName()) >= 0) {
            System.out.println("Branch already exists");
            return false;
        }

        branchList.add(branch);
        return true;
    }

    public boolean removeBranch(Branch branch) {
        int position = findBranch(branch);
        if(position < 0) {
            System.out.println(branch.getName() + " doesn't exist");
            return false;
        }

        branchList.remove(position);
        return true;
    }

    //METHODS TO SEARCH BRANCH LIST

    private int findBranch(Branch branch) {
        return branchList.indexOf(branch);
    }

    private int findBranch(String name) {
        for(int i = 0; i < branchList.size(); i++) {
            if(branchList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //GETTERS
    public Branch getBranch(String name) {
        int position = findBranch(name);
        if(position >= 0) {
            return branchList.get(position);
        }

        return null;
    }
}

