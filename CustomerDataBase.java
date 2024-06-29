// Purpose: This class is a singleton class that stores all the customers in a HashMap. 
//the key of the HashMap is the customer's id and the value is the customer object.
//It has methods to add, get and remove customers from the database.


import java.util.HashMap;
import java.util.Map;

public class CustomerDataBase {
    private static CustomerDataBase instance;
    private Map<Long, Customer> customers;

    private CustomerDataBase() {
        customers = new HashMap<>();
    }

    public static CustomerDataBase getInstance() {
        if (instance == null) {
            instance = new CustomerDataBase();
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.id,customer);
    }

    public Customer getCustomer(Long id) {
        return customers.get(id);
    }

    public void removeCustomer(Long id) {
        customers.remove(id);
    }
}