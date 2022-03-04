import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customerList;

    public Branch(String name) {
        this.name = name;
        this.customerList = new ArrayList<Customer>();
    }

    //METHODS TO MODIFY CUSTOMER LIST
    public static Branch createBranch(String name) {
        return new Branch(name);
    }

    public boolean addCustomer(Customer customer) {
        if (findCustomerByAccountNumber(customer.getAccountNumber()) >= 0) {
            System.out.println("Account already exists");
            return false;
        }

        customerList.add(customer);
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        if(findCustomerByAccountNumber(customer.getAccountNumber()) >= 0) {
            customerList.remove(customer);
            return true;
        }

        System.out.println("Account not found");
        return false;
    }

    //METHODS TO MODIFY CUSTOMERS TRANSACTION LIST
    public void addTransaction(Customer customer, Double transaction) {
        customer.addTransaction(transaction);
    }

    //METHODS TO SEARCH CUSTOMER LIST
    private int findCustomerByAccountNumber(String accountNumber) {
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            if (customer.getAccountNumber().equals(accountNumber)) {
                return i;
            }
        }

        return -1;
    }

    private int findCustomerByName(String name) {
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            if (customer.getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    private int findCustomer(Customer customer) {
        return this.customerList.indexOf(customer);
    }

    public Customer getCustomer(int position) {
        return customerList.get(position);
    }

    //GETTERS
    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getCustomer(String name) {
        if(findCustomerByName(name) >= 0) {
            return customerList.get(findCustomerByName(name));
        }

        return null;
    }

    public String getCustomer(Customer customer) {
        if(findCustomer(customer) >= 0) {
           return customer.accountInfo();
        }

        return null;
    }

    //PRINTING METHODS
    public void printCustomerList() {
        if(customerList.isEmpty()) {
            System.out.println("Currently no customers in this branch");
        } else {
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println("Customer " + (i+1) +
                    ". " + customerList.get(i).accountInfo()
            );
        }
    }
    }

}
