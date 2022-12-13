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

   public Customer getCustomer(String email) {
       try {
        return customerService.getCustomer(email);
       } catch(Exception e){
           System.out.println(e.getLocalizedMessage());
           return null;
       }

    }

    public void addRoom(List<IRoom> rooms){
        /*for(IRoom room : rooms){
            reservationService.addRoom(room);
        }*/
        rooms.forEach(reservationService::addRoom);
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
