import java.util.*;

import api.AdminResource;
import model.*;

public class AdminMenu {

    public static void printAdminMenu(){
        System.out.println("""
                -------------------------------------------------------------------------
                1. See all Customers
                2. See all Rooms
                3. See all Reservations
                4. Add a Room
                5. Back to Main Menu
                -------------------------------------------------------------------------
                Please select a number for the menu option""");

    }

    public static void selectFromAdminMenu(){
        printAdminMenu();
        Scanner input = new Scanner(System.in);
        String option = input.next();
        switch (option) {
            case "1" -> {
                displayAllCustomers();
                selectFromAdminMenu();
                break;
            }
            case "2" -> {
                displayAllRooms();
                selectFromAdminMenu();
                break;
        }
            case "3" -> {
                displayAllReservations();
                selectFromAdminMenu();
                break;
            }
            case "4" -> {
                addARoom();
                selectFromAdminMenu();
                break;
            }
            case "5" -> {
                //MainMenu.printMainMenu();
                MainMenu.selectOption();
                break;
                }
            default -> {
                System.out.println("please select a number from the menu option");
                selectFromAdminMenu();
                break;
                }
            }
    }


    private static void displayAllCustomers(){
        api.AdminResource adminResource = new AdminResource();
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        for (Customer customer : allCustomers) {
            System.out.println(customer);
        }
    }

    private static void displayAllRooms(){
        api.AdminResource adminResource= new AdminResource();
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        for (IRoom room : allRooms){
            System.out.println(room);
        }
    }

    private static void displayAllReservations(){
        api.AdminResource adminResource = new AdminResource();
        adminResource.displayAllReservations();
    }

    private static void addARoom(){
        api.AdminResource adminResource = new AdminResource();
        List<IRoom> rooms = new LinkedList<>();
        Validator validator = new Validator();
        String roomNumber;
        Double roomPrice;
        RoomType roomType;
        String keepAdding = "y";
        while (keepAdding.equals("y")) {
            roomNumber = validator.validateRoomNumber("Enter room Number ");
            roomPrice = validator.validateRoomPrice();
            roomType = validator.validateRoomType();
            rooms.add(new Room(roomNumber, roomPrice, roomType));
            keepAdding = validator.validateYesOrNo("Would you like to add another room y/n ");
        }
        adminResource.addRoom(rooms);
    }
}
