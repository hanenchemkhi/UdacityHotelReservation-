package model;

import java.util.Date;
import java.util.Objects;

public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;


    public Room(String roomNumber, Double price, RoomType roomType){
        this.roomNumber= roomNumber;
        this.price = price;
        this.enumeration = roomType;

    }


    @Override
    public final String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public final Double getRoomPrice() {
        return price;
    }

    @Override
    public final RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public final boolean isFree() {
        return price == 0.0;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Room room)) return false;

        return getRoomNumber().equals(room.getRoomNumber());
    }

    @Override
    public final int hashCode() {
        return getRoomNumber().hashCode();
    }

    @Override
    public String toString(){
        return "Room number: "+ roomNumber + " "+ enumeration + " Price: $"+price;
    }

}
