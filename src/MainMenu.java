import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public static void printMainMenu() {
        System.out.println("""
                -------------------------------------------------------------------------
                1. Find and reserve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                -------------------------------------------------------------------------
                Please select a number for the menu option""");
    }
    public static void selectOption() {
        printMainMenu();
        Scanner input = new Scanner(System.in);
        String option = input.next();
        switch (option) {
            case "1" -> {
                reserveARoom();
                break;
                }
            case "2" -> {
                seeReservations();
                break;
                }

            case "3" -> {
                createAnAccount();
                break;
            }

            case "4" -> {
                AdminMenu.selectFromAdminMenu();
                break;
            }

            case "5" -> {
                System.out.println("Exiting");
                System.exit(0);
                break;
            }

            default -> {
                System.out.println("please select a number from the menu option");
            }
        }
    }

    private static void createAnAccount(){
        api.HotelResource hotelResource = new HotelResource();
        Validator validator = new Validator();
        String email = validator.validateEmail("Enter Email format: name@domain.com");
        String firstName = validator.validateName("First Name ");
        String lastName = validator.validateName("Last Name ");

        hotelResource.createACustomer(email, firstName, lastName);
    }

    private static void reserveARoom(){
        api.HotelResource hotelResource = new HotelResource();
        api.AdminResource adminResource = new AdminResource();
        Validator validator = new Validator();
        Date checkIn = validator.validateDate(new Date(), "Enter CheckIn Date mm/dd/yyyy example ");
        Date checkOut= validator.validateDate(checkIn, "Enter CheckOut Date mm/dd/yyyy example ");
        Format dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // Display Available Rooms within a given dates
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn,checkOut);
        if (availableRooms.isEmpty()){
            checkIn = addDays(checkIn,7);
            checkOut = addDays(checkOut,7);
            availableRooms = hotelResource.findARoom(checkIn, checkOut);
            if (availableRooms.isEmpty()){
                System.out.println("Rooms are not not available at that time");
                return;
            }
        }
        System.out.println("Available Rooms between " + dateFormat.format(checkIn) + " and "+ dateFormat.format(checkOut));
        availableRooms.forEach(System.out::println);


        // Give the user the choice to book a room
        Validator bookValidator = new Validator();
        String book = bookValidator.validateYesOrNo("Would you like to book a room? y/n ");
        if(book.equals("y")){
            book = bookValidator.validateYesOrNo("Do you have an account with us? y/n ");
            if(book.equals("n")){
                createAnAccount();
            }
            String email = validator.validateEmail("Please enter a valid Email format: name@domain.com ");

            try{
                // Check if email exists in Customers data and
                // throws an exception if Customer is not in the list of customers
                adminResource.getCustomer(email);
                bookARoom(email,availableRooms, checkIn, checkOut);

            }catch(Exception exception){
                System.out.println("Account is not found, try to create an account");
                createAnAccount();
            }

        }else{
            selectOption();
        }
    }
    private static void bookARoom(String email, Collection<IRoom> availableRooms, Date checkIn, Date checkOut){
        api.HotelResource hotelResource = new HotelResource();
        Validator validator = new Validator();
        String roomNumber = validator.validateRoomNumber("what room number would you to reserve");

            try{
                IRoom room = hotelResource.getRoom(roomNumber);
                Reservation reservation = hotelResource.bookARoom(email,room,checkIn,checkOut);
                System.out.println("Reservation Successfully Completed \n"+reservation);
            }
            catch(Exception ex){
                System.out.println("Room number entered not from the list, Please enter a number from the list");
                // Display Available rooms
                availableRooms.forEach(System.out::println);
                //give the user a second chance to reenter room number
                roomNumber = validator.validateRoomNumber("what room number would you to reserve");
                try{
                    IRoom room = hotelResource.getRoom(roomNumber);
                    Reservation reservation = hotelResource.bookARoom(email,room,checkIn,checkOut);
                    System.out.println("Reservation Successfully Completed \n"+reservation);
                }catch(Exception e) {
                    System.out.println("Room number entered not from the list");
                }

            }

    }
    private static void seeReservations(){
        api.HotelResource hotelResource = new HotelResource();
        Validator validator = new Validator();
        String email = validator.validateEmail("Enter Email format: name@domain.com");
        System.out.println("Reservations");
        Collection<Reservation> customersReservations = hotelResource.getCustomersReservations(email);
        if (customersReservations  != null)
            customersReservations.forEach(System.out::println);

    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
