package api;

import model.Customer;
import model.IRoom;

import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    CustomerService customerService =  CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email) throws Exception{
        Customer customer = customerService.getCustomer(email);
        if (customer == null){
            throw new Exception();
        }
        return customer ;
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){

        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
