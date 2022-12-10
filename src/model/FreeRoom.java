package model;

public class FreeRoom extends Room{

    final Double PRICE = 0.0 ;

    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber,0.0, roomType);
    }
    @Override
    public String toString(){
        return super.toString();
    }
}


