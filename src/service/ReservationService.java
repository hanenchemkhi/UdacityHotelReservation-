package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;



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


    private final Map<String,IRoom> rooms = new HashMap<>();

    public void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomId){
        return rooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return new LinkedList<>(rooms.values());

    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut){

        Collection<IRoom> reservedRooms= new LinkedList<>();
        Collection<IRoom> allRooms = getAllRooms();

        for (Reservation reservation : reservations) {
            if ( ! reservation.getCheckOutDate().before(checkIn) || checkOut.before(reservation.getCheckInDate())) {
                reservedRooms.add(reservation.getRoom());
            }
        }

        allRooms.removeAll(reservedRooms);
        return allRooms;
    }

    public List<Reservation> getCustomersReservation(Customer customer){

        List<Reservation> customerReservation= new LinkedList<>();
        for(Reservation reservation : reservations){
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())){
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }
    public void printAllReservation(){
        System.out.println("All Reservations :");
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

}
