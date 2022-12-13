package service;

import model.Customer;
import model.Reservation;

import java.util.*;

/**
 * Used same methods suggested in "Create Service classes" lesson :
 * *public void addCustomer(String email, String firstName, String lastName)
 * *public Customer getCustomer(String customerEmail)
 * *public Collection<Customer> getAllCustomers()
 * *provide static reference
 */
public class CustomerService {

    /**
     * Used this blog to help me understand Singleton Patter :
     * https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
     *The pattern used is Bill Pugh Singleton
     */
    private CustomerService() {}
    private static class SingletonHelper {
        private static final CustomerService INSTANCE = new CustomerService();
    }
    public static CustomerService getInstance() {
        return CustomerService.SingletonHelper.INSTANCE;
    }

    service.ReservationService reservationService = ReservationService.getInstance();
    /**
     *Used Map structure to be able to retrieve customer info based on their email
     * as it is explained in lesson 7 : 2.Maps
     */
    Map<String, Customer> customers = new TreeMap<>();

    public void addCustomer(String email, String firstName, String lastName) {
            customers.put(email, new Customer(firstName, lastName, email));
    }

//   public Customer getCustomer(String customerEmail){
//        return customers.get(customerEmail);
//    }
public Customer getCustomer(String customerEmail) throws Exception{
    Customer customer = customers.get(customerEmail);
    if (customer == null){
        throw new Exception("Customer account not found");
    }
    return customer ;
}
    public Collection<Reservation> getCustomerReservation(String customerEmail){
        try{
            Customer customer = getCustomer(customerEmail);
            return reservationService.getCustomersReservation(customer);
        }catch(Exception e) {
            return null;
        }
    }
    public Collection<Customer> getAllCustomers(){
        return new ArrayList<>(customers.values());
    }
}
