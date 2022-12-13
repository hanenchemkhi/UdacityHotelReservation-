package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;


public class HotelResource {//use of the service class to implement its methods

    //static reference

    service.CustomerService customerService = CustomerService.getInstance();
    service.ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email) throws Exception{
        Customer customer = customerService.getCustomer(email);
        if (customer == null){
            throw new Exception("Customer account not found");
        }
        return customer ;
    }

    public void createACustomer(String email, String firstName, String lastName){
        try{
        customerService.addCustomer(email, firstName, lastName);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    public IRoom getRoom(String roomNumber) throws Exception{
        IRoom room= reservationService.getRoom(roomNumber);
        if (room == null){
            throw new Exception("Room not found");
        }
        return room;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        try {
            return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
//        try{
//            Customer customer = getCustomer(customerEmail);
//            return reservationService.getCustomersReservation(customer);
//        }catch(Exception e){
//            System.out.println(e.getLocalizedMessage());
//            return null;
//        }
        try{
            return customerService.getCustomerReservation(customerEmail);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }
    public Collection<IRoom> searchRecommendedRooms(Date checkIn, Date checkOut, int date){
        return reservationService.searchRecommendedRooms(checkIn, checkOut, date);
    }

    public Date flexibleDate(Date date, int days){
        return ReservationService.flexDate(date, days);
    }

}
