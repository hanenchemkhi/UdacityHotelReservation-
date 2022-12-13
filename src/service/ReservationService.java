package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;
import java.util.stream.Collectors;


public class ReservationService {

    // Used Bill Pugh Singleton Implementation
    //https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
    private ReservationService() {}
    private static class SingletonHelper {
        private static final ReservationService INSTANCE = new ReservationService();
    }
    public static ReservationService getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private final List<Reservation> reservations = new LinkedList<>();


    //private final Map<String,IRoom> rooms = new HashMap<>();
    private final Set<IRoom> rooms = new HashSet<>();
    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getRoom(String roomId){
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
        //return rooms.stream().filter(room->room.getRoomNumber().equals(roomId)).findAny().orElse(null);
    }


    public Collection<IRoom> getAllRooms(){
        return new LinkedList<>(rooms);

    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut){

        Collection<IRoom> reservedRooms= new LinkedList<>();
        Collection<IRoom> availableRooms = getAllRooms();

        for (Reservation reservation : reservations) {
            if ( ! reservation.getCheckOutDate().before(checkIn) || checkOut.before(reservation.getCheckInDate())) {
                reservedRooms.add(reservation.getRoom());
            }
        }
        availableRooms.removeAll(reservedRooms);

        return availableRooms;
    }
    public Collection<IRoom> searchRecommendedRooms(Date checkIn, Date checkOut, int date){
        checkIn = addDays(checkIn, date);
        checkOut = addDays(checkOut, date);
        return findRooms(checkIn, checkOut);

    }



     List<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservation = new ArrayList<>();
         for(Reservation reservation : reservations) {
             if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                 customerReservation.add(reservation);
             }
         }
         return customerReservation;

        /*return reservations.stream().
                filter(reservation ->reservation.getCustomer().getEmail().equals(customer.getEmail()) ).
                collect(Collectors.toList());*/
    }
    public void printAllReservation(){
        System.out.println("All Reservations :");
        reservations.forEach(System.out::println);
    }

    public static Date flexDate(Date date, int days){
        return addDays(date, days);
    }



    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
