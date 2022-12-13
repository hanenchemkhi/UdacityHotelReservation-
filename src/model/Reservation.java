package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }

    public final Customer getCustomer() {
        return customer;
    }

    public final IRoom getRoom() {
        return room;
    }

    public final Date getCheckInDate() {
        return checkInDate;
    }

    public final Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Reservation that)) return false;

        if (!getCustomer().equals(that.getCustomer())) return false;
        if (!getRoom().equals(that.getRoom())) return false;
        if (!getCheckInDate().equals(that.getCheckInDate())) return false;
        return getCheckOutDate().equals(that.getCheckOutDate());
    }

    @Override
    public int hashCode() {
        int result = getCustomer().hashCode();
        result = 31 * result + getRoom().hashCode();
        result = 31 * result + getCheckInDate().hashCode();
        result = 31 * result + getCheckOutDate().hashCode();
        return result;
    }

    @Override
    public String toString(){
        Format dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return customer.getFirstName()+ " "+ customer.getLastName()+ "\n"+
                room.getRoomNumber()+ " - "+ room.getRoomType() + "\n"+
                "Price: $"+ room.getRoomPrice()+ " price per night \n"+
                "CheckIn Date: "+ dateFormat.format(checkInDate)+ "\n"+
                "CheckOut Date: "+ dateFormat.format(checkOutDate);
    }
}
