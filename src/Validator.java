import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {

    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern emailPattern = Pattern.compile(emailRegex);

    private final String nameRegex = "^[\\p{L} .'-]+$";
    private final Pattern namePattern = Pattern.compile(nameRegex);

    private final Pattern roomNumberPattern = Pattern.compile("\\d+");
    private final Pattern roomPricePattern = Pattern.compile("^(((\\d{1,3},)+\\d{3})|\\d+)(\\.\\d{2})?");
    private final Pattern roomTypePattern = Pattern.compile("[1-2]");
    private final Pattern responsePattern = Pattern.compile("[ynYN]");
    Scanner sc = new Scanner(System.in);

    public String validateEmail(String text) {
        String email;
        System.out.println(text);
        while (true) {
            email = sc.nextLine();
            if (!emailPattern.matcher(email).matches()) {
                System.out.println(text);
            } else {
                break;
            }
        }
        return email;
    }

    public String validateName(String text) {
        String name;
        System.out.println(text);
        while (true) {
            name = sc.nextLine();
            if (!namePattern.matcher(name).matches()) {
                System.out.println("Please enter a valid name ");
            } else {
                break;
            }
        }
        return name;
    }

    public String validateRoomNumber(String text){
        String roomNumber;
        System.out.println(text);
        while(true){
            roomNumber = sc.nextLine();
            if (!roomNumberPattern.matcher(roomNumber).matches()){
                System.out.println("Please enter a valid room Number ");
            }else{
                break;
            }
        }
        return roomNumber;
    }
    public double validateRoomPrice(){
        String roomPrice;
        System.out.println("Enter price per night ");
        while(true){
            roomPrice = sc.nextLine();
            if (!roomPricePattern.matcher(roomPrice).matches()){
                System.out.println("Please enter a valid price ");
            }else{
                break;
            }
        }
        return Double.parseDouble(roomPrice);
    }

    public RoomType validateRoomType(){
        String roomType;
        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        while(true){
            roomType = sc.nextLine();
            if (!roomTypePattern.matcher(roomType).matches()){
                System.out.println("Please enter 1 for single bed or 2 for double bed ");
            }else{
                break;
            }
        }
        if(roomType.equals("1")) return  RoomType.SINGLE;
            else return RoomType.DOUBLE;
    }

    public String validateYesOrNo(String text){
        String response;
        System.out.println(text);
        while (true){
            response = sc.nextLine();
            if (!responsePattern.matcher(response).matches()){
                System.out.println("Please enter Y (Yes) or N(No)");
            }else {
                return response.toLowerCase();
            }
        }
    }


    public Date validateDate(Date d, String text) {
        Date checkDate = null;
        //Scanner sc = new Scanner(System.in);
        while(true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println(text + dateFormat.format(MainMenu.addDays(d,1)));
            String date = sc.nextLine();
            try {
                //Parsing the date input
                checkDate = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("Invalid Date format");
            }

            if (checkDate != null && d.before(checkDate))
                return checkDate;

        }
    }
}