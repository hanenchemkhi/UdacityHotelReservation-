package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
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
